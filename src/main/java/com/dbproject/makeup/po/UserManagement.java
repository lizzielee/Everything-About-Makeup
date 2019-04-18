package com.dbproject.makeup.po;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_management")
public class UserManagement {

    @Id
    @GeneratedValue
    private int userId;

    private String userName;
    private String userPass;
    private String userRole;
    private int userState;
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    // Relationship: UserInfo - UserManagement
    @OneToOne(mappedBy = "userManagement")
    private UserInfo userInfo;

    public UserManagement() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
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
        return "UserManagement{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userRole='" + userRole + '\'' +
                ", registrationDate=" + registrationDate +
                ", userState=" + userState +
                '}';
    }
}
