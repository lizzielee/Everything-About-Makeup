package com.dbproject.makeup.web.user;

import com.dbproject.makeup.po.User;
import com.dbproject.makeup.po.UserInfo;
import com.dbproject.makeup.service.UserInfoService;
import com.dbproject.makeup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final UserService userService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService, UserService userService) {
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    @GetMapping("/info")
    public String info(Model model, HttpSession session) {
        // Get User Id
        User user = ((User)session.getAttribute("user"));

        // Check if we have userInfo.
        UserInfo info = userInfoService.getUserInfo(user);

        if(info == null) {
            info = new UserInfo();
        }

        model.addAttribute("userInfo", info);
        return "user/info";
    }

    @PostMapping("/info")
    public String post(UserInfo userInfo, HttpSession session) {
        User user = ((User)session.getAttribute("user"));
        if(userInfo.getUserInfoId() == null) {
            userInfo.setUser(user);
            userInfoService.saveUserInfo(userInfo);
        }
        else {
            userInfoService.updateUserInfo(userInfo.getUserInfoId(), userInfo);
        }
        return "redirect:/user/info";
    }
}
