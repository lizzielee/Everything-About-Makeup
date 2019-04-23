package com.dbproject.makeup.web;

import com.dbproject.makeup.po.GeneralProductCategory;
import com.dbproject.makeup.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final CategoryService categoryService;

    @Autowired
    public GlobalControllerAdvice(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("category")
    public Page<GeneralProductCategory> categories(@PageableDefault Pageable pageable) {
        return categoryService.listGeneralProductCategory(pageable);
    }
}
