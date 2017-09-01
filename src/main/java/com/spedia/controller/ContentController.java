/**
 * 
 */
package com.spedia.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.jodah.expiringmap.ExpiringMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.spedia.dao.ContentDao;
import com.spedia.dao.MongoDao;
import com.spedia.dao.ReviewDao;
import com.spedia.model.Content;
import com.spedia.model.ContentBody;
import com.spedia.model.FieldGroup;
import com.spedia.model.Reviews;
import com.spedia.model.User;
import com.spedia.service.follow.IFollowService;
import com.spedia.service.user.UserService;
import com.spedia.utils.SEOURLUtils;
import com.spedia.utils.SchoolUtil;
import com.spedia.utils.SocialUtility;
import com.spedia.utils.WebConstants;

/**
 * @author pawan
 * 
 */
@Controller
public class ContentController extends BaseWSController{
	private static final String DISCUSSION = "discussion";
	private static final String RELATED_CONTENT_KEY = "relatedContentKey";
	private static final String TOP_SCHOOLS_KEY = "topSchoolsKey";
	private static Integer rowPerPage = 21;
	//caching for 1 hour
	Map<String, List<DBObject>> cache = ExpiringMap.builder()
			  .expiration(10, TimeUnit.MINUTES)
			  .build(); 
	@Autowired
	@Qualifier("mongoDao")
	private MongoDao mongoDao;
	@Autowired
	private ReviewDao reviewsDao;

	@Autowired
	private IFollowService followService;
	@Autowired
	@Qualifier("contentDao")
	private ContentDao contentDao;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@RequestMapping(value = { "/content.json" }, method = { RequestMethod.GET })
	public @ResponseBody String contentAPI(HttpServletRequest request,
			HttpServletResponse response) {
		String nid = request.getParameter("nid");
		Integer cid=null;
		if(!SocialUtility.chkNull(nid)){
			cid=Integer.parseInt(nid);
		}else{
			 return null;
		}
		DBObject content = mongoDao.getContentByNid(cid);
		return convertIntoJson(content);
	}

	@RequestMapping(value = { "/website.html" }, method = { RequestMethod.GET })
	public ModelAndView schoolDetails(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView view = new ModelAndView("schoolHome");
		String url = request.getParameter("url");
		String msg = request.getParameter("msg");
		// String url="website/bal-bharti-pub-school-sector-14-rohini-delhi";
		DBObject content = mongoDao.getContentByURL(url);
		if (content != null) {
			Integer nid = (Integer) content.get("nid");
			List<Reviews> reviews = reviewsDao.findByNid(nid, 10);
			BasicDBObject basicDBObject = new BasicDBObject(
					"field_group.target_id", nid);
			DBCursor dbCursor = mongoDao.getContent(basicDBObject);
			List<DBObject> news=new ArrayList<DBObject>();
			for (DBObject dbObject : dbCursor) {
				news.add(dbObject);
			}
			model.put("content", content);
			model.put("reviews", reviews);
			model.put("news", news);
			model.put("newsCount", news.size());
			model.put("msg", msg);
			dbCursor.close();
			if (content.containsField("location")) {
				DBObject location = (DBObject) content.get("location");
				String city = "";
					if(location!=null&&location.containsField("city")){
						city=(String) location.get("city");
					}
						
				if (!SocialUtility.chkNull(city)) {
					BasicDBObject relatedSchoolQuery = new BasicDBObject();
					relatedSchoolQuery.put("type", "group");
					/*relatedSchoolQuery.put("location.city", 
							new BasicDBObject("$regex", city)
							.append("$options", "i"));*/
					relatedSchoolQuery.put("location.city",city);
					relatedSchoolQuery.put("sortOrderBy","alias");
					String[] govtSchools={"GOVT","SARVODAYA","kv"};
					BasicDBObject nin=new BasicDBObject("$nin",govtSchools);
					relatedSchoolQuery.put("tags",nin);
					relatedSchoolQuery.put("order",1);
					DBObject indexKey=new BasicDBObject();
					indexKey.put("location.city", 1);
					DBCursor dbCursorRelated = mongoDao
							.getContent(relatedSchoolQuery).hint(indexKey);
					List<DBObject> relatedSchool=new ArrayList<DBObject>();
					for (DBObject dbObject : dbCursorRelated) {
						relatedSchool.add(dbObject);
					}
					dbCursorRelated.close();
					if(relatedSchool!=null){
						model.put("relatedSchoolSize", relatedSchool.size());
						model.put("relatedSchool", relatedSchool);
					}
					
				}
			}
			Boolean  redirectToAlias=(Boolean)content.get("redirectToAlias");
			if(redirectToAlias!=null&&redirectToAlias){
				String alias=(String)content.get("alias");
				if(alias!=null){
					response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					response.setHeader("Location", "/"+alias);
				}
			}
			
		}
		view.addAllObjects(model);
		return view;

	}
	@ExceptionHandler(Exception.class)
	@RequestMapping(value = { "/404.html","/403.html","/500.html" }, method = { RequestMethod.GET })
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView view = new ModelAndView("404");
		Map<String, Object> model = new HashMap<String, Object>();
		getLatestContent(model);
		view.addAllObjects(model);
		return view;
 
	}
	@RequestMapping(value = { "/content.html" }, method = { RequestMethod.GET })
	public ModelAndView contentDetails(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAndView view = new ModelAndView("news");
		String url = request.getParameter("url");
		// String
		// url="nursery-admission/heritage-school-vasant-kunj-nursery-admission-schedule-and-criteria-session-2013";
		DBObject content = mongoDao.getContentByURL(url);
		if (content != null && content.containsField("field_group")) {
			DBObject field_group = (DBObject) content.get("field_group");
			if (field_group != null) {
				Integer groupId = (Integer) field_group.get("target_id");
				if (groupId != null) {
					DBObject group = mongoDao.getContentByNid(groupId);
					model.put("group", group);
				}
			}
			Boolean  redirectToAlias=(Boolean)content.get("redirectToAlias");
			if(redirectToAlias!=null&&redirectToAlias){
				String alias=(String)content.get("alias");
				if(alias!=null){
					response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					response.setHeader("Location", "/"+alias);
				}
			}
		}
		if(content.containsField("updatedBy")){
			String postedBy=(String)content.get("updatedBy");
			if(SocialUtility.chkNull(postedBy)&&content.containsField("uid")){
				postedBy=(String)content.get("uid");
			}
			if(!SocialUtility.chkNull(postedBy)){
				DBObject postedByUser=mongoDao.getUserProfileByProfileID(postedBy);
				model.put("postedByUser", postedByUser);
			}
		}
		model.put("content", content);
		String contentType = (String) content.get("type");
		DBObject query = new BasicDBObject();
		List<DBObject> contents=cache.get(contentType);
		if (contents==null||contents.size()==0) {
			contents=new ArrayList<DBObject>();
			if (!SocialUtility.chkNull(contentType)
					&& contentType.contains("nursery")) {
				DBObject clause1 = new BasicDBObject("type",
						"nursery_admission");
				DBObject clause2 = new BasicDBObject("type",
						"nursery_admission_news");
				BasicDBList or = new BasicDBList();
				or.add(clause1);
				or.add(clause2);
				query = new BasicDBObject("$or", or);
			} else {
				query.put("type", contentType);
			}
			DBCursor dbCursor = mongoDao.getContent(query).limit(10);
			for (DBObject dbObject : dbCursor) {
				contents.add(dbObject);
			}
			dbCursor.close();
			cache.put(contentType, contents);
		}
		model.put("relatedContent", contents);
		view.addAllObjects(model);
		return view;

	}

	@RequestMapping(value = { "/tags.html" }, method = { RequestMethod.GET })
	public ModelAndView getContentByTags(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAndView view = new ModelAndView("searchResult");
		String url = request.getParameter("url");
		String pageNumber = request.getParameter("pageNumber");
		Integer p = 0;
		if (!SocialUtility.chkNull(pageNumber)) {
			p = Integer.parseInt(pageNumber);
		}
		url = url.replace("-", " ");
		// String
		// url="nursery-admission/heritage-school-vasant-kunj-nursery-admission-schedule-and-criteria-session-2013";
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("tags", url);
		DBCursor dbCursor = mongoDao.getContent(basicDBObject).skip(p * rowPerPage).limit(rowPerPage);
		Integer totalCount = dbCursor.count();
		System.out.println("totalCount " + totalCount);
		List<DBObject> contents=new ArrayList<DBObject>();
		for (DBObject dbObject : dbCursor) {
			contents.add(dbObject);
		}
		dbCursor.close();
		model.put("contents", contents);
		model.put("totalCount", totalCount);
		model.put("rowsPerPage", rowPerPage);
		getLatestContent(model);
		view.addAllObjects(model);
		return view;

	}

	@RequestMapping(value = { "/contentType.html" }, method = { RequestMethod.GET })
	public ModelAndView getContentByType(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAndView view = new ModelAndView("searchResult");
		String type = request.getParameter("type");
		String pageNumber = request.getParameter("pageNumber");
		Integer p = 0;
		if (!SocialUtility.chkNull(pageNumber)) {
			p = Integer.parseInt(pageNumber);
		}
		// String
		// url="nursery-admission/heritage-school-vasant-kunj-nursery-admission-schedule-and-criteria-session-2013";
		DBObject query = new BasicDBObject();
		if (!SocialUtility.chkNull(type) && type.contains("nursery")) {
			DBObject clause1 = new BasicDBObject("type", "nursery_admission");
			DBObject clause2 = new BasicDBObject("type",
					"nursery_admission_news");
			BasicDBList or = new BasicDBList();
			or.add(clause1);
			or.add(clause2);
			query = new BasicDBObject("$or", or);
			DBObject nurseryContent = mongoDao.getContentByURL("nursery-admission-news/nursery-admission-delhi-2016-17-update");
			model.put("nurseryContent", nurseryContent);
		} else {
			query.put("type", type);
		}
		DBCursor dbCursor = mongoDao.getContent(query).skip(p * rowPerPage).limit(rowPerPage);
		Integer totalCount = dbCursor.count();
		System.out.println("totalCount " + totalCount);
		List<DBObject> contents=new ArrayList<DBObject>();
		for (DBObject dbObject : dbCursor) {
			contents.add(dbObject);
		}
		dbCursor.close();
		model.put("contents", contents);
		model.put("totalCount", totalCount);
		model.put("rowsPerPage", rowPerPage);
		getLatestContent(model);
		view.addAllObjects(model);
		return view;

	}

	@RequestMapping(value = { "/searchSchool.html" }, method = { RequestMethod.GET })
	public ModelAndView searchSchool(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAndView view = new ModelAndView("searchResult");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String postal_code = request.getParameter("postal_code");
		String schoolCode = request.getParameter("school-code");
		String affiliationCode = request.getParameter("affiliation-code");
		String distance = request.getParameter("distance");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		String locationSearchURL = request.getParameter("url");
		// String
		// url="nursery-admission/heritage-school-vasant-kunj-nursery-admission-schedule-and-criteria-session-2013";
		String pageNumber = request.getParameter("pageNumber");
		Integer p = 0;
		if (!SocialUtility.chkNull(pageNumber)) {
			p = Integer.parseInt(pageNumber);
		}
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("type", "group");
	    DBObject indexKey = new BasicDBObject();
		if (!SEOURLUtils.chkNull(province)) {
			basicDBObject.put("location.province", 
					new BasicDBObject("$regex", province)
					.append("$options", "i"));
			//indexKey.put("location.province", 1);
		}
		
		if (!SEOURLUtils.chkNull(city)) {
			basicDBObject.put("location.city", 
					new BasicDBObject("$regex", city)
					.append("$options", "i"));
			indexKey.put("location.city", 1);
		}
		if (!SEOURLUtils.chkNull(postal_code)) {
			basicDBObject.put("location.postal_code", postal_code);
			indexKey.put("location.postal_code", 1);
		}
		if (!SEOURLUtils.chkNull(affiliationCode)) {
			basicDBObject.put("sd.AFF_NO", affiliationCode);
			indexKey.put("sd.AFF_NO", 1);
		}
		if (!SEOURLUtils.chkNull(schoolCode)) {
			basicDBObject.put("schoolCode", schoolCode);
			indexKey.put("schoolCode", 1);
		}
		if(!SEOURLUtils.chkNull(locationSearchURL)){
				DBCursor locationSearchData=mongoDao.getLocationSearchData(new BasicDBObject("alias",locationSearchURL));
				DBObject locationSearchQuery=locationSearchData.one();
				locationSearchData.close();
				if(locationSearchQuery!=null){
					model.put("locationSearch", locationSearchQuery);
					Integer d=(Integer)locationSearchQuery.get("d");
					BasicDBObject loc=(BasicDBObject)locationSearchQuery.get("loc");
					BasicDBList coordinates= (BasicDBList)loc.get("coordinates");
					Double longi=(Double)coordinates.get(0);
					Double lat=(Double)coordinates.get(1);
					int distanceInMeter=d*1000;
					basicDBObject=getSearchCriteraByDistance(distanceInMeter,longi,lat);
				}
		}
		if (!SEOURLUtils.chkNull(distance)) {
			String address = request.getParameter("address");
			if(!SEOURLUtils.chkNull(address)&&SEOURLUtils.chkNull(longitude)){
				 String[] latLong=	SchoolUtil.getLatLong(address);
				 latitude=latLong[0];
				 longitude=latLong[1];
			}
			Integer d=Integer.valueOf(distance);
			Double longi=Double.valueOf(longitude);
			Double lat=Double.valueOf(latitude);
			int distanceInMeter=d*1000;
			basicDBObject=getSearchCriteraByDistance(distanceInMeter,longi,lat);
			try {
				DBObject locationSearch=saveSearchQuery(address, d, longi, lat);
				if(locationSearch!=null){
					model.put("locationSearch", locationSearch);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		basicDBObject.put("sortOrderBy","changed");
		String[] govtSchools={"GOVT","SARVODAYA","kv"};
		BasicDBObject nin=new BasicDBObject("$nin",govtSchools);
		basicDBObject.put("tags",nin);
		DBCursor dbCursor = mongoDao.getContent(basicDBObject).skip(p * rowPerPage).limit(rowPerPage).hint(indexKey);
		Integer totalCount = dbCursor.count();
		if (!SEOURLUtils.chkNull(affiliationCode)&&dbCursor!=null) {
		 DBObject schoolInfo=	dbCursor.one();
		 String url=(String)schoolInfo.get("alias");
		 String redirectedUrl="redirect:"+WebConstants.APPLICATION_URL+url;
		 view.setViewName(redirectedUrl);
			dbCursor.close();
		 return view;
	    }
		if (!SEOURLUtils.chkNull(schoolCode)&&dbCursor!=null) {
			 DBObject schoolInfo=	dbCursor.one();
			 String url=(String)schoolInfo.get("alias");
			 String redirectedUrl="redirect:"+WebConstants.APPLICATION_URL+url;
			 view.setViewName(redirectedUrl);
				dbCursor.close();
			 return view;
		    }
		List<DBObject> contents=new ArrayList<DBObject>();
		for (DBObject dbObject : dbCursor) {
			contents.add(dbObject);
		}
		System.out.println("totalCount " + totalCount);
		model.put("contents", contents);
		model.put("totalCount", totalCount);
		model.put("rowsPerPage", rowPerPage);
		getLatestContent(model);
		view.addAllObjects(model);
		dbCursor.close();
		return view;

	}

	private DBObject saveSearchQuery(String address, Integer d, Double longi,
			Double lat) {
		BasicDBObject locationSearch=new BasicDBObject();
		String title="School With In "+d +" km of "+address;
		locationSearch.put("a", address);
		locationSearch.put("d", d);
		String url=SEOURLUtils.getSEOURL("search-school-by-location", title);
		locationSearch.put("alias",url );
		BasicDBObject loc = new BasicDBObject();
		loc.put("type", "Point");
		loc.put("coordinates", new double[] { longi, lat });
		locationSearch.put("loc", loc);
		Long time = SEOURLUtils.getCurrentTime();
		locationSearch.put("ct", time);
		locationSearch.put("ut", time);
		mongoDao.saveLocationSearchData(locationSearch);
		return locationSearch;
	}

	private BasicDBObject getSearchCriteraByDistance(int distanceInMeter,double longitude,double latitude) {
		BasicDBObject filter = new BasicDBObject();
		filter.put("type", "Point");
		filter.put("coordinates", new double[] { longitude, latitude });
		BasicDBObject geometry=new BasicDBObject("$geometry",filter);
		geometry.put("$maxDistance", distanceInMeter);
		BasicDBObject near=new BasicDBObject("$near",geometry);
		BasicDBObject query = new BasicDBObject("loc", near);
		return query;
		
	}

	@RequestMapping(value = { "/followSchool.html" }, method = { RequestMethod.GET })
	public @ResponseBody
	String followSchool(HttpServletRequest request, HttpServletResponse response) {
		String result="0";
		try {
			Integer nid = Integer.parseInt(request.getParameter("nid"));
			String uidS = SocialUtility.getCookieByKey(request, "uid");
			if (!SocialUtility.chkNull(uidS)) {
				Integer uid = Integer.parseInt(uidS);
				Integer status = Integer.parseInt(request.getParameter("status"));
				User user = new User();
				user.setUid(uid);
				if (status.equals(1)) {
					//to follow
					followService.follow(nid, user, true);
					result="1";
				}
				if (status.equals(0)) {
					//to unfollow
					followService.follow(nid, user, false);
					result="0";
				}

			}
		} catch (Exception e) {
			result="2";
			e.printStackTrace();
		}

		return result;

	}

	@RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET })
	public ModelAndView home(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<DBObject> topSchools=cache.get(TOP_SCHOOLS_KEY);
		if (topSchools==null||topSchools.size()==0) {
			topSchools = mongoDao.getTopReviewedSchool();
			cache.put(TOP_SCHOOLS_KEY, topSchools);
		}
		model.put("topSchools", topSchools);
		ModelAndView view = new ModelAndView("home");
		getLatestContent(model);
		view.addAllObjects(model);
		return view;
	}

	private void getLatestContent(Map<String, Object> model) {
		List<DBObject> contents=cache.get(RELATED_CONTENT_KEY);
		if(contents==null||contents.size()==0){
			contents=new ArrayList<DBObject>();
			DBObject query = new BasicDBObject();
			/*BasicDBList or = new BasicDBList();
			DBObject clause1 = new BasicDBObject("type", "group");
			or.add(clause1);
			DBObject clause2 = new BasicDBObject("type", "nursery_admission_news");
			DBObject clause3 = new BasicDBObject("type", "summer_camp");
			DBObject clause4 = new BasicDBObject("type", "schools_news");
			DBObject clause5 = new BasicDBObject("type", "parents_tips");
			DBObject clause6 = new BasicDBObject("type", DISCUSSION);
			or.add(clause2);
			or.add(clause3);
			or.add(clause4);
			or.add(clause5);
			or.add(clause6);*/
			query.put("type", new BasicDBObject("$ne", "group"));
			DBCursor dbCursor=mongoDao.getContent(query).limit(10);
			for (DBObject dbObject : dbCursor) {
				contents.add(dbObject);
			}
			cache.put(RELATED_CONTENT_KEY, contents);
			dbCursor.close();
		}
		
		model.put("relatedContent", contents);
		DBCursor locationSearchData=mongoDao.getLocationSearchData(new BasicDBObject());
		List<DBObject> locationSearchContent=new ArrayList<DBObject>();
		for (DBObject dbObject : locationSearchData) {
			locationSearchContent.add(dbObject);
		}
		model.put("locationSearchData", locationSearchContent);
		locationSearchData.close();
	}

	@RequestMapping(value = "/savefbgroupdata.html")
	public @ResponseBody
	String saveFbGroupData(HttpServletRequest request,
			HttpServletResponse response) {
		String jsonData = request.getParameter("data");
		if (jsonData != null) {
			mongoDao.saveFBGroupData(jsonData);
		} else {
			return "error";
		}
		return "done";
	}

	@RequestMapping(value = { "/add_news.html" }, method = { RequestMethod.GET })
	public ModelAndView addNews(HttpServletRequest request,
			HttpServletResponse response) {
		// Content content=new Content();
		ModelAndView view = new ModelAndView("addNews", "content",
				new Content());
		return view;
	}
	@RequestMapping(value = { "/ask_question.html" }, method = { RequestMethod.GET })
	public ModelAndView ask(HttpServletRequest request,
			HttpServletResponse response) {
		// Content content=new Content();
		ModelAndView view = new ModelAndView("ask", "content",
				new Content());
		return view;
	}
	@RequestMapping(value = { "/edit_content.html" }, method = { RequestMethod.GET })
	public ModelAndView editContent(HttpServletRequest request,
			HttpServletResponse response) {
		String url=request.getParameter("url");
		DBObject content = mongoDao.getContentByURL(url);
		Content c=new Content();
		DBObject body=(DBObject)content.get("body");
		String body_content=(String)body.get("value");
		c.setBody_content(body_content);
		c.setTitle((String)content.get("title"));
		c.setImagePath((String)content.get("imagePath"));
		c.setAlias(((String)content.get("alias")));
		c.setMeta_description((String)content.get("meta_description"));
		c.setMeta_keywords((String)content.get("meta_keywords"));
		c.setRobots((String)content.get("robots"));
		ModelAndView view = new ModelAndView("addNews", "content",
				c);
		return view;

	}

	@RequestMapping(value = "/submitNews.html")
	public ModelAndView submit(
			@Valid @ModelAttribute("content") Content content,
			 HttpServletRequest httpServletRequest ,BindingResult result) {
		ModelAndView view = new ModelAndView("addNews");
		Boolean isResponseCorrect=true;
			isResponseCorrect = SchoolUtil.isValidCaptcha(httpServletRequest);
		String isAdmin=	SocialUtility.getCookieByKey(httpServletRequest, "isAdmin");
		if(!SocialUtility.chkNull(isAdmin)){
			isResponseCorrect=true;
		}
		Map<String, Object> model = new HashMap<String, Object>();
		// Get School Details
		if (result.hasErrors()||!isResponseCorrect) {
			if(!isResponseCorrect){
				model.put("errorMsg", "Invalid Captcha ,Please try Again");
			}
			view = new ModelAndView("ask", "content",
					new Content());
			view.addAllObjects(model);
			return view; 
		}
		FileOutputStream outputStream = null;
		MultipartFile imageFile = content.getImageFile();
		if (imageFile != null && !imageFile.isEmpty()) {
			String filePath = WebConstants.contentImageDirectory+"/"
					+ imageFile.getOriginalFilename();
			int i=filePath.indexOf("/spedia");
			String imagePath=filePath.substring(i);
			content.setImagePath(imagePath);
			// String path =
			// request.getSession().getServletContext().getContextPath();
			System.out.println(filePath);
			try {
				outputStream = new FileOutputStream(new File(filePath));
				FileCopyUtils.copy(imageFile.getInputStream(), outputStream);
				outputStream.close();
			} catch (Exception e) {
				System.out.println("not written");
				// e.printStackTrace();
			}
		} else {
			content.setImagePath(null);
		}
		content.setImageFile(null);
		String[] tags = content.getTags();
		String seourl=content.getAlias();
		String type = content.getType();
		if(SocialUtility.chkNull(seourl)){
			seourl = SEOURLUtils.getSEOURL(type,
					content.getTitle());
		}
		if(!SocialUtility.chkNull(type)&&type.equals(DISCUSSION)){
			content.setStatus(0);
		}
		ContentBody body=new ContentBody();
		body.setValue(content.getBody_content());
		body.setSummary(content.getBody_content());
		content.setBody(body);
		String sid=content.getSid();
		if(!SocialUtility.chkNull(sid)){
			seourl=seourl+"-"+sid;
			FieldGroup fieldGroup=new FieldGroup();
			fieldGroup.setTarget_id(Integer.parseInt(sid));
			content.setField_group(fieldGroup);
			DBObject node=mongoDao.getContentByNid(Integer.parseInt(sid));
			String url=(String)node.get("alias");
			String redirectedUrl="redirect:"+WebConstants.APPLICATION_URL+url;
			String msg="Thanks For Posting News ";
			redirectedUrl+="?msg="+msg;
			view.setViewName(redirectedUrl);
		}else{
			String redirectedUrl="redirect:"+WebConstants.APPLICATION_URL+seourl;
			String msg="Thanks For Posting News ";
			redirectedUrl+="?msg="+msg;
			view.setViewName(redirectedUrl);
		}
		content.setAlias(seourl);
		System.out.println(seourl);
		content.setBody_content(null);
		Long time = SEOURLUtils.getCurrentTime();
		content.setChanged(time);
		content.setCreated(time);
		if (content.getStatus() == 0) {
			content.setChanged(time);
		}
		String name = httpServletRequest.getParameter("name");
		String email = httpServletRequest.getParameter("email");
		content.setUid(name);
		content.setUpdatedBy(email);
		contentDao.saveContent(content);
		return view;
	}
	@RequestMapping(value = "/get_all_unmoderated_content.html")
	public ModelAndView getAllUnModeratedContent(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAndView view = new ModelAndView("content_moderation");
		DBObject query = new BasicDBObject();
		DBObject clause1 = new BasicDBObject("type", "nursery_admission");
		DBObject clause2 = new BasicDBObject("type", "nursery_admission_news");
		DBObject clause3 = new BasicDBObject("type", "summer_camp");
		DBObject clause4 = new BasicDBObject("type", "schools_news");
		DBObject clause5 = new BasicDBObject("type", "parents_tips");
		DBObject clause6 = new BasicDBObject("type", DISCUSSION);
		BasicDBList or = new BasicDBList();
		or.add(clause1);
		or.add(clause2);
		or.add(clause3);
		or.add(clause4);
		or.add(clause5);
		or.add(clause6);
		query = new BasicDBObject("$or", or);
		query.put("status", 0);
		DBCursor dbCursor=mongoDao.getContent(query);
		List<DBObject> contents=new ArrayList<DBObject>();
		for (DBObject dbObject : dbCursor) {
			contents.add(dbObject);
		}
		dbCursor.close();
		model.put("contents", contents);
		view.addAllObjects(model);
		return view;

	}
	@RequestMapping(value = "/sitemap.html")
	public ModelAndView sitemap(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		String pageNumber = request.getParameter("pageNumber");
		Integer p = 0;
		if (!SocialUtility.chkNull(pageNumber)) {
			p = Integer.parseInt(pageNumber);
		}
		ModelAndView view = new ModelAndView("sitemap");
		DBObject query = new BasicDBObject();
		query.put("status", 1);
		int rowPerPage=100;
		DBCursor dbCursor=mongoDao.getContent(query).skip(p * rowPerPage).limit(rowPerPage);;
		List<DBObject> contents=new ArrayList<DBObject>();
		for (DBObject dbObject : dbCursor) {
			contents.add(dbObject);
		}
		int totalCount=dbCursor.count();
		System.out.println("totalCount " + totalCount);
		model.put("contents", contents);
		model.put("totalCount", totalCount);
		model.put("rowsPerPage", rowPerPage);
		model.put("contents", contents);
		view.addAllObjects(model);
		return view;

	}
	@RequestMapping(value = { "/content_moderation.html" }, method = { RequestMethod.GET })
	public @ResponseBody String contentModeration(HttpServletRequest request, HttpServletResponse response) {
		String nidS=request.getParameter("id");
		String status=request.getParameter("status");
		try {
			if(!SocialUtility.chkNull(nidS)&&!SocialUtility.chkNull(status)){
				DBObject node=mongoDao.getContentByNid(Integer.parseInt(nidS));
				node.put("status", Integer.parseInt(status));
				mongoDao.updateOverAllRating(node);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
		
		return "1";
	}

}
