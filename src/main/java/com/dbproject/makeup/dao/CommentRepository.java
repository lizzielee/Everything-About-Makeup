package com.dbproject.makeup.dao;

import com.dbproject.makeup.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByReview_ReviewIdAndParentCommentNull(Long reviewId, Sort sort);

}
