package com.spedia.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.spedia.model.Content;
import com.spedia.service.CounterService;
import com.spedia.utils.MongoConstants;
import com.spedia.utils.SocialUtility;
@Repository("contentDao")
public class ContentDaoImpl implements ContentDao {
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	CounterService counterService;
	@Override
	public void saveContent(Content content) {
			Query query = new Query();
			query.addCriteria(Criteria.where("alias").is(content.getAlias()));
			Content content1=mongoTemplate.findOne(query, Content.class,  MongoConstants.FIELDS_CURRENT_NODE);
			if(content1==null){
				content.set_id(counterService.getNextSequence("_id"));
				mongoTemplate.save(content, MongoConstants.FIELDS_CURRENT_NODE);
			}else{
				if(SocialUtility.chkNull(content.getRid())){
					content1.setChanged(content.getChanged());
				}else{
					//do not update feed content.
					return;
				}
				content1.setTitle(content.getTitle());
				content1.setUpdatedBy(content.getUpdatedBy());
				content1.setBody(content.getBody());
				content1.setMeta_description(content.getMeta_description());
				content1.setMeta_keywords(content.getMeta_keywords());
				content1.setRobots(content.getRobots());
				String imagePath=content.getImagePath();
				String uid=content1.getUid();
				
				if(SocialUtility.chkNull(uid)){
					content1.setUid(content.getUid());
				}
				if(!SocialUtility.chkNull(imagePath)){
					content1.setImagePath(content.getImagePath());
				}
				mongoTemplate.save(content1, MongoConstants.FIELDS_CURRENT_NODE);
			}
	}
}
