/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.quartz.servicequartz.job.dao;

import com.quartz.servicequartz.job.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface ScheduleJobDao{
	
	void insert(ScheduleJobEntity entity);

	ScheduleJobEntity selectById(@Param("id") Long id);

	void updateById(ScheduleJobEntity entity);

	void deleteBatchIds(@Param("idList") List<Long> asList);

	int updateBatch(Map<String, Object> map);

	List<ScheduleJobEntity> selectList(Object o);
}
