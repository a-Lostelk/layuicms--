package com.sunny.layuicms.sys.dto;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: fang
 * @Date: 2019/11/7
 * <p>
 * 状态码
 */
public interface Constast {

    /**
     * 登录成功，失败，验证码错误
     */
    Integer LOGIN_SUCCESS = 200;
    Integer LOGIN_FAILED = -1;
    Integer LOGIN_ERROR_CODE = -1;

    /**
     * 常规操作的成功或失败
     */
    Integer SUCCESS = 200;
    Integer FAILED = -1;

    /**
     * 菜单权限类型
     */
    String TYPE_MENU = "menu";
    String TYPE_PERMISSION = "permission";

    /**
     * 状态是否可用
     */
    Object AVAILABLE_TRUE = 1;
    Object AVATIALE_FALSE = 0;

    /**
     * 是否是超级管理员
     */
    Integer TYPE_USER_SUPER = 0;
    Integer TYPE_USER_NORMAL = 1;

    /**
     * 左边导航栏的菜单是否展开
     */
    Integer OPEN_TRUE = 1;
    Integer OPEN_FALSE = 1;
}
