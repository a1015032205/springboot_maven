package com.springboot.md.dao;

import com.springboot.md.pojo.BossJavaJob;

public interface BossJavaJobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BossJavaJob record);

    int insertSelective(BossJavaJob record);

    BossJavaJob selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BossJavaJob record);

    int updateByPrimaryKey(BossJavaJob record);
}