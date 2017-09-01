package com.spedia.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.spedia.dao.MongoDao;
import com.spedia.model.User;
import com.spedia.model.UserRole;
import com.spedia.service.user.UserService;
import com.spedia.utils.EmailValidator;
import com.spedia.utils.SchoolUtil;
import com.spedia.utils.SocialUtility;
@Controller
public class LoginController {
	@Autowired
	@Qualifier("mongoDao")
	private MongoDao mongoDao;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired @Qualifier("authenticationManagerAutoLogin")
	private AuthenticationManager authenticationManagerAutoLogin;
	
	@RequestMapping(value = { "/", "/userHome.html" }, method = RequestMethod.GET)
	public ModelAndView defaultPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		Map<String, Object> model = new HashMap<String, Object>();
		Integer uid;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth!=null&&!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails=(UserDetails) auth.getPrincipal();
			User userExist=userService.findByUserEmail(userDetails.getUsername());
			if(userExist!=null){
				setUserDataInSession(response,userExist);
				uid=userExist.getUid();
				BasicDBObject basicDBObject = new BasicDBObject();
				basicDBObject.put("type", "group");
				basicDBObject.put("f", uid);
				DBCursor dbCursor = mongoDao.getContent(basicDBObject);
				List<DBObject> contents=new ArrayList<DBObject>();
				for (DBObject dbObject : dbCursor) {
					contents.add(dbObject);
				}
				dbCursor.close();
				model.put("contents", contents);
				List<Integer> sids=new ArrayList<Integer>();
				for (DBObject content : contents) {
					Integer sid=(Integer) content.get("nid");
					sids.add(sid);
				}
				BasicDBObject sidsQuery=new BasicDBObject("$in",sids);
				BasicDBObject relatedContentQuery = new BasicDBObject(
						"field_group.target_id", sidsQuery);
				DBCursor newsDbCursor = mongoDao.getContent(relatedContentQuery);
				List<DBObject> news=new ArrayList<DBObject>();
				for (DBObject dbObject : newsDbCursor) {
					news.add(dbObject);
				}
				model.put("news", news);
				view.addAllObjects(model);
			}
			
		}
		    String redirectURL=SchoolUtil.getRedirectUrl(request);
			if(redirectURL==null){
				view.setViewName("userHome");
			}
			if(redirectURL!=null&&redirectURL.contains("login.html")){
				redirectURL="/spedia/index.html";
				response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", redirectURL);
			}
		return view;

	}
	@RequestMapping(value = { "/logout.html" }, method = { RequestMethod.GET })
	public @ResponseBody String logOut(HttpServletRequest request, HttpServletResponse response) {
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		cookieClearingLogoutHandler.logout(request, response, null);
		securityContextLogoutHandler.logout(request, response, null);
		request.getSession().invalidate();
		removeUserDataInSession(response);
		return SchoolUtil.getRedirectUrl(request);
	}
	@RequestMapping(value = { "/subscibeUser.html" }, method = { RequestMethod.GET })
	public @ResponseBody String subscibeUser(HttpServletRequest request, HttpServletResponse response) {
		String email=request.getParameter("email");
		String op=request.getParameter("op");
		Boolean valid=EmailValidator.validate(email);
		String result="0";
		if(valid){
			mongoDao.saveSubscription(email, op);
			result="1";
		}
		return result;
	}
	@RequestMapping(value = { "/registerUser.html" }, method = { RequestMethod.GET })
	public @ResponseBody String registerUser(HttpServletRequest request, HttpServletResponse response) {
		String data=request.getParameter("data");
		String socialType=request.getParameter("socialType");
		User user=SocialUtility.getUserFromJson(data);
		String email=user.getMail();
		User userExist=userService.findByUserEmail(email);
		String status="1";
		if (userExist==null) {
			WriteResult writeResult = mongoDao.saveUserFbData(data);
			System.out.println(writeResult.getUpsertedId());
			user.setSocialType(socialType);
			user.setPassword(SocialUtility.getMD5(user.getSocialLoginId()));
			user = userService.registerUser(user);
			setUserDataInSession(response,user);
			status="0";
		}else{
			setUserDataInSession(response,userExist);
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		Boolean autoLogin=doAutoLogin(request,userDetails);
		if(!autoLogin){
			//login false
			status="-1";
		}
		String redirectURL=SchoolUtil.getRedirectUrl(request);
		if(redirectURL==null||redirectURL.contains("login.html")){
			redirectURL="/spedia/index.html";
		}
		return redirectURL;
	}
	private void setUserDataInSession(HttpServletResponse response, User userExist) {
		Set<UserRole>  userroles=userExist.getUserRoleses();
		List<String> roles=new ArrayList();
		for (UserRole userRole : userroles) {
			roles.add(userRole.getRole());
		}
		Boolean isAdmin=false;
		if(roles!=null){
		 isAdmin=roles.contains("ROLE_ADMIN");
		}
		SocialUtility.addCookie("username", userExist.getUsername(), response);
		SocialUtility.addCookie("isAdmin", isAdmin.toString(), response);
		SocialUtility.addCookie("email", userExist.getMail(), response);
		SocialUtility.addCookie("uid", userExist.getUid()+"", response);
		SocialUtility.addCookie("socialLoginId", userExist.getSocialLoginId(), response);
	}
	private void removeUserDataInSession(HttpServletResponse response) {
		SocialUtility.removeCookie("username", response);
		SocialUtility.removeCookie("email", response);
		SocialUtility.removeCookie("uid", response);
		SocialUtility.removeCookie("socialLoginId", response);
		SocialUtility.removeCookie("isAdmin", response);
	}
	private Boolean doAutoLogin(HttpServletRequest request,UserDetails userDetails) {
		// perform login authentication
		Boolean autoLogin=false;
	    try {
	      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
	      authenticationManagerAutoLogin.authenticate(authentication);
	      // redirect into secured main page if authentication successful
	      SecurityContextHolder.getContext().setAuthentication(authentication);
	      request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
	      autoLogin=true;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return autoLogin;
	}

	@RequestMapping(value = "/admin.html", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login.html")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,HttpServletResponse response,Principal principal) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if(auth!=null&&auth.isAuthenticated()){
			 String name = auth.getName();
			 if(name!=null&&!name.equals("anonymousUser")){
				 String redirectURL=SchoolUtil.getRedirectUrl(request);
					if(redirectURL==null||redirectURL.contains("login.html")){
						redirectURL="/spedia/index.html";
					}
					response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
					response.setHeader("Location", redirectURL);
			 }
		 }
	   //   String name = auth.getName(); //get logged in username
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}
	public Boolean isAuthenticated(){
		Boolean isLogin=Boolean.FALSE;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth!=null&&!(auth instanceof AnonymousAuthenticationToken)) {
			isLogin=Boolean.TRUE;
		}
		return isLogin;
	}
	public String getUserName(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public Boolean isAdmin(){
		Boolean isAdmin=Boolean.FALSE;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<GrantedAuthority> roles=(List<GrantedAuthority>) auth.getAuthorities();
			for (GrantedAuthority grantedAuthority : roles) {
				isAdmin=grantedAuthority.getAuthority().equals("ROLE_ADMIN");
				if(isAdmin){
					break;
				}
			}
		return isAdmin;
	}
}