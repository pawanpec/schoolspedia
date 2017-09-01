package com.spedia;

import junit.framework.TestCase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.spedia.dao.MongoDao;
import com.spedia.service.CounterService;
import com.spedia.utils.SEOURLUtils;
import com.spedia.utils.SocialUtility;

public class SchoolAddService extends TestCase {

	public void testAddSchool() {
		MongoDao mongoDao = (MongoDao) BaseSpringTest.getInstance().getBean(
				"mongoDao");
		CounterService counterService = (CounterService) BaseSpringTest
				.getInstance().getBean("counterService");
		DBObject schoolInfo = new BasicDBObject();
		DBObject query = new BasicDBObject();
		//query.put("S.No.",1);
		DBCursor dbCursor = mongoDao.getContentByCollectionName(query,
				"sp_1500");
		for (DBObject dsObject : dbCursor) {
			try {
				String sp = (String) dsObject.get("about us");
				Integer id = (Integer) dsObject.get("Schid");
			//	 query.put("schoolCode", id);
			//	DBCursor schoolExist=mongoDao.getContent(query);
			//	System.out.println(schoolExist.size());
				if (sp!=null&&"no".equals(sp)) {
					System.out.println("adding school "+id);
					Long time = SEOURLUtils.getCurrentTime();
					Integer sid = counterService.getNextSequence("_id");
					System.out.println(id);
					System.out.println(sid);
					schoolInfo.put("_id", sid);
					schoolInfo.put("nid", sid);
					schoolInfo.put("type", "group");
					schoolInfo.put("src", "bulk_1500");
					String title = (String) dsObject.get("title");
					if (!SocialUtility.chkNull(title)) {
						String seourl = SEOURLUtils.getSEOURL("website", title);
						System.out.println(seourl);
						schoolInfo.put("alias", seourl);
						schoolInfo.put("title", title);
					}
					schoolInfo.put("created", time);
					schoolInfo.put("changed", time);
					Double lng = (Double) dsObject.get("long");
					Double lat = (Double) dsObject.get("lat");
					DBObject loc = new BasicDBObject();
					loc.put("type", "Point");
					loc.put("coordinates", new double[] { lng, lat });
					schoolInfo.put("loc", loc);
					String city = (String) dsObject.get("city");
					String district = (String) dsObject.get("District");
					String street = (String) dsObject.get("Address");
					String schoolName = (String) dsObject.get("School Name");
					DBObject location = new BasicDBObject();
					location.put("_id", sid);
					location.put("province", "delhi");
					location.put("city", city);
					location.put("district", district);
					location.put("street", street);
					schoolInfo.put("location", location);
					String email = (String) dsObject.get("email");
					DBObject sd = new BasicDBObject();
					Object number=dsObject.get("contact number");
					if(number instanceof Long){
						Long contactNumber = (Long) dsObject.get("contact number");
						sd.put("PHONE_NO", contactNumber);
					}
					
					String website = (String) dsObject.get("website");
					sd.put("E", email);
					sd.put("Id", id);
					sd.put("W", website);
					sd.put("PA", street);
					sd.put("SN", schoolName);
					Integer PS_SEATS = (Integer) dsObject.get("PS");
					Integer PP_SEATS = (Integer) dsObject.get("PP");
					Integer class1 = (Integer) dsObject.get("class1");
					schoolInfo.put("PS_SEATS", PS_SEATS);
					schoolInfo.put("PP_SEATS", PP_SEATS);
					schoolInfo.put("class1", class1);
					schoolInfo.put("sd", sd);
					schoolInfo.put("schoolCode", id);
					schoolInfo.put("status", 1);
					mongoDao.updateSchoolInformation(sid, schoolInfo);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

}
