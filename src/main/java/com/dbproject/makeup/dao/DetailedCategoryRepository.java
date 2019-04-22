package com.dbproject.makeup.dao;

import com.dbproject.makeup.po.DetailedProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetailedCategoryRepository extends JpaRepository<DetailedProductCategory, Long>, JpaSpecificationExecutor<DetailedProductCategory> {
}
