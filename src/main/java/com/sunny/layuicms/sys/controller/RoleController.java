package com.sunny.layuicms.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.layuicms.sys.common.DataGridView;
import com.sunny.layuicms.sys.common.WebUtils;
import com.sunny.layuicms.sys.dto.ResultDTO;
import com.sunny.layuicms.sys.entity.Role;
import com.sunny.layuicms.sys.entity.User;
import com.sunny.layuicms.sys.service.RoleService;
import com.sunny.layuicms.sys.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2019-11-15
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo) {
        //初始化分页参数
        Page<Role> page = new Page<>(roleVo.getPage(), roleVo.getLimit());
        //创建查询条件(标题、时间和发布者),根据日期排序
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getName()), "name", roleVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(roleVo.getRemark()), "remark", roleVo.getRemark());
        queryWrapper.eq(roleVo.getAvailable() != null, "available", roleVo.getAvailable());
        queryWrapper.orderByDesc("createtime");
        //执行查询
        this.roleService.page(page, queryWrapper);
        //返回封装数据对象
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 发布公告
     */
    @RequestMapping("addRole")
    public ResultDTO addRole(RoleVo roleVo) {
        try {
            //设置发布时间和发布者
            roleVo.setCreatetime(new Date());
            this.roleService.save(roleVo);
            return ResultDTO.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.ADD_ERROR;
        }
    }

    /**
     * 修改
     *
     * @param roleVo
     * @return
     */
    @RequestMapping("updateRole")
    public ResultDTO updateRole(RoleVo roleVo) {
        try {
            this.roleService.updateById(roleVo);
            return ResultDTO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.UPDATE_ERROR;
        }
    }

    /**
     * 删除单个日志
     */
    @RequestMapping("deleteRole")
    public ResultDTO deleteRole(Integer id) {
        try {
            this.roleService.removeById(id);
            return ResultDTO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.DELETE_FAILED;
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "batchDeleteRole")
    public ResultDTO batchDeleteRole(RoleVo roleVo) {
        try {
            ArrayList<Serializable> list = new ArrayList<>();
            for (Integer id : roleVo.getIds()) {
                list.add(id);
            }
            this.roleService.removeByIds(list);
            return ResultDTO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.DELETE_FAILED;
        }
    }

    /**
     * @return
     */
    @RequestMapping("initPermissionByRoleId")
    public ResultDTO initPermissionByRoleId(@Param("roleId") String roleId) {
        return null;
    }
}

