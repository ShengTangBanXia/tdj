package com.tdj.SpringBootDemo1.config.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tdj.SpringBootDemo1.models.account.entity.Resource;
import com.tdj.SpringBootDemo1.models.account.entity.Role;
import com.tdj.SpringBootDemo1.models.account.entity.User;
import com.tdj.SpringBootDemo1.models.account.service.ResourceService;
import com.tdj.SpringBootDemo1.models.account.service.RoleService;
import com.tdj.SpringBootDemo1.models.account.service.UserService;

@Component
public class MyShiroRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	//资源授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();	//创建资源验证器
		
		String userName = (String) principals.getPrimaryPrincipal();	//获取用户名
		User user = userService.getUserByUserName(userName);			//查询用户
		//若用户不存在，返回错误信息
		if (user == null) {	
			throw new UnknownAccountException("This user name do not exist.");
		}
		List<Role> roles = roleService.getRolesByUserId(user.getUserId());	//获取用户角色
		for (Role role : roles) {
			simpleAuthorizationInfo.addRole(role.getRoleName());	//这里添加的是啥字段，后面资源授权就要用啥字段
			List <Resource> resources = resourceService.getResourcesByRoleId(role.getRoleId());
			
			for (Resource resource : resources) {
				simpleAuthorizationInfo.addStringPermission(resource.getPermission());	//这里添加的是啥字段，后面资源授权就要用啥字段
			}
			
		}
		
		return simpleAuthorizationInfo;	//返回资源验证器
	}

	//身份验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		User user = userService.getUserByUserName(userName); 
		if (user == null) {
			throw new UnknownAccountException("This user name do not exist.");
		}
		//身份验证器，包装用户名和密码
		return new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(), getName());
	}

}
