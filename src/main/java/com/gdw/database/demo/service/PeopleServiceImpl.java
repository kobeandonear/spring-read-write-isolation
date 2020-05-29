package com.gdw.database.demo.service;

import com.gdw.database.demo.dao.PeopleDao;
import com.gdw.database.demo.entity.People;
import com.gdw.database.isolation.DynamicSwitchDataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 2020/5/29 - 15:37 by guowenhao6
 * email：guowenhao6@jd.com
 * 不生产代码 做bug的搬运工
 *
 * @author guowenhao6
 */
@Service
@Transactional
@DynamicSwitchDataSource("masterDataSource")
@Log4j2
public class PeopleServiceImpl implements PeopleService{
    @Resource
    PeopleDao peopleDao;

    @Override
    @DynamicSwitchDataSource("masterDataSource")
    public void add(People people){
        log.info("开始存入");
        peopleDao.insertSelective(people);
        log.info("存入完成 Id:" + people.getId());
    }

    @Override
    @DynamicSwitchDataSource("slaveDataSource")
    public People search(int id) throws Exception {
        log.info("开始查询 id:" + id);
        People people = peopleDao.selectByPrimaryKey(id);
        if(people !=null){
            log.info("查询结束" + people);
            return people;
        }else{
            throw new Exception("找不到对应的实体对象");
        }
    }

    @Override
    @DynamicSwitchDataSource("masterDataSource")
    public void addAndSearch(People people) throws Exception {
        this.add(people);
        this.search(people.getId());
    }
}
