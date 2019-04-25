package com.dbproject.makeup.web;

import com.dbproject.makeup.service.CategoryService;
import com.dbproject.makeup.service.ProductService;
import com.dbproject.makeup.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ExploreController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ReviewService reviewService;

    @Autowired
    public ExploreController(CategoryService categoryService, ProductService productService, ReviewService reviewService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.reviewService = reviewService;
    }

    @GetMapping("explore/{g_cat}/{d_cat}")
    public String explore(@PageableDefault Pageable pageable,
            @PathVariable Long g_cat, @PathVariable Long d_cat, Model model) {
        model.addAttribute("g_category", categoryService.getGeneralProductCategory(g_cat));
        model.addAttribute("activeDetailedCategory", d_cat);
        model.addAttribute("products", productService.listProduct(pageable, d_cat));
        return "explore";
    }

    @GetMapping("explore/{g_cat}/{d_cat}/{p_id}")
    public String product(@PageableDefault Pageable pageable,
                          @PathVariable Long g_cat, @PathVariable Long d_cat, @PathVariable Long p_id, Model model) {
        model.addAttribute("g_category", categoryService.getGeneralProductCategory(g_cat));
        model.addAttribute("activeDetailedCategory", d_cat);
        model.addAttribute("product", productService.getProduct(p_id));

        // Product id review query
        model.addAttribute("reviews", reviewService.listConvertReviewUsingProduct(pageable, p_id));
        return "product";
    }
}
