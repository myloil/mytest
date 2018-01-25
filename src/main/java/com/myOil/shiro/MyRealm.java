/**
 * 
 */
package com.myOil.shiro;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.myOil.entity.Authority;
import com.myOil.entity.User;
import com.myOil.service.UserService;

/**
 * @author Shuai.yang
 *
 */
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		User token = (User) principals.getPrimaryPrincipal();
		int departmentId = token.getDepartmentId();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole(String.valueOf(departmentId));
		/*Map<String, Set<Authority>> mp = userService.getUserAuthorityMapByDepartmentId(departmentId);
		for (Map.Entry<String, Set<Authority>> entry : mp.entrySet()) {
			String key = entry.getKey();
			Set<Authority> authoritySet = entry.getValue();
			info.addRole(key);
			for (Authority au : authoritySet) {
				info.addStringPermission("/"+au.getAuthorityUrl());
			}
		}*/

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

		String userName = token.getUsername();
		String userPassword = String.valueOf(token.getPassword());
		User user = userService.checkLoginUser(userName, userPassword);
		if (user != null) {
			return new SimpleAuthenticationInfo(user, userPassword, getName());
		}
		return null;
	}

}
