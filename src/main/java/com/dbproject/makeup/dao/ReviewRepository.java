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

    Page<Review> findAllByOrderByCreateTimeDesc(Pageable pageable);

    Page<Review> findAllByRelatedProductListContaining(Pageable pageable, Product product);
}