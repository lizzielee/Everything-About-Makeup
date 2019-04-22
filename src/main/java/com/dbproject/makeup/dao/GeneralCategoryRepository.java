package com.dbproject.makeup.dao;

import com.dbproject.makeup.po.GeneralProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralCategoryRepository extends JpaRepository<GeneralProductCategory, Long> {
}
