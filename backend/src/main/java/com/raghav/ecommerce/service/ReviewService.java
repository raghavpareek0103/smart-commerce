package com.raghav.ecommerce.service;

import com.raghav.ecommerce.exception.ReviewNotFoundException;
import com.raghav.ecommerce.model.Product;
import com.raghav.ecommerce.model.Review;
import com.raghav.ecommerce.model.User;
import com.raghav.ecommerce.request.CreateReviewRequest;

import javax.naming.AuthenticationException;
import java.util.List;

public interface ReviewService {

    Review createReview(CreateReviewRequest req,
                        User user,
                        Product product);

    List<Review> getReviewsByProductId(Long productId);

    Review updateReview(Long reviewId,
                        String reviewText,
                        double rating,
                        Long userId) throws ReviewNotFoundException, AuthenticationException;


    void deleteReview(Long reviewId, Long userId) throws ReviewNotFoundException, AuthenticationException;

}
