package com.dbproject.makeup.service;

import com.dbproject.makeup.po.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByReviewId(Long reviewId);

    Comment saveComment(Comment comment);
}
