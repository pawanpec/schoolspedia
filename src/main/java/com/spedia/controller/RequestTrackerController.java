/**
 * 
 */
package com.spedia.controller;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.spedia.dao.MongoDao;
import com.spedia.utils.SEOURLUtils;
import com.spedia.utils.SocialUtility;

/**
 * @author root
 *
 *
 */
@Controller
public class RequestTrackerController  extends MultiActionController {
	@Autowired
	@Qualifier("mongoDao")
	private MongoDao mongoDao;
	@RequestMapping(value = { "/saveRequest.json" }, method = { RequestMethod.POST })
	public ModelAndView saveRequest(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String resp="1";
		BasicDBObject basicDBObject = new BasicDBObject();
		Map<String, ?> reqData=request.getParameterMap();
		Set<String> keys=reqData.keySet();
		for (String key : keys) {
			Object obj=reqData.get(key);
			String value = ((String[]) obj)[ 0 ];
			basicDBObject.put(key, value);
		}
		basicDBObject.put("ct", SEOURLUtils.getCurrentTime());
		basicDBObject.put("ut", SEOURLUtils.getCurrentTime());
		basicDBObject.put("cl", "admission_application");
		WriteResult wr=mongoDao.saveRequest(basicDBObject);
		SocialUtility.setResultInResponse(response,resp);
		return null;
	}

}
