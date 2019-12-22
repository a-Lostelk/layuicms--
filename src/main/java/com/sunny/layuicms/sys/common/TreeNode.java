package com.sunny.layuicms.sys.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: sunny
 * @Date: 2019/11/11
 * <p>
 * 树节点
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {

    private Integer id;
    /**
     * 上级部门ID
     */
    @JsonProperty("parentId")
    private Integer pid;
    private String title;
    /**
     * 导航栏小图标
     */
    private String icon;
    private String href;
    private Boolean spread;

    /**
     * 子节点
     */
    private List<TreeNode> children = new ArrayList<>();

    /**
     * 左边导航树构造器：每一个节点都包含下下面的元素，
     * 子节点是需要自己组装的
     *
     * @param id
     * @param pid
     * @param title
     * @param icon
     * @param href
     * @param spread
     */
    public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }

    /**
     * dtree的数据格式
     *
     * @param id
     * @param pid
     * @param title
     * @param spread
     */
    public TreeNode(Integer id, Integer pid, String title, Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.spread = spread;
    }
}
