package com.dbproject.makeup.dao;

import com.dbproject.makeup.po.Product;
import com.dbproject.makeup.po.Review;
import com.dbproject.makeup.po.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    Page<Review> findAllByWriteUser_UserId(Pageable pageable, Long userId);

    Page<Review> findAllByOrderByCreateTimeDesc(Pageable pageable);

    Page<Review> findAllByRelatedProductListContaining(Pageable pageable, Product product);

    @Query("select r from Review r where r.title like ?1 or r.content like ?1")
    Page<Review> findByQuery(String query, Pageable pageable);
}