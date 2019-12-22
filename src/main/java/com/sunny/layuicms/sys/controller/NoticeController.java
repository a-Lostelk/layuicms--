package com.sunny.layuicms.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunny.layuicms.sys.common.DataGridView;
import com.sunny.layuicms.sys.common.WebUtils;
import com.sunny.layuicms.sys.dto.ResultDTO;
import com.sunny.layuicms.sys.entity.Notice;
import com.sunny.layuicms.sys.entity.User;
import com.sunny.layuicms.sys.service.NoticeService;
import com.sunny.layuicms.sys.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2019-11-13
 * <p>
 * 公告管理
 */
@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("loadAllNotice")
    public DataGridView loadAllNotice(NoticeVo noticeVo) {
        //初始化分页参数
        Page<Notice> page = new Page<>(noticeVo.getPage(), noticeVo.getLimit());
        //创建查询条件(标题、时间和发布者),根据日期排序
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()), "title", noticeVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getOpername()), "opername", noticeVo.getOpername());
        queryWrapper.ge(noticeVo.getStartTime() != null, "createtime", noticeVo.getCreatetime());
        queryWrapper.le(noticeVo.getEndTime() != null, "createtime", noticeVo.getEndTime());
        queryWrapper.orderByDesc("createtime");
        //执行查询
        this.noticeService.page(page, queryWrapper);
        //返回封装数据对象
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 发布公告
     */
    @RequestMapping("addNotice")
    public ResultDTO addNotice(NoticeVo noticeVo) {
        try {
            //设置发布时间和发布者
            noticeVo.setCreatetime(new Date());
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            noticeVo.setOpername(user.getName());
            if (StringUtils.isEmpty(noticeVo.getTitle()) && noticeVo.getTitle().length() <= 0) {
                return ResultDTO.ADD_ERROR;
            } else if (StringUtils.isEmpty(noticeVo.getContent()) && noticeVo.getContent().length() <= 0) {
                return ResultDTO.ADD_ERROR;
            } else {
                this.noticeService.save(noticeVo);
                return ResultDTO.ADD_SUCCESS;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.ADD_ERROR;
        }
    }

    /**
     * 修改
     *
     * @param noticeVo
     * @return
     */
    @RequestMapping("updateNotice")
    public ResultDTO updateNotice(NoticeVo noticeVo) {
        try {
            this.noticeService.updateById(noticeVo);
            return ResultDTO.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.UPDATE_ERROR;
        }
    }

    /**
     * 删除单个日志
     */
    @RequestMapping("deleteNotice")
    public ResultDTO deleteNotice(Integer id) {
        try {
            this.noticeService.removeById(id);
            return ResultDTO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.DELETE_FAILED;
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "batchDeleteNotice")
    public ResultDTO batchDeleteNotice(NoticeVo noticeVo) {
        try {
            ArrayList<Serializable> list = new ArrayList<>();
            for (Integer id : noticeVo.getIds()) {
                list.add(id);
            }
            this.noticeService.removeByIds(list);
            return ResultDTO.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTO.DELETE_FAILED;
        }
    }
}

