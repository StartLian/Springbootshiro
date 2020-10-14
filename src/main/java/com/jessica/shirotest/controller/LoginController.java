package com.jessica.shirotest.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jessica.shirotest.bean.User;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class LoginController {
	Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);
	@GetMapping("/login")
	public String login(User user) {
		Subject subject = SecurityUtils.getSubject();
		logger.info("user.getUserName():"+user.getUserName());
		UsernamePasswordToken token = new  UsernamePasswordToken(user.getUserName(),user.getPassword());
 		subject.login(token);
		return "login success";
	}
	 @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin success!";
    }

    @RequiresPermissions("query")
    @GetMapping("/index")
    public String index() {
        return "index success!";
    }

    @RequiresPermissions("add")
    @GetMapping("/add")
    public String add() {
        return "add success!";
    }

}
