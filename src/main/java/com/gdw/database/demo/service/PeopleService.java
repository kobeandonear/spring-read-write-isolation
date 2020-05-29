package com.gdw.database.demo.service;

import com.gdw.database.demo.entity.People;
import com.gdw.database.isolation.DynamicSwitchDataSource;

/**
 * 2020/5/29 - 15:37 by guowenhao6
 * email：guowenhao6@jd.com
 * 不生产代码 做bug的搬运工
 *
 * @author guowenhao6
 */
public interface PeopleService {
    @DynamicSwitchDataSource("masterDataSource")
    void add(People people);

    @DynamicSwitchDataSource("slaveDataSource")
    People search(int id) throws Exception;

    @DynamicSwitchDataSource("masterDataSource")
    void addAndSearch(People people) throws Exception;
}
