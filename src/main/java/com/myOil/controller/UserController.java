/**
 * 
 */
package com.myOil.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myOil.entity.Authority;
import com.myOil.entity.User;
import com.myOil.service.UserService;
import com.myOil.util.EncryptUtil;

/**
 * @author Shuai.yang
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map login(@RequestParam("userName") String userName,
			@RequestParam("userPassword") String userPassword) {
		// User u = userService.checkLoginUser(userName, userPassword);
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try{
			UsernamePasswordToken token = new UsernamePasswordToken(userName,EncryptUtil.encrypt(userPassword));
			SecurityUtils.getSubject().login(token);
			resultMap.put("status", 200);
			resultMap.put("message",SecurityUtils.getSubject().getPrincipal());
		}catch(UnknownAccountException u){
			resultMap.put("status", 300);
			resultMap.put("message","UnknownAccountException");
			u.printStackTrace();
		} 
		
		return resultMap;
	}
	
	@RequestMapping(value = "/list/getAllUsers", method = RequestMethod.GET)
	public Map getAllUsers() {
		// User u = userService.checkLoginUser(userName, userPassword);
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		List<User> l = userService.getAllUsers();
		resultMap.put("status", 200);
		resultMap.put("message", l);
		return resultMap;
	}
	
	@RequestMapping(value = "/list/getUserAllAuthority", method = RequestMethod.GET)
	public Map getUserAllAuthority() {
		// User u = userService.checkLoginUser(userName, userPassword);
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Set<Authority>> mp = userService.getUserAllAuthorityMap();
		/*for (Map.Entry<String, Set<Authority>> entry : mp.entrySet()) {
			String key = entry.getKey();
			Set<Authority> authoritySet = entry.getValue();
			for (Authority au : authoritySet) {
				String roles = "roles[" + key + "]";
				System.out.print(au.getAuthorityUrl());
			}
		}*/
		resultMap.put("status", 200);
		resultMap.put("message", mp);
		return resultMap;
	}
}
