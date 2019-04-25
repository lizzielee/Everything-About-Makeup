package com.dbproject.makeup.service;

import com.dbproject.makeup.NotFoundException;
import com.dbproject.makeup.dao.UserInfoRepository;
import com.dbproject.makeup.po.User;
import com.dbproject.makeup.po.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Transactional
    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Transactional
    @Override
    public UserInfo getUserInfo(Long id) {
        return userInfoRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public UserInfo getUserInfo(User user) {
        return userInfoRepository.findByUser(user);
    }

    @Transactional
    @Override
    public UserInfo updateUserInfo(Long id, UserInfo userInfo) {
        UserInfo originalInfo = userInfoRepository.findById(id).orElse(null);
        if(originalInfo == null) {
            throw new NotFoundException("Invalid user info");
        }
        BeanUtils.copyProperties(userInfo, originalInfo);
        return userInfoRepository.save(originalInfo);
    }
}
