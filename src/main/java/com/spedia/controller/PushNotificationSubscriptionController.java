package com.spedia.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spedia.dao.PushNotificationSubscriptionDao;
import com.spedia.model.Subscription;
/**
 * Controller for Subscription request
 *
 */
@Controller
public class PushNotificationSubscriptionController {
	@Autowired
	@Qualifier("pushNotificationSubscriptionDao")
	private PushNotificationSubscriptionDao pushNotificationSubscriptionDao;
	private static final Log log = LogFactory.getLog(PushNotificationSubscriptionController.class);
	@RequestMapping(value = { "subscription.api" })
	public @ResponseBody String subscribeUser(HttpServletRequest req,
			HttpServletResponse resp
			) {
		//It means some operation is done for the Subscription
		String loginId = req.getParameter("li");
		String operation= req.getParameter("op");
		String deviceId = req.getParameter("di");
		String deviceType = req.getParameter("dt");
		String registrationId = req.getParameter("ri");
		String dn = req.getParameter("dn");
		String rt = req.getParameter("rt");
		String prjId=req.getParameter("projId");
		Subscription subscription = new Subscription();
		subscription.setLi(loginId);
		subscription.setDi(deviceId);
		subscription.setDt(deviceType);
		subscription.setRi(registrationId);
		subscription.setDn(dn);
		String operationMessage = pushNotificationSubscriptionDao.subscribe(subscription,operation);
		return operationMessage;
	}
	
	


}
