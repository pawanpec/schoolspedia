package com.spedia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.spedia.model.Reviews;
import com.spedia.utils.MongoConstants;
@Repository("reviewDao")
public class ReviewDaoImpl implements ReviewDao{
	private static final String REVIEW_COLLECTION = "reviews";
	@Autowired
	MongoTemplate mongoTemplate;
	@Override
	public List<Reviews> findByNid(Integer nid, int i) {
		Query query = new Query();
		query.addCriteria(Criteria.where("nid").is(nid));
		query.addCriteria(Criteria.where("status").is(1));
		return mongoTemplate.find(query, Reviews.class,REVIEW_COLLECTION);
	}

	@Override
	public List<Reviews> getAllUnModeratedReviews() {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(0));
		return mongoTemplate.find(query, Reviews.class,REVIEW_COLLECTION);
	}

	@Override
	public Reviews findById(Integer rid) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(rid));
		return mongoTemplate.findOne(query, Reviews.class,REVIEW_COLLECTION);
	}

	@Override
	public Reviews persist(Reviews reviews) {
		// TODO Auto-generated method stub
		mongoTemplate.save(reviews, REVIEW_COLLECTION);
		return reviews;
	}

}
