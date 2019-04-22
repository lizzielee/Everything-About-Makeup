package com.dbproject.makeup.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ReviewController {

    @GetMapping("/reviews")
    public String list() {
        return "admin/reviews";
    }
}
