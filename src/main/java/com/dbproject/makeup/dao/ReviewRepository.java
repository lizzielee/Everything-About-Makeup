package com.dbproject.makeup.dao;

import com.dbproject.makeup.po.Review;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
}
