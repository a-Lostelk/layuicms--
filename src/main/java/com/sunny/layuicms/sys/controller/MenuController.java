package com.sunny.layuicms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunny.layuicms.sys.common.DataGridView;
import com.sunny.layuicms.sys.common.TreeNode;
import com.sunny.layuicms.sys.common.TreeNodeBuilder;
import com.sunny.layuicms.sys.common.WebUtils;
import com.sunny.layuicms.sys.vo.PermissionVo;
import com.sunny.layuicms.sys.dto.Constast;
import com.sunny.layuicms.sys.entity.Permission;
import com.sunny.layuicms.sys.entity.User;
import com.sunny.layuicms.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: sunny
 * @Date: 2019/11/9
 * <p>
 * index首页左侧菜单管理
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 加载index首页的左侧菜单栏数据，前台转换成JSON
     */
    @RequestMapping("loadIndexMenuJSON")
    public DataGridView loadIndexMenu(PermissionVo permissionVo) {
        //查询菜单表中的所有数据
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        //只取出菜单查询菜单和available
        queryWrapper.eq("type", Constast.TYPE_MENU);
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        //超级管理员的权限和其他用户不一样
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        List<Permission> permissionList;
        if (user.getType().equals(Constast.TYPE_USER_SUPER)) {
            permissionList = permissionService.list(queryWrapper);
        } else {
            // TODO 根据用户ID+权限+角色查询
            permissionList = permissionService.list(queryWrapper);
        }

        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission p : permissionList) {
            Integer id = p.getId();
            Integer pid = p.getPid();
            String title = p.getTitle();
            String icon = p.getIcon();
            String href = p.getHref();
            boolean spread = (p.getOpen().equals(Constast.OPEN_TRUE)) ? true : false;
            treeNodes.add(new TreeNode(id, pid, title, icon, href, spread));
        }
        //构造菜单栏的层级关系
        List<TreeNode> buildList = TreeNodeBuilder.build(treeNodes, 1);
        return new DataGridView(buildList);
    }
}
