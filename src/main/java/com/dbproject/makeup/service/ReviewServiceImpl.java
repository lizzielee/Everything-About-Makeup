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
import com.dbproject.makeup.util.MarkdownUtils;
import com.dbproject.makeup.vo.ReviewQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final int showReviewLen = 500;

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Review getReview(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Review getConvertReview(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if(review == null) {
            throw new NotFoundException("Failed to find review.");
        }

        Review r = new Review();
        BeanUtils.copyProperties(review, r);
        String content = r.getContent();
        r.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return r;
    }

    @Transactional
    @Override
    public Page<Review> listReview(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Page<Review> listReview(Pageable pageable, ReviewQuery query) {
        return reviewRepository.findAll((Specification<Review>) (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            // Search by userId
            if(query.getUserId() != null) {
                predicates.add(cb.equal(root.get("writeUser").get("userId"), query.getUserId()));
            }
            // Search by title
            if (!"".equals(query.getTitle()) && query.getTitle() != null) {
                predicates.add(cb.like(root.get("title"), "%" + query.getTitle() + "%"));
            }
            // Search by product
            if(query.getProductId() != null) {
                // Get queried product
                Product product = productRepository.findById(query.getProductId()).orElse(null);

                // Find all the reviews contain this product
                predicates.add(cb.isMember(product, root.get("relatedProductList")));
            }
            // Search by recommend
            if (query.isRecommend()) {
                predicates.add(cb.equal(root.<Boolean>get("recommend"),query.isRecommend()));

            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    @Transactional
    @Override
    public Page<Review> listReviewByUserId(Pageable pageable, Long userId) {
        return reviewRepository.findAllByWriteUser_UserId(pageable, userId);
    }

    @Transactional
    @Override
    public Page<Review> listConvertReviewByCreateTimeDesc(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAllByOrderByCreateTimeDesc(pageable);
        return listReviewConvert(reviews);
    }

    @Transactional
    @Override
    public Page<Review> listConvertReviewByLikesDesc(Pageable pageable) {
        Page<Review> reviews =  reviewRepository.findAll((Specification<Review>) (root, cq, cb) -> {
            cq.orderBy(cb.desc(cb.size(root.get("likeByUserList"))));
            return cq.getRestriction();
        }, pageable);
        return listReviewConvert(reviews);
    }

    @Transactional
    @Override
    public Page<Review> listConvertReviewUsingProduct(Pageable pageable, Long productId) {
        Product product = productRepository.findById(productId).orElse(null);

        Page<Review> reviews = reviewRepository.findAllByRelatedProductListContaining(pageable, product);
        return listReviewConvert(reviews);
    }

    private Page<Review> listReviewConvert(Page<Review> reviews) {
        // Deep clone
        List<Review> newReviews = new ArrayList<>();
        for(Review review: reviews) {
            Review newReview = new Review();
            BeanUtils.copyProperties(review, newReview);

            // Get substring
            int subLen = newReview.getContent().length() > showReviewLen? showReviewLen : newReview.getContent().length();
            newReview.setContent(MarkdownUtils.markdownToHtmlExtensions(newReview.getContent().substring(0, subLen)));

            newReviews.add(newReview);
        }
        return new PageImpl<>(newReviews);
    }

    @Transactional
    @Override
    public Review saveReview(Review review) {
        review.setCreateTime(new Date());
        review.setUpdateTime(new Date());
        review.setViews(0);

        return reviewRepository.save(review);
    }

    @Transactional
    @Override
    public Review updateReview(Long id, Review review) {
        review.setUpdateTime(new Date());
        Review r = reviewRepository.findById(id).orElse(null);
        if (r == null) {
            throw new NotFoundException("Nobody has written a relevant review yet.");
        }
        BeanUtils.copyProperties(review, r);
        return reviewRepository.save(r);
    }

    @Transactional
    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

