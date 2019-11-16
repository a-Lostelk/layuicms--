package com.sunny.layuicms.sys.controller;


import com.sunny.layuicms.sys.common.DataGridView;
import com.sunny.layuicms.sys.common.TreeNode;
import com.sunny.layuicms.sys.entity.Dept;
import com.sunny.layuicms.sys.service.DeptService;
import com.sunny.layuicms.sys.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2019-11-14
 */
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 加载部门左边树结构JSON数据
     *
     * @return
     */
    @RequestMapping("loadDeptManagerLeft")
    public DataGridView loadDeptManagerLeft(DeptVo deptVo) {
        List<Dept> list = this.deptService.list();
        List<TreeNode> treeNodes=new ArrayList<>();
        for (Dept dept : list) {
            Boolean spread = deptVo.getOpen() == 1 ? true : false;
            treeNodes . add(new TreeNode(dept . getId(), dept. getPid(), dept.getTitle(), spread));
        }
        return new DataGridView(treeNodes);
    }

}

