package com.springboot.db.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.db.entity.MybatisEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MybatisTestMapper extends BaseMapper<MybatisEntity> {
   List<MybatisEntity> getTestData();
   MybatisEntity getDataById(@Param("id") Integer id);
}
