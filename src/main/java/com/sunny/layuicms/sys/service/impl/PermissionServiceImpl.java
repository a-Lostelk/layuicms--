package com.sunny.layuicms.sys.service.impl;

import com.sunny.layuicms.sys.entity.Permission;
import com.sunny.layuicms.sys.mapper.PermissionMapper;
import com.sunny.layuicms.sys.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2019-11-09
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    /**
     * 根据权限或菜单删除权限表和各角色之间的关系的数据
     */
    @Override
    public boolean removeById(Serializable id) {
        PermissionMapper permissionMapper = this.getBaseMapper();
        permissionMapper.deleteRolePermissionByPid(id);

        return super.removeById(id);
    }
}
