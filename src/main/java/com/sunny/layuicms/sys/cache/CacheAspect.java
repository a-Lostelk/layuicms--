package com.sunny.layuicms.sys.cache;

import cn.hutool.core.bean.BeanUtil;
import com.sunny.layuicms.sys.entity.Dept;
import com.sunny.layuicms.sys.vo.DeptVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Sunny
 * @Date: 2019/11/19
 * <p>
 * 缓存处理
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class CacheAspect {

    /**
     * 声明一个缓存容器
     */
    private Map<String, Object> CACHE_CONTAINER = new HashMap<>();

    /**
     * 声明切面表达式
     */
    private static final String POINTCUT_DEPT_UPDATE = "execution(* com.sunny.layuicms.sys.service.impl.DeptServiceImpl.updateById(..))";
    private static final String POINTCUT_DEPT_GET = "execution(* com.sunny.layuicms.sys.service.impl.DeptServiceImpl.getOne(..))";
    private static final String POINTCUT_DEPT_DELETE = "execution(* com.sunny.layuicms.sys.service.impl.DeptServiceImpl.removeById(..))";

    private static final String CACHE_DEPT_PREFIX = "dept:";

    /**
     * 查询切入
     */
    @Around(value = CacheAspect.POINTCUT_DEPT_GET)
    public Object cacheDeptGet(ProceedingJoinPoint point) throws Throwable {
        Integer object = (Integer) point.getArgs()[0];
        Object result = CACHE_CONTAINER.get(CACHE_DEPT_PREFIX + object);
        if (result != null) {
            return result;
        } else {
            Dept result1 = (Dept) point.proceed();
            CACHE_CONTAINER.put(CACHE_DEPT_PREFIX + result1.getId(), result1);
            return result1;
        }
    }

    /**
     * 更新切入
     */
    @Around(value = CacheAspect.POINTCUT_DEPT_UPDATE)
    public Object cacheDeptUpdate(ProceedingJoinPoint point) throws Throwable {
        DeptVo deptVo = (DeptVo) point.getArgs()[0];
        Boolean isSuccess = (Boolean) point.proceed();
        if (isSuccess) {
            Dept dept = (DeptVo) CACHE_CONTAINER.get(CACHE_DEPT_PREFIX + deptVo.getId());
            if (dept == null) {
                dept = new Dept();
                BeanUtil.copyProperties(deptVo, dept);
                CACHE_CONTAINER.put(CACHE_DEPT_PREFIX + dept.getId(), dept);
            }
        }
        return isSuccess;
    }

    /**
     * 删除切入
     */
    @Around(value = CacheAspect.POINTCUT_DEPT_DELETE)
    public Object cacheDeptDelete(ProceedingJoinPoint point) throws Throwable {
        Integer id = (Integer) point.getArgs()[0];
        Boolean isSuccess = (Boolean) point.proceed();
        if (isSuccess) {
            CACHE_CONTAINER.remove(CACHE_DEPT_PREFIX + id);
        }
        return isSuccess;
    }
}
