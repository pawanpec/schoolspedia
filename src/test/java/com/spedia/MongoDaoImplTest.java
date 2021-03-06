package com.spedia;

import junit.framework.TestCase;

import com.mongodb.DBObject;
import com.spedia.dao.MongoDao;
import com.spedia.utils.SEOURLUtils;

public class MongoDaoImplTest extends TestCase {

	/*
	 * public void testUpdateSchoolDetails() { MongoDao mongoDao = (MongoDao)
	 * BaseSpringTest.getInstance().getBean( "mongoDao"); DBCollection node =
	 * mongoDao.getMongoDatabase().getCollection( "fields_current.node");
	 * assertNotNull(mongoDao); int nid = 220; DBObject dbObject =
	 * mongoDao.getContentByNid(nid); SchoolBean schoolBean = new SchoolBean();
	 * List<SchoolSubSection> schoolSubSections = new
	 * ArrayList<SchoolSubSection>(); schoolBean.setBackGroundImagePath("BG");
	 * schoolBean.setLogoPath("logoPath"); List<SchoolPhotoVideoBean>
	 * photoVedioBean = new ArrayList<SchoolPhotoVideoBean>();
	 * SchoolPhotoVideoBean schoolPhotoVideoBean = new SchoolPhotoVideoBean();
	 * schoolPhotoVideoBean.setMediaText("text");
	 * schoolPhotoVideoBean.setMediaType(1);
	 * schoolPhotoVideoBean.setMediaUrl(" Vedio URL");
	 * photoVedioBean.add(schoolPhotoVideoBean); SchoolPhotoVideoBean
	 * schoolPhotoBean = new SchoolPhotoVideoBean();
	 * schoolPhotoBean.setMediaText("image text");
	 * schoolPhotoBean.setMediaType(0);
	 * schoolPhotoBean.setMediaUrl("image URL");
	 * photoVedioBean.add(schoolPhotoBean);
	 * schoolBean.setPhotoVedioBean(photoVedioBean); //
	 * mongoDao.getUserProfileByLoginID("smita_staging39993", "tj"); Gson gson =
	 * new Gson(); dbObject.put("schoolBean", schoolBean); SchoolSubSection
	 * schoolSubSection = new SchoolSubSection();
	 * schoolSubSection.setTitle("About School");
	 * schoolSubSection.setText("This is about school");
	 * schoolSubSection.setImage("imagePath");
	 * schoolSubSections.add(schoolSubSection);
	 * schoolBean.setSchoolSubSection(schoolSubSections); dbObject.put("uid",
	 * 123); mongoDao.updateSchoolInformation(nid, dbObject);
	 * 
	 * }
	 */

	/*
	 * public void testGetSchoolDetails(){ MongoDao mongoDao=(MongoDao)
	 * BaseSpringTest.getInstance().getBean("mongoDao"); DBCollection node =
	 * mongoDao.getMongoDatabase().getCollection( "fields_current.node");
	 * assertNotNull(mongoDao); String
	 * url="website/bal-bharti-pub-school-sector-14-rohini-delhi"; DBObject
	 * dbObject=mongoDao.getContentByURL(url); BasicDBObject basicDBObject=
	 * (BasicDBObject) dbObject.get("schoolBean"); Gson gson=new Gson();
	 * SchoolBean schoolBean= gson.fromJson(basicDBObject.toString(),
	 * SchoolBean.class);
	 * System.out.println(schoolBean.getBackGroundImagePath()); }
	 */
	/*
	 * public void testCreateUser() { IUserService userService = (IUserService)
	 * BaseSpringTest.getInstance() .getBean("userService");
	 * assertNotNull("UserService is null.", userService); User user=new User();
	 * user.setUsername("admin"); String pass="123456";
	 * user.setPassword(SocialUtility.getMD5(pass));
	 * user.setMail("admin@gmail.com"); user.setEnabled(true); Set<UserRole>
	 * userRoleses=new HashSet<UserRole>(); UserRole userRole=new UserRole();
	 * userRole.setUser(user); userRole.setRole("ROLE_ADMIN");
	 * userRoleses.add(userRole); user.setUserRoleses(userRoleses); Long
	 * created=System.currentTimeMillis(); user.setCreated(created.intValue());
	 * user.setUpdated(created.intValue()); userService.registerUser(user); }
	 */
	/*
	 * public void testGetUser() { IUserService userService = (IUserService)
	 * BaseSpringTest.getInstance() .getBean("userService"); User user=new
	 * User(); user.setUid(4); user=userService.getUser(user); Set<UserRole>
	 * userRoleses = user.getUserRoleses(); for (UserRole userRole :
	 * userRoleses) { System.out.println(userRole.getRole()); }
	 * 
	 * }
	 */
	/*
	 * public void testGetSchoolDetails(){ MongoDao mongoDao=(MongoDao)
	 * BaseSpringTest.getInstance().getBean("mongoDao"); DBCollection node =
	 * mongoDao.getMongoDatabase().getCollection( "fields_current.node");
	 * assertNotNull(mongoDao); String
	 * url="website/bal-bharti-pub-school-sector-14-rohini-delhi"; DBObject
	 * dbObject=mongoDao.getContentByURL(url); BasicDBObject basicDBObject=
	 * (BasicDBObject) dbObject.get("schoolBean"); Gson gson=new Gson();
	 * SchoolBean schoolBean= gson.fromJson(basicDBObject.toString(),
	 * SchoolBean.class);
	 * System.out.println(schoolBean.getBackGroundImagePath()); }
	 */
	/*
	 * public void testsaveUserFbData(){ MongoDao mongoDao=(MongoDao)
	 * BaseSpringTest.getInstance().getBean("mongoDao"); String
	 * data="{'name':'mkyong', 'age':30}"; WriteResult
	 * writeResult=mongoDao.saveUserFbData(data);
	 * System.out.println(writeResult.getLastError()); }
	 */
	/*
	 * public void testTopSchool() { MongoDao mongoDao = (MongoDao)
	 * BaseSpringTest.getInstance().getBean( "mongoDao"); List<DBObject>
	 * topReviewedSchool = mongoDao.getTopReviewedSchool(); for (DBObject
	 * dbObject : topReviewedSchool) {
	 * System.out.println(dbObject.get("title"));
	 * System.out.println(dbObject.get("review")); } }
	 */
	/*
	 * public void testLatestContent() { MongoDao mongoDao = (MongoDao)
	 * BaseSpringTest.getInstance().getBean( "mongoDao"); DBObject query = new
	 * BasicDBObject(); DBObject clause1 = new BasicDBObject("type",
	 * "nursery_admission"); DBObject clause2 = new BasicDBObject("type",
	 * "nursery_admission_news"); DBObject clause3 = new BasicDBObject("type",
	 * "summer_camp"); DBObject clause4 = new BasicDBObject("type",
	 * "schools_news"); DBObject clause5 = new BasicDBObject("type",
	 * "parents_tips"); BasicDBList or = new BasicDBList(); or.add(clause1);
	 * or.add(clause2); or.add(clause3); or.add(clause4); or.add(clause5); query
	 * = new BasicDBObject("$or", or); DBCursor
	 * dbCursor=mongoDao.getContent(query).limit(10); for (DBObject dbObject :
	 * dbCursor) { System.out.println("title "+dbObject.get("title")); }
	 * 
	 * }
	 */
	/*public void testGetContentByDistance() {
		MongoDao mongoDao = (MongoDao) BaseSpringTest.getInstance().getBean(
				"mongoDao");
		BasicDBObject filter = new BasicDBObject();
		filter.put("type", "Point");
		filter.put("coordinates", new double[] { 77.1139979999999952, 28.7166290000000011 });
		BasicDBObject geometry=new BasicDBObject("$geometry",filter);
		geometry.put("$maxDistance", 1000);
		BasicDBObject near=new BasicDBObject("$near",geometry);
		BasicDBObject query = new BasicDBObject("loc", near);
		System.out.println(query);
		DBCursor dbCursor = mongoDao.getContent(query);
		System.out.println(dbCursor.size());
	}*/
	public void testUpdateLocation() {
		MongoDao mongoDao = (MongoDao) BaseSpringTest.getInstance().getBean(
				"mongoDao");
		Integer sid = 3280;
		DBObject schoolInfo = mongoDao.getContentByNid(sid);
		String lng = "78.2139979999999952";
		String lat = "29.8166290000000011";
		if(!SEOURLUtils.chkNull(lat)&&!SEOURLUtils.chkNull(lng)){
			DBObject loc = (DBObject)schoolInfo.get("loc");
			loc.put("type", "Point");
			loc.put("coordinates", new double[] { Double.valueOf(lng), Double.valueOf(lat) });
		}
		
		mongoDao.updateSchoolInformation(sid, schoolInfo);
	}
}
