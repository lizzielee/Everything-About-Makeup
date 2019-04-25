package com.dbproject.makeup.service;

import com.dbproject.makeup.po.User;
import com.dbproject.makeup.po.UserInfo;

public interface UserInfoService {

    UserInfo saveUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(Long id);

    UserInfo getUserInfo(User user);

    UserInfo updateUserInfo(Long id, UserInfo userInfo);
}
