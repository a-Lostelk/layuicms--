package com.sunny.layuicms.sys.controller;

import com.sunny.layuicms.sys.common.ActiverUser;
import com.sunny.layuicms.sys.common.WebUtils;
import com.sunny.layuicms.sys.dto.ResultDTO;
import com.sunny.layuicms.sys.entity.Loginfo;
import com.sunny.layuicms.sys.service.LoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 *
 * @Author: fang
 * @Date: 2019/11/7
 * <p>
 * 登录控制器
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    public LoginfoService loginfoService;

    @RequestMapping("login")
    public ResultDTO login(String loginname, String pwd) {
        //获取认证主体（被认证的登录用户密码）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginname, pwd);
        try {
            subject.login(token);
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            System.out.println(activerUser);
            //写入session
            WebUtils.getHttpSession().setAttribute("user", activerUser.getUser());
            /*记录登录日志，名称(姓名+登录级别)，ip，时间*/
            Loginfo loginfo = new Loginfo();
            loginfo.setLoginname(activerUser.getUser().getName() + "-" + activerUser.getUser().getLoginname());
            loginfo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());
            loginfo.setLogintime(new Date());
            loginfoService.save(loginfo);
            System.out.println("登录成功:" + ResultDTO.LOGIN_SUCCESS);
            return ResultDTO.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.err.println("用户名或密码错误" + ResultDTO.LOGIN_FAILED);
            return ResultDTO.LOGIN_FAILED;
        }
    }

}
