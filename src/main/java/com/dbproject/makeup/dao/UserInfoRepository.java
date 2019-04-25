package com.dbproject.makeup.dao;

import com.dbproject.makeup.po.User;
import com.dbproject.makeup.po.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByUser(User user);
}
