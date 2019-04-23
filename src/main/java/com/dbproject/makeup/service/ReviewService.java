package com.dbproject.makeup.service;
import com.dbproject.makeup.po.Review;
import com.dbproject.makeup.vo.ReviewQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Review getReview(Long id);

    Page<Review> listReview(Pageable pageable);

    Page<Review> listReviewByCreateTimeDesc(Pageable pageable);

    Page<Review> listReviewByLikesDesc(Pageable pageable);

    Page<Review> listReview(Pageable pageable, ReviewQuery reviewQuery);

    Review saveReview(Review review);

    Review updateReview(Long id, Review review);

    void deleteReview(Long id);

}