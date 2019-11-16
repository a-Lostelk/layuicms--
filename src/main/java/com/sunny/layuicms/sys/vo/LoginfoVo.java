package com.sunny.layuicms.sys.vo;

import com.sunny.layuicms.sys.entity.Loginfo;
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
public class LoginfoVo extends Loginfo {

    private static final long serialVersionUID = -156185411519234276L;

    /**
     * 初始值1
     */
    private Integer page = 1;

    /**
     * 默认分页
     */
    private Integer limit = 10;

    private Integer[] ids;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
