package com.spedia.dao;

import java.util.List;

import com.spedia.model.Reviews;

public interface ReviewDao {
	public List<Reviews> findByNid(Integer nid, int i);
	public Reviews findById(Integer rid);
	public List<Reviews> getAllUnModeratedReviews();
	public Reviews persist(Reviews reviews);
}
