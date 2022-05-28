package com.czvisual.config;


import com.czvisual.entity.User;
import com.czvisual.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /*
     * 认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取的前台username
        String username = (String) authenticationToken.getPrincipal();

        User sysUser = null;
        try {
            sysUser = userService.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断对象是否有值
        if (sysUser == null) {
            return null;
        }
        //密码不需要我们比对，shiro会给我们比对                      //对象    //获取前台密码
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
        return info;

    }

    /*
     * 授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        HashSet<String> roles = new HashSet<>();
        HashSet<String> permissions = new HashSet<>();
        User current = (User) principalCollection.getPrimaryPrincipal();
        if(current.getAvailable() == 1){
            switch (current.getType()) {
                case 0:
                    roles.add("superAdmin");
                    permissions.add("hsManage:hs");
                    permissions.add("dataManage:operateHsData");
                    permissions.add("chartAnalysis");
                    permissions.add("user:manageUser");
                    permissions.add("user:updateProfile");
                    break;
                case 1:
                    roles.add("recorder");
                    permissions.add("hsManage:hs");
                    permissions.add("dataManage:operateHsData");
                    permissions.add("user:updateProfile");
                    break;
                case 2:
                    roles.add("analyst");
                    permissions.add("hsManage:hs");
                    permissions.add("dataManage:operateHsData");
                    permissions.add("chartAnalysis");
                    permissions.add("user:updateProfile");
                    break;
                case 3:
                    roles.add("normalUser");
                    permissions.add("hsManage:hs");
                    permissions.add("user:updateProfile");
                    break;

            }
        }
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }


}
