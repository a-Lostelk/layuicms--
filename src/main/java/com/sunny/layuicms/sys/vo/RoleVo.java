package com.sunny.layuicms.sys.vo;

import com.sunny.layuicms.sys.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: sunny
 * @Date: 2019/11/11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends Role {

    private static final long serialVersionUID = -156185411519234276L;

    /**
     * 初始值1
     */
    private Integer page = 1;

    /**
     * 默认分页
     */
    private Integer limit = 10;

    /**
     * 接收多个ID
     */
    private Integer[] ids;


}
