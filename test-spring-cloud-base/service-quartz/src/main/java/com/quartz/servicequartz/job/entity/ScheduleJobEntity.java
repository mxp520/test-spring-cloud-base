

package com.quartz.servicequartz.job.entity;

import com.quartz.servicequartz.job.base.BaseEntity;

import java.util.Date;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ScheduleJobEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * spring bean名称
	 */
	private String beanName;
	/**
	 * 参数
	 */
	private String params;
	/**
	 * cron表达式
	 */
	private String cronExpression;
	/**
	 * 任务状态  0：暂停  1：正常
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 更新者
	 */
	private Long updater;
	/**
	 * 更新时间
	 */
	private Date updateDate;

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUpdater() {
		return updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}