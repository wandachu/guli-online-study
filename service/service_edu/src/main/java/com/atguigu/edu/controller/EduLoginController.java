package com.atguigu.edu.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

  // Login
  @PostMapping("login")
  public R login() {
    return R.ok().data("token", "admin");
  }

  // Info
  @GetMapping("info")
  public R info() {
    return R.ok().data("roles", "[admin]").data("name", "admin")
        .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"); // test image
  }
}
