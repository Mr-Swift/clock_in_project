package com.njusc.npm.app.security.shiro;

import com.njusc.npm.app.util.SessionUtil;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TUserService;
import com.njusc.npm.utils.Constants;
import com.njusc.npm.utils.util.GetPropertiesUtil;
import com.njusc.npm.utils.util.RSAUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Pattern;


@Component
public class SpringShiroRealm extends AuthorizingRealm {

    private static final Logger Log = LoggerFactory
            .getLogger(SpringShiroRealm.class);

    @Resource
    private TUserService tUserService;


    // 获取授权信息
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {

        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Set<String> roles = new HashSet<String>();

        authorizationInfo.setRoles(roles);

        return authorizationInfo;

    }

    // 获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        /**
         * 通过表单接收的用户名(String)、密码（字符数组）
         */
        String username = token.getUsername();
        char[] password = token.getPassword();

        /**
         * 前端传回的输入的密码字符串
         */
        String passwordStr = new String(token.getPassword());


        /**
         * rsa加密的公钥、私钥
         */
        String privateKey = GetPropertiesUtil.getProperties("privateKey");
        String publicKey = GetPropertiesUtil.getProperties("publicKey");



        Map param = new HashMap();

        param.put("personNo", username);
//        param.put("userPassword", RSAUtil.encrypt(GetPropertiesUtil.getProperties("publicKey"), new String(password)));

        TUserEntity user = tUserService.find(param);

        String passwordInDatabase =null;


        if (user == null) {
            throw new UnknownAccountException();
        } else if (!RSAUtil.decrypt(privateKey, user.getUserPassword()).equals(passwordStr)) {
            throw new UnknownAccountException();
        }else{
            /**
             * 账号、密码验证通过后，就更换数据库里的密码
             * 将原密码解密后，重新加密并存入数据库
             */
            passwordInDatabase = RSAUtil.decrypt(privateKey, user.getUserPassword());
            String newPassword = RSAUtil.encrypt(publicKey, passwordInDatabase);
            if (newPassword != null) {
                user.setUserPassword(newPassword);
                tUserService.update(user);
            }
        }


        if (user.getUserStatus().equals(Constants.BackStageUserStatusEnum.DISABLED.getValue())) {
            //禁用
            throw new DisabledAccountException();
        }

        // 密码强度校验(更改成页面的正则校验)
        String passWordRule = "((^(?=.*[A-Z])(?=.*\\W)[\\da-zA-Z\\W]{6,10}$)|(^(?=.*\\d)(?=.*\\W)[\\da-zA-Z\\W]{6,10}$)|(^(?=.*\\d)(?=.*[a-z])[\\da-zA-Z\\W]{6,10}$)|(^(?=.*\\d)(?=.*[A-Z])[\\da-zA-Z\\W]{6,10}$)|(^(?=.*[a-z])(?=.*\\W)[\\da-zA-Z\\W]{6,10}$)|(^(?=.*[a-z])(?=.*[A-Z])[\\da-zA-Z\\W]{6,10}$))";
        StringBuilder builder = new StringBuilder();

        for (char p : password) {
            builder.append(p);
        }
        boolean pattern = Pattern.compile(passWordRule).matcher(builder.toString()).matches();
        if (!pattern) {
            throw new AccountException();
        }
        SessionUtil.setUser(user);


        return new SimpleAuthenticationInfo(user, token.getPassword(), getName());
    }

}
