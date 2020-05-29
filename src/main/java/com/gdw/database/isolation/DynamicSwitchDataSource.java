package com.gdw.database.isolation;

import java.lang.annotation.*;

/**
 * 2020/5/28 - 15:43 by guowenhao6
 * email：guowenhao6@jd.com
 * 不生产代码 做bug的搬运工
 *
 * @author guowenhao6
 * 用在开启读写分离的方法的最上层，下层方法上配置的目标数据源会被上层的配置覆盖掉用来保持事务
 * 配置在方法上优先级高于类上
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSwitchDataSource {
    String value();
}
