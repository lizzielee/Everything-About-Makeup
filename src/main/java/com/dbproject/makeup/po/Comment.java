package com.dbproject.makeup.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    // Relationship: Comment - Review
    @ManyToOne
    private Review review;

    // Relationship: UserInfo - Comment
    @ManyToOne
    private UserInfo userInfo;

    // Relationship: Comment - Comment
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childrenCommentList = new ArrayList<>();
    @ManyToOne
    private Comment parentComment;

    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Comment> getChildrenCommentList() {
        return childrenCommentList;
    }

    public void setChildrenCommentList(List<Comment> childrenCommentList) {
        this.childrenCommentList = childrenCommentList;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                '}';
    }
}
