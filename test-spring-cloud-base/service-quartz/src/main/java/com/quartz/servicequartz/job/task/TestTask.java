
package com.quartz.servicequartz.job.task;

import com.quartz.servicequartz.client.ServiceAFeignClint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试定时任务(演示Demo，可删除)
 *
 * testTask为spring bean的名称
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component("testTask")
public class TestTask implements ITask{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ServiceAFeignClint serviceAFeignClint;

	@Override
	public void run(String params){
		System.out.println(serviceAFeignClint.print());
		System.out.println("TestTask定时任务正在执行，参数为：{}"+ params);
		logger.debug("TestTask定时任务正在执行，参数为：{}", params);
	}
}