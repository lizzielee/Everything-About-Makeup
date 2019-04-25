/**
 * Copyrigth (C), 2019-2019
 * FileAName:   CommentController
 * Author:
 * Date:        2019-04-25 04:13
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
package com.dbproject.makeup.web;

import com.dbproject.makeup.po.Comment;
import com.dbproject.makeup.po.User;
import com.dbproject.makeup.service.CommentService;
import com.dbproject.makeup.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ReviewService reviewService;

    @Value("${comment.userInfo}")
    private String userInfo;

    @GetMapping("/comments/{reviewId}/")
    public String comments(@PathVariable Long reviewId, Model model) {
        model.addAttribute("comments", commentService.listCommentByReviewId(reviewId));
        return "review :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long reviewId = comment.getReview().getReviewId();
        comment.setReview(reviewService.getReview(reviewId));
//        User user = (User) session.getAttribute("user");
//        if (user != null) {
//            comment.setAvatar(user.getAvatar());
//            comment.setWriterComment(true);
//        } else {
//            comment.setAvatar(avatar);
//        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + reviewId;
    }








}
