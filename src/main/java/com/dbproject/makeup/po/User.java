package com.dbproject.makeup.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_management")
public class User {

    @Id
    @GeneratedValue
    private int userId;

    private String username;
    private String password;
    private String userRole;
    private int userState;
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    // Relationship: UserInfo - User
    @OneToOne(mappedBy = "user")
    private UserInfo userInfo;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                ", registrationDate=" + registrationDate +
                ", userState=" + userState +
                '}';
    }
}
