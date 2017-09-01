package com.spedia.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.DBObject;
import com.spedia.dao.MongoDao;
import com.spedia.model.Reviews;
import com.spedia.service.review.IReviewService;
import com.spedia.utils.SchoolUtil;
import com.spedia.utils.SocialUtility;
import com.spedia.utils.WebConstants;

/*Pawan */
@Controller
public class ReviewController {
	@Autowired
	private IReviewService reviewService;
	@Autowired
	@Qualifier("mongoDao")
	private MongoDao mongoDao;

	@RequestMapping(value = "/get_all_unmoderated_review.html")
	public ModelAndView reviewModerat(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAndView view = new ModelAndView("review_moderation");
		List<Reviews> reviews = reviewService.getAllUnModeratedReviews();
		model.put("reviews", reviews);
		view.addAllObjects(model);
		return view;

	}

	@RequestMapping(value = "/approveReview.html")
	public Reviews approveReview(HttpServletRequest request,
			HttpServletResponse response) {
		Integer rid = Integer.parseInt(request.getParameter("rid"));
		Reviews reviews = reviewService.approveReview(rid);
		return reviews;

	}

	@RequestMapping(value = "/rejectReview.html")
	public Reviews rejectReview(HttpServletRequest request,
			HttpServletResponse response) {
		Integer rid = Integer.parseInt(request.getParameter("rid"));
		Reviews reviews = reviewService.rejectReview(rid);
		return reviews;

	}

	@RequestMapping(value = { "/write_review.html" }, method = { RequestMethod.GET })
	public ModelAndView writeReview(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		Reviews reviews = new Reviews();
		String nidS=request.getParameter("nid");
		Integer nid=Integer.parseInt(nidS);
		String type=request.getParameter("type");
		DBObject content=mongoDao.getContentByNid(nid);
		reviews.setNid(nid);
		ModelAndView view = new ModelAndView("writeReview", "reviews",
				reviews);
		if(!SocialUtility.chkNull(type)&&"popup".equals(type)){
			 view = new ModelAndView("writeReviewPOPUP", "reviews",
						reviews);
		}
		Map<Integer, String> ratingOption = new HashMap<Integer, String>();
		ratingOption.put(1,"poor");
		ratingOption.put(2,"Average");
		ratingOption.put(3,"Good");
		ratingOption.put(4,"very Good");
		ratingOption.put(5,"Excellent");
		model.put("ratingOption", ratingOption);
		model.put("content", content);
		view.addAllObjects(model);
		return view;

	}

	@RequestMapping(value = "/submitReview.html")
	public ModelAndView submit(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@Valid @ModelAttribute("Reviews") Reviews reviews,
			BindingResult result) {
		Integer uid=reviews.getUid();
		Boolean isResponseCorrect=true;
		if (uid==null||uid==0) {
			isResponseCorrect = SchoolUtil.isValidCaptcha(httpServletRequest);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAndView view = new ModelAndView("writeReview","reviews",
				reviews);
		// Get School Details
		DBObject content=mongoDao.getContentByNid(reviews.getNid());
		if (result.hasErrors()||!isResponseCorrect) {
			Map<Integer, String> ratingOption = new HashMap<Integer, String>();
			ratingOption.put(1,"poor");
			ratingOption.put(2,"Average");
			ratingOption.put(3,"Good");
			ratingOption.put(4,"very Good");
			ratingOption.put(5,"Excellent");
			model.put("ratingOption", ratingOption);
			model.put("content", content);
			if(!isResponseCorrect){
				model.put("errorMsg", "Invalid Captcha ,Please try Again");
			}
			view.addAllObjects(model);
			return view; 
		}
		reviews.setStatus(0);
		Date date = new Date();
		Long created = date.getTime();
		reviews.setCreated(created);
		reviewService.writeReview(reviews);
		
		
		String url=(String)content.get("alias");
		String redirectedUrl="redirect:"+WebConstants.APPLICATION_URL+url;
		String msg="Thanks For Writing Review";
		redirectedUrl+="?msg="+msg;
		view.setViewName(redirectedUrl);
		return view;
	}

}
