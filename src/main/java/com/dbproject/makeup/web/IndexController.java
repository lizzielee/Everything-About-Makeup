package com.dbproject.makeup.web;

import com.dbproject.makeup.service.CategoryService;
import com.dbproject.makeup.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final ReviewService reviewService;
    private final CategoryService categoryService;

    @Autowired
    public IndexController(ReviewService reviewService, CategoryService categoryService) {
        this.reviewService = reviewService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(@Qualifier("top") Pageable topPageable,
                        @Qualifier("new") Pageable newPageable,
                        @Qualifier("cat") Pageable catPageable,
                        Model model) {
        model.addAttribute("top_reviews", reviewService.listReviewByLikesDesc(topPageable));
        model.addAttribute("new_reviews", reviewService.listReviewByCreateTimeDesc(newPageable));
        addCategoryAttribute(catPageable, model);
        return "index";
    }

    private void addCategoryAttribute(Pageable pageable, Model model) {
        model.addAttribute("category", categoryService.listGeneralProductCategory(pageable));
    }
}
