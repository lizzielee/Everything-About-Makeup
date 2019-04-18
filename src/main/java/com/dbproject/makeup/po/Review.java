package com.dbproject.makeup.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reviewId;

    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commendable;
    private boolean published;
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    // Relationship: (Write) UserInfo - Review
    @ManyToOne
    private UserInfo writeUser;

    // Relationship: (Favorite) UserInfo - Review
    @ManyToMany
    private List<UserInfo> favoriteByUserList = new ArrayList<>();

    // Relationship: (Like) UserInfo - Review
    @ManyToMany
    private List<UserInfo> likeByUserList = new ArrayList<>();

    // Relationship: Comment - Review
    @OneToMany(mappedBy = "review")
    private List<Comment> commentList = new ArrayList<>();

    // Relationship: Review - Product
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Product> relatedProductList = new ArrayList<>();

    public Review() {
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommendable() {
        return commendable;
    }

    public void setCommendable(boolean commendable) {
        this.commendable = commendable;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Product> getRelatedProductList() {
        return relatedProductList;
    }

    public void setRelatedProductList(List<Product> relatedProductList) {
        this.relatedProductList = relatedProductList;
    }

    public UserInfo getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(UserInfo writeUser) {
        this.writeUser = writeUser;
    }

    public List<UserInfo> getFavoriteByUserList() {
        return favoriteByUserList;
    }

    public void setFavoriteByUserList(List<UserInfo> favoriteByUserList) {
        this.favoriteByUserList = favoriteByUserList;
    }

    public List<UserInfo> getLikeByUserList() {
        return likeByUserList;
    }

    public void setLikeByUserList(List<UserInfo> likeByUserList) {
        this.likeByUserList = likeByUserList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commendable=" + commendable +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
