package com.sunny.layuicms.sys.service.impl;

import com.sunny.layuicms.sys.entity.Role;
import com.sunny.layuicms.sys.mapper.RoleMapper;
import com.sunny.layuicms.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2019-11-15
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
