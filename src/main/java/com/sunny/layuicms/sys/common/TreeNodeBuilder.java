package com.sunny.layuicms.sys.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: sunny
 * @Date: 2019/11/11
 */
public class TreeNodeBuilder {

    /**
     * 变成有层级关系的数节点集合
     *
     * @param treeNodes
     * @param topId
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> treeNodes, Integer topId) {
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode n1 : treeNodes) {
            if (n1.getPid().equals(topId)) {
                nodes.add(n1);
            }
            //当树节点的菜单有上级Pid，就加入到该节点的子节点中
            for (TreeNode n2 : treeNodes) {
                if (n1.getId().equals(n2.getPid())) {
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;
    }
}
