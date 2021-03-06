package com.dbproject.makeup.service;
import com.dbproject.makeup.po.Review;
import com.dbproject.makeup.vo.ReviewQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewService {
    Review getReview(Long id);

    Review getConvertReview(Long id);

    Page<Review> listReview(Pageable pageable);

    Page<Review> listReviewByUserId(Pageable pageable, Long userId);

    Page<Review> listConvertReviewByCreateTimeDesc(Pageable pageable);

    Page<Review> listConvertReviewByLikesDesc(Pageable pageable);

    Page<Review> listReview(Pageable pageable, ReviewQuery reviewQuery);

    Page<Review> listConvertReviewUsingProduct(Pageable pageable, Long id);

    Review saveReview(Review review);

    Review updateReview(Long id, Review review);

    void deleteReview(Long id);

    @Transactional
    Page<Review> listReview(String query, Pageable pageable);
}