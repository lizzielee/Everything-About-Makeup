package com.dbproject.makeup.service;

import com.dbproject.makeup.po.DetailedProductCategory;
import com.dbproject.makeup.po.GeneralProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    DetailedProductCategory saveDetailedProductCategory(DetailedProductCategory category);

    DetailedProductCategory getDetailedProductCategory(Long id);

    Page<DetailedProductCategory> listDetailedProductCategory(Pageable pageable, Long generalCategoryId);

    DetailedProductCategory updateDetailedProductCategory(Long id, DetailedProductCategory category);

    void deleteDetailedProductCategory(Long id);

    GeneralProductCategory saveGeneralProductCategory(GeneralProductCategory category);

    GeneralProductCategory getGeneralProductCategory(Long id);

    Page<GeneralProductCategory> listGeneralProductCategory(Pageable pageable);

    GeneralProductCategory updateGeneralProductCategory(Long id, GeneralProductCategory category);

    void deleteGeneralProductCategory(Long id);

    List<DetailedProductCategory> listDetailedProductCategory();
}
