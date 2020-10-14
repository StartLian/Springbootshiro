package com.jessica.shirotest.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.jessica.shirotest.bean.Permissions;
import com.jessica.shirotest.bean.Role;
import com.jessica.shirotest.bean.User;
import com.jessica.shirotest.service.LoginService;

public class CustomRealm extends AuthorizingRealm{
	@Autowired
    private LoginService loginService;
	/**
	 * 权限配置
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取登录用户名
		String name = (String) principals.getPrimaryPrincipal();
		//查询用户名称
		User user = loginService.getUserByName(name);
		//添加角色和权限
		SimpleAuthorizationInfo simpleauthorizationinfo = new SimpleAuthorizationInfo();
		for (Role role : user.getRoles()) {
			simpleauthorizationinfo.addRole(role.getRoleName());
			for (Permissions permission : role.getPermissions()) {
				simpleauthorizationinfo.addStringPermission(permission.getPermissionsName());
			}
		}
		return simpleauthorizationinfo;
	}
	/**
	 * 认证配置类
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (StringUtils.isEmpty(token.getPrincipal())) {
			return null;
		}
		String name = token.getPrincipal().toString();
		System.out.println("AuthenticationInfo->name:"+name);
		User user = loginService.getUserByName(name);
//		System.out.println("user:"+user.toString());
		if (user == null) {
			return null;
		} else {
			SimpleAuthenticationInfo simpleAuthenticationInfo = new  SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
 			return simpleAuthenticationInfo;
		}
	}

}
