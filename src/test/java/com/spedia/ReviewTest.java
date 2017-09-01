package com.spedia;

import java.util.List;

import com.spedia.model.Reviews;
import com.spedia.service.CounterService;
import com.spedia.service.review.IReviewService;

import junit.framework.TestCase;

public class ReviewTest extends TestCase {
	/*public void testWriteReview() {
		IReviewService reviewService = (IReviewService) BaseSpringTest.getInstance()
				.getBean("reviewService");
		CounterService counterService = (CounterService) BaseSpringTest.getInstance()
				.getBean("counterService");
		String reviewText="very good";
		Long created=System.currentTimeMillis();
		Reviews reviews=new Reviews(2380,2,"rohini",125,reviewText,4,5,4,3,4,0,created);
		reviews.set_id(counterService.getNextSequence("rid"));
		reviewService.writeReview(reviews);
	}
	public void testApproveReview() {
		IReviewService reviewService = (IReviewService) BaseSpringTest.getInstance()
				.getBean("reviewService");
		List<Reviews> reviews=reviewService.getAllUnModeratedReviews();
		for (Reviews reviews2 : reviews) {
			System.out.println(reviews2.get_id());
			try {
				reviewService.approveReview(reviews2.get_id());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	public void testGetAllUnModeratedReviews() {
		IReviewService reviewService = (IReviewService) BaseSpringTest.getInstance()
				.getBean("reviewService");
		List<Reviews> reviews=reviewService.getAllUnModeratedReviews();
		for (Reviews reviews2 : reviews) {
			System.out.println(reviews2.get_id());
		}
	}
}
