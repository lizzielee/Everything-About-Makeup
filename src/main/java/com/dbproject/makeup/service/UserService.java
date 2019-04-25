package com.dbproject.makeup.service;

import com.dbproject.makeup.po.User;

public interface UserService {
    User checkUser(String username, String password);

    User saveUser(User user);
}
