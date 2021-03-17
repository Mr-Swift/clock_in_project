package com.njusc.npm.app.security.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class MyCasRealm extends CasRealm {  

	//	public MyCasRealm() {
	//	super();
	//}
	
	// 获取授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//ModularRealmAuthenticator
	//	CentralAuthenticationServiceImpl
	//	DefaultFilter
		System.out.println("===============================已经授权了添加授权信息");
		// ... 与前面 MyShiroRealm 相同
		Set<String> roleNames = new HashSet<String>();
//		Set<String> permissions = new HashSet<String>();
//		roleNames.add("admin");
//		permissions.add("user.do?myjsp");
//		permissions.add("login.do?main");
//		permissions.add("login.do?logout");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
//		info.setStringPermissions(permissions);
		return info;
	}
	
	
	//@Override  
	//protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
	//	System.out.println("===============================已经授权了添加授权信息");
	////    String username = (String)principals.getPrimaryPrincipal();  
	//    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
	////    authorizationInfo.setRoles(userService.findRoles(username));  
	////    authorizationInfo.setStringPermissions(userService.findPermissions(username));  
	//    return authorizationInfo;  
	//} 
	
	/**
	 * 返回 CAS 服务器地址，实际使用一般通过参数进行配置
	 */
	public String getCasServerUrlPrefix() {
		System.out.println("======================================getCasServerUrlPrefix");
		return "http://10.88.2.8:8080/cas";
	//	return "http://www.baidu.com";
	}
	/**
	 * 返回 CAS 客户端处理地址，实际使用一般通过参数进行配置
	 */
	public String getCasService() {
		System.out.println("======================================getCasService");
		return "http://10.88.2.33:8080/shirodemo/main.jsp";
	//	return "http://www.baidu.com";
	}
}   

