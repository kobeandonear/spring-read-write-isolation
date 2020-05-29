package com.gdw.database.isolation;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 2020/5/28 - 15:44 by guowenhao6
 * email：guowenhao6@jd.com
 * 不生产代码 做bug的搬运工
 *
 * @author guowenhao6
 */
@Aspect
@Slf4j
@Component
@Order(1)
public class SwitchDataSourceAop {

    @Autowired
    private DataSourceSelector dataSourceSelector;

    @Pointcut("@within(com.gdw.database.isolation.DynamicSwitchDataSource)||@annotation(com.gdw.database.isolation.DynamicSwitchDataSource)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        if (DataSourceContextThreadHolder.getDataSoureLocal() != null) {
            DataSourceContextThreadHolder.countIncrement();
            return;
        }
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DynamicSwitchDataSource annotationClass = method.getAnnotation(DynamicSwitchDataSource.class);
        //获取方法上的注解
        if (annotationClass == null) {
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(DynamicSwitchDataSource.class);
            //获取类上面的注解
            if (annotationClass == null) {
                DataSourceContextThreadHolder.setDataSoureLocal(dataSourceSelector.getDefaultDataSource());
                return;
            }
        }
        //获取注解上的数据源的值的信息
        String dataSourceKey = annotationClass.value();
        if (StringUtils.isNotBlank(dataSourceKey)) {
            //给当前的执行SQL的操作设置特殊的数据源的信息
            DataSourceContextThreadHolder.setDataSoureLocal(dataSourceKey);
        }
        log.info("AOP动态切换数据源，className" + joinPoint.getTarget().getClass().getName() + "methodName" + method.getName() + ";dataSourceKey:" + (dataSourceKey == "" ? "默认数据源" : dataSourceKey));
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        DataSourceContextThreadHolder.countDecrementOrClean();
    }
}
