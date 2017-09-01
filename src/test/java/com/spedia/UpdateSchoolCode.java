package com.spedia;

import junit.framework.TestCase;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class UpdateSchoolCode extends TestCase {
	public static final String mongoHost = "127.0.0.1";// for qc
	public static Mongo mongo;
	public static DBCollection node;
	public static DBCollection school_code;
	public static DB db;
	public static DBCollection result;
	static{
		
		try {
			mongo = new Mongo(mongoHost, 27017);
			db = mongo.getDB("spedia");
			node = db.getCollection("fields_current.node");
			school_code = db.getCollection("school_code");
			result = db.getCollection("result");
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*public void testSchoolCode() {
		DBObject q=new BasicDBObject();
		DBCursor dbuCursor = school_code.find(q);
		//DBCursor dbuCursor=getSchool();
		int i=0;
		while (dbuCursor.hasNext()) {
			DBObject nodeObject = dbuCursor.next();
			if (nodeObject != null) {
				try {
					Integer nid = (Integer)nodeObject.get("nid");
					Object o=nodeObject.get("code");
					String schoolCode=null;
					if(o instanceof String){
						schoolCode = (String)nodeObject.get("code");
						schoolCode=schoolCode.substring(2);
					}
					if(o instanceof Integer){
						schoolCode = (Integer)nodeObject.get("code")+"";
					}
					if(o instanceof Long){
						schoolCode = (Long)nodeObject.get("code")+"";
					}
					DBObject query=new BasicDBObject("_id",nid);
					DBObject school=node.findOne(query);
					if(school!=null){
						System.out.println(schoolCode);
						school.put("schoolCode",schoolCode);
						node.update(query, school);
						i++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("total "+i);
	}*/
	public void testSchoolResult() {
		DBObject q=new BasicDBObject("School Code", 8356);
		DBCursor dbuCursor = result.find();
		//DBCursor dbuCursor=getSchool();
		int i=0;
		while (dbuCursor.hasNext()) {
			DBObject nodeObject = dbuCursor.next();
			if (nodeObject != null) {
				System.out.println(nodeObject);
				try {
					Integer School_Code = (Integer)nodeObject.get("School Code");
					Integer noc = (Integer)nodeObject.get("Number of Candidates");
					Double aa = (Double)nodeObject.get("Average Aggregate");
					String School_Name = (String)nodeObject.get("School Name");
					DBObject query = new BasicDBObject();
					DBObject clause1 = new BasicDBObject("schoolCode", School_Code+"");
					DBObject clause2 = new BasicDBObject("sd.SN", School_Name);
					BasicDBList or = new BasicDBList();
					or.add(clause1);
					or.add(clause2);
					query = new BasicDBObject("$or", or);
					DBObject school=node.findOne(query);
					DBObject result=new BasicDBObject();
					result.put("noc",noc );
					result.put("aa",aa );
					if(school!=null){
						i++;
						school.put("result", result);
						Integer _id=(Integer)school.get("_id");
						System.out.println(_id);
						DBObject q1=new BasicDBObject("_id",_id);
						node.update(q1, school);
					}
					System.out.println(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//node.update(query, school);
			}
		}
		System.out.println("total "+i);
	}
	
}
