/**
 * Copyrigth (C), 2019-2019
 * FileAName:   UserReviewController
 * Author:
 * Date:        2019-04-21 00:34
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
package com.dbproject.makeup.web.user;

import com.dbproject.makeup.po.Review;
import com.dbproject.makeup.po.User;
import com.dbproject.makeup.service.ProductService;
import com.dbproject.makeup.service.ReviewService;
import com.dbproject.makeup.vo.ReviewQuery;
import com.dbproject.makeup.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")


public class UserReviewController {

    private static final String INPUT = "user/review-input";
    private static final String LIST = "user/user_reviews";
    private static final String REDIRECT_LIST = "redirect:/user/user_reviews";


    private final ReviewService reviewService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public UserReviewController(ReviewService reviewService, CategoryService categoryService, ProductService productService) {
        this.reviewService = reviewService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/user_reviews")
    public String reviews(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                          ReviewQuery review, Model model) {
        model.addAttribute("cat", categoryService.listDetailedProductCategory());
        model.addAttribute("page", reviewService.listReview(pageable, review));
        return LIST;
    }

    @PostMapping("/user_reviews/search")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         ReviewQuery review, Model model) {
        model.addAttribute("page", reviewService.listReview(pageable, review));
        return "user/user_reviews :: reviewList";
    }


    @GetMapping("/review-input")
    public String input(Model model) {
        model.addAttribute("products", productService.listProduct());
        if(!model.containsAttribute("review")) {
            model.addAttribute("review", new Review());
        }
        return INPUT;
    }


    @GetMapping("/user_reviews/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("products", productService.listProduct());

        Review review = reviewService.getReview(id);
        model.addAttribute("review",review);
        return INPUT;
    }


    @PostMapping("/user_reviews")
    public String post(Review review, RedirectAttributes attributes, HttpSession session) {
        review.setWriteUser((User) session.getAttribute("user"));
        review.setRelatedProductList(productService.listProduct(review.getProductIds()));
        Review r;
        if(review.getReviewId() == null) {      // New Review
            r =  reviewService.saveReview(review);
        }
        else {      // Edit exist review
            r = reviewService.updateReview(review.getReviewId(), review);
        }

        if (r == null) {
            attributes.addFlashAttribute("message", "Failed, try again!");
        } else {
            attributes.addFlashAttribute("message", "Nailed it!");
        }
        return REDIRECT_LIST;
    }


    @GetMapping("/user_reviews/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        reviewService.deleteReview(id);
        attributes.addFlashAttribute("message", "Successfully deleted!");
        return REDIRECT_LIST;
    }

}
