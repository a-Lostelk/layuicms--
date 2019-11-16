package com.sunny.layuicms.sys.vo;

import com.sunny.layuicms.sys.entity.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: sunny
 * @Date: 2019/11/11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {

    private static final long serialVersionUID = -156185411519234276L;

    /**
     * 初始值1
     */
    private Integer page = 1;

    /**
     * 默认分页
     */
    private Integer limit = 10;


}
