package com.spedia.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.spedia.model.Subscription;
import com.spedia.utils.SEOURLUtils;
import com.spedia.utils.SocialUtility;
@Repository("pushNotificationSubscriptionDao")
public class PushNotificationSubscriptionDaoImpl implements
PushNotificationSubscriptionDao {
	public static final String SUBSCRIBE_OPERATION = "sub";
	public static final String SUBSCRIBE_OPERATION_GET = "get";
	public static final String UNSUBSCRIBE_OPERATION = "unsub";
	public static final String DISABLE_SUBSCRIBE_OPERATION = "disable";
	public static final String QuotaExceeded="QuotaExceeded" ;//Too many messages sent by the sender. Retry after a while.
	public static final String DeviceQuotaExceeded="DeviceQuotaExceeded" ;// Too many messages sent by the sender to a specific device. Retry after a while.
	public static final String MissingRegistration="MissingRegistration" ;// Missing registration_id. Sender should always add the registration_id to the request.
	public static final String InvalidRegistration="InvalidRegistration" ;// Bad registration_id. Sender should remove this registration_id.
	public static final String MismatchSenderId="MismatchSenderId" ;// The sender_id contained in the registration_id does not match the sender id used to register with the C2DM servers.
	public static final String NotRegistered="NotRegistered" ;// The user has uninstalled the application or turned off notifications. Sender should stop sending messages to this device and delete the registration_id. The client needs to re-register with the c2dm servers to receive notifications again.
	public static final String MessageTooBig="MessageTooBig" ;// The payload of the message is too big, see the limitations. Reduce the size of the message.
	public static final String MissingCollapseKey="MissingCollapseKey" ;// Collapse key is required. Include collapse key in the request.
	public final static String INVALID_SENDER_ID_APPLE = "push notification:invalid sender id Apple";
	public final static String APPLE_CLOUD_DOWN = "push notification:apple cloud down";
	public final static String INVALID_SENDER_ID_ANDROID = "push notification:invalid sender id Android";
	public final static String INVALID_SENDER_TOKEN_ID_ANDROID = "push notification:invalid sender token id Android";
	public final static String ANDROID_CLOUD_DOWN = "push notification:Android cloud down";
	public final static String ANDROID_QUOTA_EXCEEDED = "push notification:Android  Quota Exceeded";
	
	public static final String collectionName="push_notification_subscription";
	@Autowired
	MongoTemplate mongoTemplate;
	
	/**
	 * Subscribes a user 
	 */
	public String subscribe(Subscription subscription,String operation){
		boolean success = false;
		String di = subscription.getDi();
		Query query = new Query();
		query.addCriteria(Criteria.where("di").is(di));
		if(operation.equals(SUBSCRIBE_OPERATION)){
			Subscription sub = getSubscriptionOfDevice(di);
			//checking whether the user is already subscribed or not
			if(sub == null){
				//int counter = subscriptionCounterDao.getCurrentSubscriptionCounter();
				//Not subscribed, subscribe the user
				subscription.setE("y");
				subscription.setUt(SEOURLUtils.getCurrentTime());
				subscription.setCt(SEOURLUtils.getCurrentTime());
				//subscription.setC(counter);
				mongoTemplate.save(subscription,collectionName);
				success = true;
			}else{
				String enabled = "y";
				Update update = getUpdateStatement(enabled,subscription);
				mongoTemplate.upsert(query, update, collectionName);
				success = true;
			}
		}else if(operation.equals(UNSUBSCRIBE_OPERATION)){
			String enabled = "U";
			Update update = new Update();
			update.set("e",enabled);
			update.set("ut", SEOURLUtils.getCurrentTime());
			mongoTemplate.upsert(query, update, collectionName);
			
			success = true;
		}else if(operation.equals(InvalidRegistration)){
			String enabled = "IV";
			Update update = new Update();
			update.set("e",enabled);
			update.set("ut", SEOURLUtils.getCurrentTime());
			mongoTemplate.upsert(query, update, collectionName);
			success = true;
		}else if(operation.equals(NotRegistered)){
			String enabled = "N";
			Update update = new Update();
			update.set("e",enabled);
			update.set("ut", SEOURLUtils.getCurrentTime());
			mongoTemplate.upsert(query, update, collectionName);
			success = true;
		}
		
		return success + "";
	}
	
	/**
	 * Gets the Update Statement
	 */
	private Update getUpdateStatement(String enabled,Subscription subscription) {
		Update update = new Update();
		update.set("e",enabled);
		String loginId = subscription.getLi();
		if(!SocialUtility.chkNull(loginId)) {
			update.set("li", loginId);
		}
		String registrationId = subscription.getRi();
		if(!SocialUtility.chkNull(registrationId)) {
			update.set("ri", registrationId);
		}
		update.set("ut", SEOURLUtils.getCurrentTime());
		return update;
	}
	
	public Subscription getSubscriptionOfDevice(String deviceId){
		Query query = new Query();
		query.addCriteria(Criteria.where("di").is(deviceId));
		return mongoTemplate.findOne(query, Subscription.class,collectionName);
	}


}
