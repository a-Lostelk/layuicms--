package com.sunny.layuicms.sys.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunny.layuicms.sys.common.ActiverUser;
import com.sunny.layuicms.sys.entity.User;
import com.sunny.layuicms.sys.service.Userervice;
import com.sunny.layuicms.sys.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: fang
 * @Date: 2019/11/7
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private Userervice userervice;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname", authenticationToken.getPrincipal().toString());
        User user = userervice.getOne(queryWrapper);
        if (user != null) {
            ActiverUser activerUser = new ActiverUser();
            activerUser.setUser(user);
            ByteSource byteSourceSalt = ByteSource.Util.bytes(user.getSalt());
            System.out.println(user.getSalt());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser, user.getPwd(), byteSourceSalt, this.getName());
            return info;
        }
        return null;
    }
}
