package com.sunny.layuicms.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.sunny.layuicms.sys.entity.Dept;
import com.sunny.layuicms.sys.mapper.DeptMapper;
import com.sunny.layuicms.sys.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2019-11-14
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    /**
     *
     */
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> selectMaxOrderNum() {
        return deptMapper.selectMaxOrderNum();
    }

    @Override
    public Dept getOne(Wrapper<Dept> queryWrapper) {
        return super.getOne(queryWrapper);
    }

    @Override
    public boolean update(Dept entity, Wrapper<Dept> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateById(Dept entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

}
