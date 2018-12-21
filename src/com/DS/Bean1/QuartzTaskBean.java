package com.DS.Bean1;

public class QuartzTaskBean {
	  private String jobName;//任务名
	  private String jobGroup;//任务组
	  private String triggerName;//触发器名
	  private String triggerGroup;//触发器组
	  private String dateStr;//时间字符串
	  private String description="";//描述
	  private String jobClassStr;//具体的job的类名
	  private String cron;//cron表达式
	  

	  public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public String getJobClassStr() {
		return jobClassStr;
	}
	public void setJobClassStr(String jobClassStr) {
		this.jobClassStr = jobClassStr;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
 
}
