package com.dbproject.makeup.service;

import com.dbproject.makeup.dao.UserRepository;
import com.dbproject.makeup.po.User;
import com.dbproject.makeup.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Check username and password in the database.
     * @param username Queried username.
     * @param password Queried password.
     * @return Return UserInfo if found user, otherwise return null.
     */
    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        if(userRepository.findByUsername(user.getUsername()) != null) {     // Check if have repeat username.
            return null;
        }
        return userRepository.save(user);
    }
}
