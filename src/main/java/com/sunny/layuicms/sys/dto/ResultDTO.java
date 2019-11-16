package com.sunny.layuicms.sys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: fang
 * @Date: 2019/11/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultDTO {

    private Integer code;
    private String msg;

    /**
     * 200表示登录成功，页面中result=200表示登录成功
     */
    public static final ResultDTO LOGIN_SUCCESS = new ResultDTO(Constast.LOGIN_SUCCESS,"登录成功");
    public static final ResultDTO LOGIN_FAILED = new ResultDTO(Constast.LOGIN_FAILED, "登录失败，用户名或密码错误");
    public static final ResultDTO LOGIN_ERROR_CODE = new ResultDTO(Constast.LOGIN_ERROR_CODE, "登录失败，验证码错误");

    public static final ResultDTO  UPDATE_SUCCESS=new ResultDTO(Constast.SUCCESS, "更新成功");
    public static final ResultDTO  UPDATE_ERROR=new ResultDTO(Constast.FAILED, "更新失败，标题或内容不能为空");

    public static final ResultDTO  ADD_SUCCESS=new ResultDTO(Constast.SUCCESS, "添加成功");
    public static final ResultDTO  ADD_ERROR=new ResultDTO(Constast.FAILED, "添加失败，标题或内容不能为空");

    public static final ResultDTO DELETE_SUCCESS = new ResultDTO(Constast.SUCCESS, "删除成功");
    public static final ResultDTO DELETE_FAILED = new ResultDTO(Constast.FAILED, "删除失败");

    public static final ResultDTO  RESET_SUCCESS=new ResultDTO(Constast.SUCCESS, "重置成功");
    public static final ResultDTO  RESET_ERROR=new ResultDTO(Constast.FAILED, "重置失败");

    public static final ResultDTO  DISPATCH_SUCCESS=new ResultDTO(Constast.SUCCESS, "分配成功");
    public static final ResultDTO  DISPATCH_ERROR=new ResultDTO(Constast.FAILED, "分配失败");

    public static final ResultDTO  OPERATE_SUCCESS=new ResultDTO(Constast.SUCCESS, "操作成功");
    public static final ResultDTO  OPERATE_ERROR=new ResultDTO(Constast.FAILED, "操作失败");
}
