package com.aykacltd.sec.demo.controller;

import com.aykacltd.sec.demo.service.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.NameNotFoundException;
import java.util.Map;

@Controller
public class MainController {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public MainController(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/dologin")
    public String dologfin(@RequestParam Map<String, String> user) {
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.get("username"));

            if (userDetails != null && passwordEncoder.matches(user.get("password"), userDetails.getPassword())) {
                return "success";
            }
            return "error";
        } catch (UsernameNotFoundException e) {
            return "error";
        }
    }

    @PostMapping("/register")
    public String user(@RequestParam Map<String, String> user) {
        customUserDetailsService.createUser(user.get("username"), user.get("password"), user.get("role"));
        return "home";
    }
}
