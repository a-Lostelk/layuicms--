package com.sunny.layuicms.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.layuicms.sys.common.DataGridView;
import com.sunny.layuicms.sys.common.TreeNode;
import com.sunny.layuicms.sys.dto.Constast;
import com.sunny.layuicms.sys.dto.ResultDTO;
import com.sunny.layuicms.sys.entity.Permission;
import com.sunny.layuicms.sys.service.PermissionService;
import com.sunny.layuicms.sys.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2019-11-09
 */
@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**************** 权限管理开始 ****************/

    /**
     * 加载权限管理左边的权限树的json
     */
    @RequestMapping("loadPermissionManagerLeftTreeJson")
    public DataGridView loadPermissionManagerLeftTreeJson(PermissionVo permissionVo) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constast.TYPE_MENU);
        List<Permission> list = this.permissionService.list(queryWrapper);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission permission : list) {
            //是否展开
            Boolean spread = permission.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(permission.getId(), permission.getPid(), permission.getTitle(), spread));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询
     */
    @RequestMapping("loadAllPermission")
    public DataGridView loadAllPermission(PermissionVo permissionVo) {
        IPage<Permission> page = new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        // 只能查询权限
        queryWrapper.eq("type", Constast.TYPE_PERMISSION);
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getPercode()), "percode", permissionVo.getPercode());
        queryWrapper.eq(permissionVo.getId() != null,
                "pid", permissionVo.getId());
        queryWrapper.orderByAsc("ordernum");
        this.permissionService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 加载最大的排序码
     *
     * @return
     */
    @RequestMapping("loadPermissionMaxOrderNum")
    public Map<String, Object> loadPermissionMaxOrderNum() {
        Map<String, Object> map = new HashMap<String, Object>();

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
    @RequestMapping("addPermission")
    public ResultDTO addPermission(PermissionVo permissionVo) {
        try {
            // 设置添加类型
            permissionVo.setType(Constast.TYPE_PERMISSION);
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
    @RequestMapping("updatePermission")
    public ResultDTO updatePermission(PermissionVo permissionVo) {
        try {
            this.permissionService.updateById(permissionVo);
            return ResultDTO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     *
     * @param permissionVo
     * @return
     */
    @RequestMapping("deletePermission")
    public ResultDTO deletePermission(PermissionVo permissionVo) {
        try {
            this.permissionService.removeById(permissionVo.getId());
            return ResultDTO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.DELETE_FAILED;
        }
    }

    /**************** 权限管理结束 ****************/
}

