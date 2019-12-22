package com.sunny.layuicms.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: fang
 * @Date: 2019/11/7
 */
@Controller
@RequestMapping("sys")
public class SystemController {

    /**
     * 跳转登录页面
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "system/index/login";
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("index")
    public String index() {
        return "system/index/index";
    }

    /**
     * 跳转到控制台
     */
    @RequestMapping("toMain")
    public String toMain() {
        return "system/index/main";
    }

    /************************************************************************/

    /**
     * 跳转到日志管理
     */
    @RequestMapping("toLoginInfoManager")
    public String toLoginInfoManager() {
        return "system/loginfo/loginfoManager";
    }

    /**
     * 跳转到公告管理
     */
    @RequestMapping("toNoticeManager")
    public String toNoticManager() {
        return "system/notice/noticeManager";
    }

    /************************************************************************/

    /**
     * 跳转到部门管理的首页
     *
     * @return
     */
    @RequestMapping("toDeptManager")
    public String toDeptManager() {
        return "system/dept/deptManager";
    }

    /**
     * 跳转到部门管理的left
     *
     * @return
     */
    @RequestMapping("toDeptLeft")
    public String toDeptLeft() {
        return "system/dept/deptLeft";
    }

    /**
     * 跳转到部门管理的right
     *
     * @return
     */
    @RequestMapping("toDeptRight")
    public String toDeptRight() {
        return "system/dept/deptRight";
    }

    /************************************************************************/
    /**
     * 跳转到菜单管理
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }

    /**
     * 跳转到菜单管理-left
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/menuLeft";
    }

    /**
     * 跳转到菜单管理--right
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight() {
        return "system/menu/menuRight";
    }

/************************************************************************/
    /**
     * 跳转到权限管理
     */
    @RequestMapping("toPermissionManager")
    public String toPermissionManager() {
        return "system/permission/permissionManager";
    }

    /**
     * 跳转到权限管理-left
     */
    @RequestMapping("toPermissionLeft")
    public String toPermissionLeft() {
        return "system/permission/permissionLeft";
    }

    /**
     * 跳转到权限管理--right
     */
    @RequestMapping("toPermissionRight")
    public String toPermissionRight() {
        return "system/permission/permissionRight";
    }

    /************************************************************************/

    /**
     * 跳转到角色管理
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }

    /************************************************************************/
}
