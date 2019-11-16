package com.sunny.layuicms.sys.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: fang
 * @Date: 2019/11/7
 */
public class WebUtils {

    /**
     * 获取Request对象
     *
     * RequestContextHolder Request容器的上下文获得者，属于SpringMVC中的组件
     * Request一般是作用在controller层中
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }

    /**
     * 获取session
     */
    public static HttpSession getHttpSession() {
        return getHttpServletRequest().getSession();
    }
}
