package com.dbproject.makeup.service;
import com.dbproject.makeup.po.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Review getReview(Long id);

    Page<Review> listReview(Pageable pageable, Review review);

    Review saveReview(Review review);

    Review updateReview(Long id, Review review);

    void deleteReview(Long id);

}