/**
 * 
 */
package com.myOil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myOil.entity.User;
import com.myOil.service.UserService;

/**
 * @author Shuai.yang
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
    public User login(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword) {
		User u = userService.checkLoginUser(userName, userPassword);
		return u;
    }
}
