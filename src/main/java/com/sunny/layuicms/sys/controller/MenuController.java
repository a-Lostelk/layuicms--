package com.sunny.layuicms.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.layuicms.sys.common.DataGridView;
import com.sunny.layuicms.sys.common.TreeNode;
import com.sunny.layuicms.sys.common.TreeNodeBuilder;
import com.sunny.layuicms.sys.common.WebUtils;
import com.sunny.layuicms.sys.dto.ResultDTO;
import com.sunny.layuicms.sys.vo.PermissionVo;
import com.sunny.layuicms.sys.dto.Constast;
import com.sunny.layuicms.sys.entity.Permission;
import com.sunny.layuicms.sys.entity.User;
import com.sunny.layuicms.sys.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    /**
     * 加载菜单管理左边的菜单树的json
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(PermissionVo permissionVo) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constast.TYPE_MENU);
        List<Permission> list = this.permissionService.list(queryWrapper);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission menu : list) {
            Boolean spread = menu.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), spread));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(PermissionVo permissionVo) {
        IPage<Permission> page = new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(permissionVo.getId() != null, "id", permissionVo.getId()).or().eq(permissionVo.getId() != null, "pid", permissionVo.getId());
        //只查询菜单
        queryWrapper.eq("type", Constast.TYPE_MENU);
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
        queryWrapper.orderByAsc("ordernum");
        this.permissionService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 加载最大的排序码
     *
     * @return
     */
    @RequestMapping("loadMenuMaxOrderNum")
    public Map<String, Object> loadMenuMaxOrderNum() {
        Map<String, Object> map = new HashMap<>();

        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Permission> page = new Page<>(1, 1);
        List<Permission> list = this.permissionService.page(page, queryWrapper).getRecords();
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
            map.put("value", 1);
        }
        return map;
    }


    /**
     * 添加
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("addMenu")
    public ResultDTO addMenu(PermissionVo permissionVo) {
        try {
            //设置添加类型
            permissionVo.setType(Constast.TYPE_MENU);
            this.permissionService.save(permissionVo);
            return ResultDTO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.ADD_ERROR;
        }
    }


    /**
     * 修改
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("updateMenu")
    public ResultDTO updateMenu(PermissionVo permissionVo) {
        try {
            this.permissionService.updateById(permissionVo);
            return ResultDTO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.UPDATE_ERROR;
        }
    }


    /**
     * 查询当前的ID的菜单有没有子菜单
     */
    @RequestMapping("checkMenuHasChildrenNode")
    public Map<String, Object> checkMenuHasChildrenNode(PermissionVo permissionVo) {
        Map<String, Object> map = new HashMap<String, Object>();

        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", permissionVo.getId());
        List<Permission> list = this.permissionService.list(queryWrapper);
        if (list.size() > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("deleteMenu")
    public ResultDTO deleteMenu(PermissionVo permissionVo) {
        try {
            this.permissionService.removeById(permissionVo.getId());
            return ResultDTO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.DELETE_FAILED;
        }
    }
}
