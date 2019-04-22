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
import com.dbproject.makeup.dao.ReviewRepository;
import com.dbproject.makeup.po.Review;
import com.dbproject.makeup.vo.ReviewQuery;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review getReview(Long id) {
        return reviewRepository.findById(id).get();   // .findOne(id)
    }

    @Override
    public Page<Review> listReview(Pageable pageable, Review review) {
        return null;
    }

    //    @Override
    public Page<Review> listReview(Pageable pageable, ReviewQuery review) {
        return reviewRepository.findAll(new Specification<Review>() {
            @Override
            public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {                  //search by id
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(review.getTitle()) && review.getTitle() != null) {                                       // search by type
                    predicates.add(cb.like(root.<String>get("title"), "%" + review.getTitle() + "%"));
                }
//
//                search by product
//
//                if (review.getTypeId() != null {
//                    predicates.add(cb.equal(root.equals(root.<Type>get("type").get("id"), review.getTypeId()));
//
                if (review.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),review.isRecommend()));

                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);                 // root: searching object; criteriaQuery: searching cons; CriteriaBuilder: searching expression
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
        Review r = reviewRepository.findById(id).get();
        if (r == null) {
            throw new NotFoundException("Nobody has written a relevant review yet.");
        }
        BeanUtils.copyProperties(r,review);
        return reviewRepository.save(r);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);

    }
}

