package com.quartz.servicequartz.job.service;


import com.quartz.servicequartz.job.convert.ConvertUtils;
import com.quartz.servicequartz.job.dao.ScheduleJobDao;
import com.quartz.servicequartz.job.entity.ScheduleJobEntity;
import com.quartz.servicequartz.job.utils.ScheduleUtils;
import com.quartz.servicequartz.utils.SnowflakeIdWorker;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobDao scheduleJobDao;

    public void save(io.renren.modules.job.dto.ScheduleJobDTO dto) {

        ScheduleJobEntity entity = ConvertUtils.sourceToTarget(dto, ScheduleJobEntity.class);
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        entity.setId(idWorker.nextId());
        entity.setCreator(1234L);
        entity.setUpdater(45667L);
        entity.setStatus(1);
        scheduleJobDao.insert(entity);

        ScheduleUtils.createScheduleJob(scheduler, entity);
    }

    public io.renren.modules.job.dto.ScheduleJobDTO get(Long id) {
        ScheduleJobEntity entity = scheduleJobDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, io.renren.modules.job.dto.ScheduleJobDTO.class);
    }

    public void update(io.renren.modules.job.dto.ScheduleJobDTO dto)  {

        try {
            ScheduleJobEntity entity = ConvertUtils.sourceToTarget(dto, ScheduleJobEntity.class);

            ScheduleUtils.updateScheduleJob(scheduler, entity);

            scheduleJobDao.updateById(entity);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteBatch(Long[] ids) {
        try {
            for(Long id : ids){
                ScheduleUtils.deleteScheduleJob(scheduler, id);
            }

            //删除数据
            scheduleJobDao.deleteBatchIds(Arrays.asList(ids));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void run(Long[] ids) {

        try {
            for(Long id : ids){
                ScheduleUtils.run(scheduler, scheduleJobDao.selectById(id));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void pause(Long[] ids) {
        try {
            for(Long id : ids){
                ScheduleUtils.pauseJob(scheduler, id);
            }

            updateBatch(ids, 0);
        }catch (Exception e){

        }


    }

    public void resume(Long[] ids) {
        try {
            for(Long id : ids){
                ScheduleUtils.resumeJob(scheduler, id);
            }

            updateBatch(ids, 1);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public int updateBatch(Long[] ids, int status){
        Map<String, Object> map = new HashMap<>(2);
        map.put("ids", ids);
        map.put("status", status);
        return scheduleJobDao.updateBatch(map);
    }
}
