package com.dbproject.makeup.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user_personal_info")
public class UserInfo {

    // Relationship: UserInfo - User
    @Id
    private int userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String realName;
    private String sex;
    private String profilePicture;
    private String email;
    private String phone;
    private int userLevel;

    // Relationship: (Write) UserInfo - Review
    @OneToMany(mappedBy = "writeUser")
    private List<Review> writeReviewList = new ArrayList<>();

    // Relationship: (Favorite) UserInfo - Review
    @ManyToMany(mappedBy = "favoriteByUserList")
    private List<Review> favoriteReviewList = new ArrayList<>();

    // Relationship: (Like) UserInfo - Review
    @ManyToMany(mappedBy = "likeByUserList")
    private List<Review> likeReviewList = new ArrayList<>();

    // Relationship: UserInfo - Comment
    @OneToMany(mappedBy = "userInfo")
    private List<Comment> commentList = new ArrayList<>();

    public UserInfo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Review> getWriteReviewList() {
        return writeReviewList;
    }

    public void setWriteReviewList(List<Review> writeReviewList) {
        this.writeReviewList = writeReviewList;
    }

    public List<Review> getFavoriteReviewList() {
        return favoriteReviewList;
    }

    public void setFavoriteReviewList(List<Review> favoriteReviewList) {
        this.favoriteReviewList = favoriteReviewList;
    }

    public List<Review> getLikeReviewList() {
        return likeReviewList;
    }

    public void setLikeReviewList(List<Review> likeReviewList) {
        this.likeReviewList = likeReviewList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userLevel=" + userLevel +
                '}';
    }
}
