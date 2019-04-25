package com.dbproject.makeup.web.user;

import com.dbproject.makeup.po.User;
import com.dbproject.makeup.service.UserService;
import com.dbproject.makeup.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserRegisterController {

    private final UserService userService;

    @Autowired
    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(User user, Model model, RedirectAttributes attributes) {
        // Set user attribute
        user.setRegistrationDate(new Date());

        // Set user role
        user.setUserRole("1");  // Does not have admin access.

        // Set user password
        user.setPassword(MD5Utils.code(user.getPassword()));

        // Save into database
        User u = userService.saveUser(user);
        if(u == null) {
            attributes.addFlashAttribute("message", "Username already exists!");
            return "redirect:/user/register";
        }

        return "redirect:/user";
    }
}
