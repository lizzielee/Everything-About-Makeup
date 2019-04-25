package com.dbproject.makeup.web;

import com.dbproject.makeup.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private final ReviewService reviewService;

    @Autowired
    public IndexController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String index(@Qualifier("top") Pageable topPageable,
                        @Qualifier("new") Pageable newPageable,
                        Model model) {
        model.addAttribute("top_reviews", reviewService.listConvertReviewByLikesDesc(topPageable));
        model.addAttribute("new_reviews", reviewService.listConvertReviewByCreateTimeDesc(newPageable));
        return "index";
    }

    @GetMapping("/review/{id}")
    public String review(@PathVariable Long id, Model model) {
        model.addAttribute("review", reviewService.getConvertReview(id));
        return "review";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", reviewService.listReview("%"+query+"%", pageable));
        model.addAttribute("query", query);
        return "search";
    }
}
