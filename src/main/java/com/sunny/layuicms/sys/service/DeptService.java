package com.sunny.layuicms.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.sunny.layuicms.sys.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sunny
 * @since 2019-11-14
 */
public interface DeptService extends IService<Dept> {
    /**
     * 查询最大的ordernum
     *
     * @return
     */
    List selectMaxOrderNum();

}
