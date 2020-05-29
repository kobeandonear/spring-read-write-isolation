package com.gdw.database.demo.dao;

import com.gdw.database.demo.entity.People;
import com.gdw.database.demo.entity.PeopleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PeopleDao {
    long countByExample(PeopleCriteria example);

    int deleteByExample(PeopleCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(People record);

    int insertSelective(People record);

    List<People> selectByExample(PeopleCriteria example);

    People selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") People record, @Param("example") PeopleCriteria example);

    int updateByExample(@Param("record") People record, @Param("example") PeopleCriteria example);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);

    int insertBatchSelective(List<People> records);

    int updateBatchByPrimaryKeySelective(List<People> records);
}