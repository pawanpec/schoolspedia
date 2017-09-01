package com.spedia.dao;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.spedia.model.Connection;
import com.spedia.model.Profile;
import com.spedia.model.SchoolBean;
import com.spedia.model.User;




public interface MongoDao {
	public DBObject getUserProfileByProfileID(String profileId);
	public DBObject getConnection(String pid) ;
	DBObject getContentByURL(String url);
	DBObject getContentByNid(Integer nid);
	DBCursor getContent(DBObject basicDBObject);
	DBCursor getContentByCollectionName(DBObject query,String collectionName);
	public WriteResult updateOverAllRating(DBObject review) ;
	public DB getMongoDatabase();
	public DB getMongoDatabase(String dbName) ;
	public WriteResult follow(Integer nid,User user,Boolean status) ;
	public WriteResult saveUserFbData(String jsonData) ;
	List<DBObject> getTopReviewedSchool();
	WriteResult saveFBGroupData(String jsonData);
	public String updateSchoolInformation(Integer nid,DBObject schoolInfo) ;
	public String saveSubscription(String email,String op) ;
	public String saveLocationSearchData(DBObject dbObject) ;
	public DBCursor getLocationSearchData(DBObject dbObject) ;
	WriteResult saveRequest(DBObject dbObject);
}
