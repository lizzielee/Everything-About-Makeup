/**
 * Copyrigth (C), 2019-2019
 * FileAName:   ReviewServiceImpl
 * Author:
 * Date:        2019-04-19 21:39
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
package com.dbproject.makeup.service;
import com.dbproject.makeup.NotFoundException;
import com.dbproject.makeup.dao.ProductRepository;
import com.dbproject.makeup.dao.ReviewRepository;
import com.dbproject.makeup.po.Product;
import com.dbproject.makeup.po.Review;
import com.dbproject.makeup.vo.ReviewQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Review getReview(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Review> listReview(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Page<Review> listReview(Pageable pageable, ReviewQuery review) {
        return reviewRepository.findAll((Specification<Review>) (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            // Search by title
            if (!"".equals(review.getTitle()) && review.getTitle() != null) {
                predicates.add(cb.like(root.get("title"), "%" + review.getTitle() + "%"));
            }
            // Search by product
            if(review.getProductId() != null) {
                // Get queried product
                Product product = productRepository.findById(review.getProductId()).orElse(null);

                // Find all the reviews contain this product
                predicates.add(cb.isMember(product, root.get("relatedProductList")));
            }
            // Search by recommend
            if (review.isRecommend()) {
                predicates.add(cb.equal(root.<Boolean>get("recommend"),review.isRecommend()));

            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    @Override
    public Page<Review> listReviewByCreateTimeDesc(Pageable pageable) {
        return reviewRepository.findAllByOrderByCreateTimeDesc(pageable);
    }

    @Override
    public Page<Review> listReviewByLikesDesc(Pageable pageable) {
        return reviewRepository.findAll((Specification<Review>) (root, cq, cb) -> {
            cq.orderBy(cb.desc(cb.size(root.get("likeByUserList"))));
            return cq.getRestriction();
        }, pageable);
    }

    @Override
    public Review saveReview(Review review) {
        review.setCreateTime(new Date());
        review.setUpdateTime(new Date());
        review.setViews(0);

        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review review) {
        Review r = reviewRepository.findById(id).orElse(null);
        if (r == null) {
            throw new NotFoundException("Nobody has written a relevant review yet.");
        }
        BeanUtils.copyProperties(review, r);
        return reviewRepository.save(r);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

