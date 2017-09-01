package com.spedia.dao;

import java.util.List;

import com.spedia.model.Subscription;



/**
 * Data Access Object for all the subscription related queries
 */
public interface PushNotificationSubscriptionDao {
	
	/**
	 * Subscribes a user
	 */
	public String subscribe(Subscription subscription,String operation);
	

}
