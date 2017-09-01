package com.spedia.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.spedia.dao.MongoDao;
import com.spedia.model.SchoolBean;
import com.spedia.model.SchoolData;
import com.spedia.model.SchoolInformation;
import com.spedia.model.SchoolPhotoVideoBean;
import com.spedia.service.CounterService;
import com.spedia.utils.SEOURLUtils;
import com.spedia.utils.SchoolUtil;
import com.spedia.utils.SocialUtility;
import com.spedia.utils.WebConstants;

@Controller
public class SchoolController{
	private static final String ADMISSION_APPLICATION_COLLECTION = "admission_application";
	private static final String DD_MM_YYYY = "dd-mm-yyyy";
	public static Gson gson =new Gson();
	@Autowired
	@Qualifier("mongoDao")
	private MongoDao mongoDao;
	@Autowired
	CounterService counterService;
	
	@RequestMapping(value = { "/compare-school.html" }, method = { RequestMethod.GET })
	public ModelAndView compareSchool(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		String ids=request.getParameter("ids");
		String[] sids=ids.split(",");
		List<Integer> nids=new ArrayList<Integer>(3);
		for (String nid : sids) {
			nids.add(Integer.parseInt(nid));
		}
		BasicDBObject sidsQuery=new BasicDBObject("$in",nids);
		BasicDBObject relatedContentQuery = new BasicDBObject(
				"_id", sidsQuery);
		DBCursor dbCursor = mongoDao.getContent(relatedContentQuery).limit(3);
		List<DBObject> contents=new ArrayList<DBObject>();
		for (DBObject dbObject : dbCursor) {
			contents.add(dbObject);
		}
		dbCursor.close();
		model.put("contents", contents);
		ModelAndView view = new ModelAndView("compareSchool");
		view.addAllObjects(model);
		return view;

	}

	@RequestMapping(value = { "/edit_school_info.html" }, method = { RequestMethod.GET })
	public ModelAndView editSchoolDetails(HttpServletRequest request,
			HttpServletResponse response) {
		String nidS=request.getParameter("sid");
		Integer nid=null;
		if(!SocialUtility.chkNull(nidS)){
			nid=Integer.parseInt(nidS);
		}
		DBObject content = mongoDao.getContentByNid(nid);
		String schoolName=(String)content.get("title");
		SchoolInformation schoolInformation=new SchoolInformation();
		schoolInformation.setContent(content);
		schoolInformation.setSchoolName(schoolName);
		DBObject body=(DBObject)content.get("body");
		String schoolContent=(String)body.get("value");
		schoolInformation.setSchoolContent(schoolContent);
		String schoolSummary=(String)body.get("summary");
		schoolInformation.setSchoolSummary(schoolSummary);
		DBObject sd=(DBObject)content.get("sd");
		SchoolData schoolData=gson.fromJson(gson.toJson(sd),SchoolData.class);
		schoolInformation.setSchoolData(schoolData);
		ModelAndView view = new ModelAndView("editSchoolInfo",
				"schoolInformation", schoolInformation);
		return view;

	}
	@RequestMapping(value = { "/add_school_info.html" }, method = { RequestMethod.GET })
	public ModelAndView addSchoolDetails(HttpServletRequest request,
			HttpServletResponse response) {
		SchoolInformation schoolInformation=new SchoolInformation();
		ModelAndView view = new ModelAndView("editSchoolInfo",
				"schoolInformation", schoolInformation);
		return view;

	}
	@RequestMapping(value = { "/submitSchoolInfo.html" }, method = { RequestMethod.POST })
	public ModelAndView submitSchoolDetails(
			@Valid @ModelAttribute("schoolInformation") SchoolInformation schoolInformation,HttpServletRequest request,
			BindingResult result) throws FileNotFoundException {
		ModelAndView view = new ModelAndView("editSchoolInfo",
				"schoolInformation", schoolInformation);
		SchoolBean schoolsImages = new SchoolBean();
		Integer sid = schoolInformation.getSid();
		DBObject schoolInfo = new BasicDBObject();
		Long time = SEOURLUtils.getCurrentTime();
		DBObject body=new BasicDBObject();
		DBObject schImages=new BasicDBObject();
		String title = request.getParameter("title");
		if(sid!=null&&!SocialUtility.chkNull(sid)){
				schoolInfo = mongoDao.getContentByNid(sid);
				schoolInfo.put("title", title);
				body=(DBObject)schoolInfo.get("body");
				if(schoolInfo.get("schoolsImages")!=null){
					schImages=(DBObject)schoolInfo.get("schoolsImages");
					if(schImages.get("BackGroundImagePath")!=null){
					schoolsImages.setBackGroundImagePath((String)schImages.get("BackGroundImagePath"));
					}
					if(schImages.get("LogoPath")!=null){
						schoolsImages.setLogoPath((String)schImages.get("LogoPath"));
					}
					
				}
				
		}else{
			sid=counterService.getNextSequence("_id");
			schoolInfo.put("_id", sid);
			schoolInfo.put("nid", sid);
			schoolInfo.put("type", "group");
			if(!SocialUtility.chkNull(title)){
			 String	seourl = SEOURLUtils.getSEOURL("website",
						title);
			 schoolInfo.put("alias", seourl);
			}
			schoolInfo.put("created", time);
		}
		updateImages(schoolInformation, schoolsImages, sid, schoolInfo);
		String schoolContent=schoolInformation.getSchoolContent();
		String summary=schoolInformation.getSchoolSummary();
		body.put("value", schoolContent);
		body.put("summary", summary);
		schoolInfo.put("body", body);
		SchoolData schoolData=schoolInformation.getSchoolData();
		if (schoolData!=null) {
			String email = schoolData.getE();
			String contactNumber = schoolData.getPHONE_NO();
			DBObject sd = (DBObject) schoolInfo.get("sd");
			if (sd==null) {
				sd=new BasicDBObject();
			}
			sd.put("E", email);
			sd.put("PHONE_NO", contactNumber);
			Integer PS_SEATS = schoolData.getPS_SEATS();
			Integer PP_SEATS = schoolData.getPP_SEATS();
			Integer FEES = schoolData.getFEES();
			sd.put("PS_SEATS", PS_SEATS);
			sd.put("PP_SEATS", PP_SEATS);
			sd.put("FEES", FEES);
			schoolInfo.put("sd", sd);
		}
		String tags = request.getParameter("tags");
		if(!SEOURLUtils.chkNull(tags)){
			BasicDBList tagsObject = (BasicDBList)schoolInfo.get("tags");
			String tagsA[]=tags.split(",");
			for (String tag : tagsA) {
				if(!tagsObject.contains(tag)){
					tagsObject.add(tag);
				}
				
			}
			System.out.println(tags);
			
		}
		String drawDate1 = request.getParameter("drawDate1");
		String drawDate2 = request.getParameter("drawDate2");
		String drawDate3 = request.getParameter("drawDate3");
		String lat = request.getParameter("lat");
		String lng = request.getParameter("lng");
		if(!SEOURLUtils.chkNull(drawDate1)){
			Date d1=convertStringToDate(drawDate1, DD_MM_YYYY);
			schoolInfo.put("drawDate1", d1.getTime()/1000);
		}
		if(!SEOURLUtils.chkNull(drawDate2)){
			Date d2=convertStringToDate(drawDate2, DD_MM_YYYY);
			schoolInfo.put("drawDate2", d2.getTime()/1000);
		}
		if(!SEOURLUtils.chkNull(drawDate3)){
			Date d3=convertStringToDate(drawDate3, DD_MM_YYYY);
			schoolInfo.put("drawDate3", d3.getTime()/1000);
		}
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		if(!SEOURLUtils.chkNull(state)&&!SEOURLUtils.chkNull(city)){
			DBObject location = (DBObject)schoolInfo.get("location");
			location.put("province", state);
			location.put("city",city);
			schoolInfo.put("location", location);
		}
		String meta_keywords = request.getParameter("meta_keywords");
		String meta_description = request.getParameter("meta_description");
		String robots = request.getParameter("robots");
		schoolInfo.put("meta_keywords", meta_keywords);
		schoolInfo.put("meta_description", meta_description);
		schoolInfo.put("robots", robots);
		//admission form last date
		String afld = request.getParameter("afld");
		if(!SEOURLUtils.chkNull(afld)){
			Date d4=convertStringToDate(afld, DD_MM_YYYY);
			schoolInfo.put("afld", d4.getTime()/1000);
		}
		if(!SEOURLUtils.chkNull(lat)&&!SEOURLUtils.chkNull(lng)){
			DBObject loc = (DBObject)schoolInfo.get("loc");
			if(loc==null){
				loc=new BasicDBObject();
			}
			loc.put("type", "Point");
			loc.put("coordinates", new double[] { Double.valueOf(lng), Double.valueOf(lat) });
			schoolInfo.put("loc", loc);
		}
		schoolInfo.put("changed", time);
		mongoDao.updateSchoolInformation(sid, schoolInfo);
		String url=(String)schoolInfo.get("alias");
		String redirectedUrl="redirect:"+WebConstants.APPLICATION_URL+url;
		view.setViewName(redirectedUrl);
		return view;

	}
	private void updateImages(SchoolInformation schoolInformation,
			SchoolBean schoolsImages, Integer sid, DBObject schoolInfo) {
		for (MultipartFile mfile : schoolInformation.getImageGallery()) {
			System.out.println(mfile.getOriginalFilename());
		}
		String fileUploadPath=WebConstants.schoolImageDirectory+"/"+sid+"/";
		File file = new File(fileUploadPath);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
				fileUploadPath=WebConstants.schoolImageDirectory+"/";
			}
		}
		MultipartFile logoImageFile = schoolInformation.getLogoFile();
		if (logoImageFile != null && !logoImageFile.isEmpty()) {
			String filePath = fileUploadPath+logoImageFile.getOriginalFilename();
			uploadFile(logoImageFile, filePath);
			schoolsImages.setLogoPath(filePath.substring(filePath.indexOf("images")));
			System.out.println(filePath);
		}
		MultipartFile backGroundImage = schoolInformation.getBackgroundImage();
		if (backGroundImage != null && !backGroundImage.isEmpty()) {
			String filePath = fileUploadPath+backGroundImage.getOriginalFilename();
			uploadFile(backGroundImage, filePath);
			schoolsImages.setBackGroundImagePath(filePath.substring(filePath.indexOf("images")));
			System.out.println(filePath);
		}
		MultipartFile[] imageGallery = schoolInformation.getImageGallery();
		List<SchoolPhotoVideoBean> list = new ArrayList<SchoolPhotoVideoBean>();
		if (imageGallery != null) {
			for (MultipartFile mFile : imageGallery) {
				if (mFile != null && !mFile.isEmpty()) {
					String filePath = fileUploadPath
							+ mFile.getOriginalFilename();
					SchoolPhotoVideoBean bean = new SchoolPhotoVideoBean();
					bean.setMediaUrl(filePath.substring(filePath.indexOf("images")));
					list.add(bean);
					System.out.println(filePath);
					uploadFile(mFile, filePath);
				}
			}
			schoolsImages.setPhotoVedioBean(list);
		}
		schoolInfo.put("schoolsImages", schoolsImages);
	}
	public static Date convertStringToDate(String date, String inputDateFormat){
        try{
        	return new SimpleDateFormat(inputDateFormat).parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }
		return null;
	}
	private void uploadFile(MultipartFile imageFile, String filePath) {
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(new File(filePath));
			FileCopyUtils.copy(imageFile.getInputStream(), outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			// System.out.println("not written");
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/get_all_application.html")
	public ModelAndView getAllApplication(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAndView view = new ModelAndView("allAdmissionQuery");
		DBObject query = new BasicDBObject();
		DBCursor dbCursor=mongoDao.getContentByCollectionName(query,ADMISSION_APPLICATION_COLLECTION);
		List<DBObject> contents=new ArrayList<DBObject>();
		for (DBObject dbObject : dbCursor) {
			contents.add(dbObject);
		}
		dbCursor.close();
		model.put("contents", contents);
		view.addAllObjects(model);
		return view;

	}

}
