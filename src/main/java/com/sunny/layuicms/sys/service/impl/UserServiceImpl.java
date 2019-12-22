package com.sunny.layuicms.sys.service.impl;

import com.sunny.layuicms.sys.entity.User;
import com.sunny.layuicms.sys.mapper.UserMapper;
import com.sunny.layuicms.sys.service.Userervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2019-11-06
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements Userervice {

}
