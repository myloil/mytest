/**
 * 
 */
package com.myOil.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shuai.yang
 *
 */
@RestController
public class CustomerController {

	@RequestMapping(value = "/getCustomerList", method = RequestMethod.GET)
	public Map getCustomerList() {
		// User u = userService.checkLoginUser(userName, userPassword);
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		resultMap.put("status", 200);
		resultMap.put("message", "getCustomerList");
		return resultMap;
	}
	
	@RequestMapping(value = "/getCustomerListA", method = RequestMethod.GET)
	public Map getCustomerListA() {
		// User u = userService.checkLoginUser(userName, userPassword);
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		resultMap.put("status", 200);
		resultMap.put("message", "getCustomerListA");
		return resultMap;
	}
}
