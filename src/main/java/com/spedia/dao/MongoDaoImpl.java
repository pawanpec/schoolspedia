package com.spedia.dao;

import static com.spedia.utils.MongoConstants.FIELDS_CURRENT_NODE;
import static com.spedia.utils.MongoConstants.MONGO_DB_NAME;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.spedia.model.Connection;
import com.spedia.model.Profile;
import com.spedia.model.SchoolBean;
import com.spedia.model.User;
import com.spedia.service.FeedProcessService;
import com.spedia.utils.Constants;
import com.spedia.utils.MongoConstants;
import com.spedia.utils.SocialUtility;

public class MongoDaoImpl implements MongoDao,ApplicationContextAware {
	private static final String WEB = "web";
	private static final String SUBSCRIPTION_COLLECTION = "subscription";
	private static final String locationSearch = "locationSearch";
	ApplicationContext applicationContext = null;
	/**
	 * This will be called when the bean will get initialised, this will ensure
	 * that the index on the fields are properly created
	 */
	public void init() {
		System.out.println("ensureIndex called ");
	//	ensureIndex();
		ContentDao contentDao= (ContentDao) applicationContext.getBean("contentDao");
		TimerTask task = new FeedProcessService(contentDao);
    	Timer timer = new Timer();
    	timer.schedule(task, 1000,6000000);
	}

	private Mongo mongo;
	public DB getMongoDatabase() {
		return mongo.getDB(MONGO_DB_NAME);
	}
	public DB getMongoDatabase(String dbName) {
		return mongo.getDB(dbName);
	}
	@Override
	public DBObject getContentByURL(String url) {
		DBCollection node = getMongoDatabase().getCollection(
				FIELDS_CURRENT_NODE);
		DBObject query=new BasicDBObject("alias", url);
		query.put("status", 1);
		DBObject content=node.findOne(query);
		if(content==null){
			query= new BasicDBObject("url", url); 
			content=node.findOne(query);
			content.put("redirectToAlias", true);
		}
		
		return content;
	}
	public Mongo getMongo() {
		return mongo;
	}
	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}
	@Override
	public DBObject getContentByNid(Integer nid) {
		DBCollection node = getMongoDatabase().getCollection(
				FIELDS_CURRENT_NODE);
		DBObject cond = new BasicDBObject();
		cond.put("_id", nid);
		//cond.put("status", 1);
		return node.findOne(cond);
	}
	@Override
	public DBCursor getContent(DBObject basicDBObject) {
		DBCollection node = getMongoDatabase().getCollection(
				FIELDS_CURRENT_NODE);
		String sortOrder=(String)basicDBObject.get("sortOrderBy");
		DBObject orderBy=new BasicDBObject();
		if(!SocialUtility.chkNull(sortOrder)){
			Integer order=-1;
			if(basicDBObject.containsField("order")){
				order=(Integer)basicDBObject.get("order");
				basicDBObject.removeField("order");
			}
			orderBy.put(sortOrder,order);
			basicDBObject.removeField("sortOrderBy");
		}else{
			orderBy.put("changed", -1);
		}
		if(basicDBObject.containsField("status")){
			basicDBObject.put("status", basicDBObject.get("status"));
		}else{
			basicDBObject.put("status", 1);
		}
		
		return node.find(basicDBObject).sort(orderBy);
	}
	@Override
	public List<DBObject> getTopReviewedSchool() {
		DBCollection node = getMongoDatabase().getCollection(
				FIELDS_CURRENT_NODE);
		DBObject cond = new BasicDBObject();
		cond.put("type", "group");
		cond.put("review.count", new BasicDBObject("$gt",2));
		cond.put("status", 1);
		DBObject orderBy=new BasicDBObject("review.count",-1);
		DBCursor cursor=node.find(cond).sort(orderBy).limit(4);
		return cursor.toArray();
		
	}

	/** Get user profile by profile id and app-code **/
	@Override
	public DBObject getUserProfileByProfileID(String profileId) {
		DBCollection col = getMongoDatabase().getCollection(
				MongoConstants.MONGO_DB_USER_PROFILE_COLLECTION);

		DBObject cond = new BasicDBObject();
		cond.put(MongoConstants.MONGO_COLLECTION_PROFILEID, profileId);
		DBObject returnObject = col.findOne(cond);
		return returnObject;
	}
	@Override
	public DBObject getConnection(String pid) {
		DBCollection col = getMongoDatabase().getCollection(
				MongoConstants.MONGO_DB_USER_CONNECTION_COLLECTION);
		DBObject cond = new BasicDBObject();
		cond.put(MongoConstants.MONGO_COLLECTION_PROFILEID, pid);
		DBObject fields = new BasicDBObject();
		fields.put(MongoConstants.MONGO_COLLECTION_PARENT_PROFILEID, true);
		fields.put(MongoConstants.MONGO_COLLECTION_PARENT_LOGINID, true);
		fields.put(MongoConstants.MONGO_COLLECTION_APP_CODE, true);
		fields.put(MongoConstants.MONGO_COLLECTION_TIMESTAMP, true);
		fields.put(MongoConstants.MONGO_COLLECTION_UPDATED_TIMESTAMP, true);

		DBObject returnObject = col.findOne(cond, fields);
		return returnObject;
	}
	@Override
	public WriteResult updateOverAllRating(DBObject node) {
		DBCollection nodeCollection = getMongoDatabase().getCollection(
				FIELDS_CURRENT_NODE);
		BasicDBObject nodeQuery = new BasicDBObject();
		nodeQuery.put("_id", node.get("_id"));
		WriteResult c = nodeCollection.update(nodeQuery, node);
		return c;
	}
	@Override
	public WriteResult follow(Integer nid,User user,Boolean status) {
		DBCollection nodeCollection = getMongoDatabase().getCollection(
				FIELDS_CURRENT_NODE);
		BasicDBObject nodeQuery = new BasicDBObject();
		nodeQuery.put("_id", nid);
		BasicDBObject followObject = new BasicDBObject();
		if (status) {
			followObject.put("$addToSet", new BasicDBObject("f", user.getUid()));
		}else{
			followObject.put("$pull", new BasicDBObject("f", user.getUid()));
		}
		return nodeCollection.update(nodeQuery,followObject, true,
				false);
	}
	@Override
	public WriteResult saveUserFbData(String jsonData) {
		DBCollection col = getMongoDatabase().getCollection(
				MongoConstants.MONGO_DB_USER_PROFILE_COLLECTION);
		DBObject dbObject = (DBObject) JSON.parse(jsonData);
		String fbId=(String)dbObject.get("id");
		DBObject q=new BasicDBObject("id",fbId);
		return col.update(q, dbObject,true,false);
	}
	@Override
	public WriteResult saveFBGroupData(String jsonData) {
		DBCollection col = getMongoDatabase().getCollection(
				MongoConstants.MONGO_DB_FB_GROUP_COLLECTION);
		/*BasicDBObject basicDBObject=new BasicDBObject();
		basicDBObject.put("data", jsonData);*/
		//Gson gson=new Gson();
		/*Gson gson = new GsonBuilder().registerTypeAdapter(DBObject.class, new InterfaceAdapter<DBObject>()).create();
		DBObject dbObject = gson.fromJson(jsonData, DBObject.class);*/
		
		DBObject dbObject = null;
		try {
			dbObject = (BasicDBObject) JSON.parse(jsonData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dbObject=new BasicDBObject();
			dbObject.put("data", jsonData);
		}
		col.save(dbObject);
		return null;
	}
	@Override
	public String updateSchoolInformation(Integer nid, DBObject schoolData) {
		DBCollection nodeCollection = getMongoDatabase().getCollection(
				FIELDS_CURRENT_NODE);
		BasicDBObject nodeQuery = new BasicDBObject();
		nodeQuery.put("_id", nid);
		WriteResult c = nodeCollection.update(nodeQuery, schoolData,true,false);
		return c.toString();
	}
	@Override
	public String saveSubscription(String email, String op) {
		DBCollection subscription = getMongoDatabase().getCollection(
				SUBSCRIPTION_COLLECTION);
		DBObject dbObject = new BasicDBObject();
		dbObject.put("e", email);
		dbObject.put("s", op);
		dbObject.put("src", WEB);
		boolean upsert=true;
		boolean multi=false;
		DBObject q=new BasicDBObject();
		q.put("e", email);
		WriteResult c=subscription.update(q, dbObject, upsert, multi);
		return "1";
	}
	@Override
	public String saveLocationSearchData(DBObject dbObject) {
		DBCollection locationSearchCollection = getMongoDatabase().getCollection(
				locationSearch);
		boolean upsert=true;
		boolean multi=false;
		DBObject q=new BasicDBObject();
		q.put("alias", dbObject.get("alias"));
		WriteResult c=locationSearchCollection.update(q, dbObject, upsert, multi);
		return "1";
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
			System.out.println("setting context");
	        this.applicationContext = applicationContext;
	}
	@Override
	public DBCursor getLocationSearchData(DBObject dbObject) {
		DBCollection locationSearchCollection = getMongoDatabase().getCollection(
				locationSearch);
		DBObject orderBy=new BasicDBObject("_id",-1);
		return locationSearchCollection.find(dbObject).sort(orderBy);
	}
	@Override
	public WriteResult saveRequest(DBObject dbObject) {
		String cl=(String)dbObject.get("cl");
		DBCollection clCollection = getMongoDatabase().getCollection(
				cl);
		dbObject.removeField("cl");
		return clCollection.save(dbObject);
	}
	@Override
	public DBCursor getContentByCollectionName(DBObject query,String collectionName) {
		DBCollection node = getMongoDatabase().getCollection(
				collectionName);
		DBObject orderBy=new BasicDBObject("_id",-1);
		return node.find(query).sort(orderBy);
	}
}
