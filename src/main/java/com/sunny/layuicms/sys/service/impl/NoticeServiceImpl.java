package com.sunny.layuicms.sys.service.impl;

import com.sunny.layuicms.sys.entity.Notice;
import com.sunny.layuicms.sys.mapper.NoticeMapper;
import com.sunny.layuicms.sys.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2019-11-13
 */
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
