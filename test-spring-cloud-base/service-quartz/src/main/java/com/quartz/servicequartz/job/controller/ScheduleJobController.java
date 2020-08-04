
package com.quartz.servicequartz.job.controller;

import com.quartz.servicequartz.job.service.ScheduleJobService;
import io.renren.modules.job.dto.ScheduleJobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;



	@GetMapping("{id}")
	public ScheduleJobDTO info(@PathVariable("id") Long id){
		ScheduleJobDTO schedule = scheduleJobService.get(id);
		
		return schedule;
	}

	@PostMapping
	public String save(@RequestBody ScheduleJobDTO dto){

		
		scheduleJobService.save(dto);
		
		return "保存成功";
	}

	@PutMapping
	public String update(@RequestBody ScheduleJobDTO dto){

		scheduleJobService.update(dto);
		
		return "修改成功";
	}

	@DeleteMapping
	public String delete(@RequestBody Long[] ids){
		scheduleJobService.deleteBatch(ids);
		
		return "删除成功";
	}

	@PutMapping("/run")
	public String run(@RequestBody Long[] ids){
		scheduleJobService.run(ids);
		
		return "执行成功";
	}

	@PutMapping("/pause")
	public String pause(@RequestBody Long[] ids){
		scheduleJobService.pause(ids);
		
		return "暂停成功";
	}

	@PutMapping("/resume")
	public String resume(@RequestBody Long[] ids){
		scheduleJobService.resume(ids);
		
		return "恢复成功";
	}

}