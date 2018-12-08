package com.DS.quartz.service;
import com.DS.Bean.QuartzTaskBean;
import com.jfinal.plugin.activerecord.Record;
/***
 * 
 * @author jeff
 * quartz调度任务服务层
 *
 */
public interface QuartzService {
	/***
	 * 根据调度任务获取调度任务的相关信息
	 * @param jobName 调度任务名
	 * @return
	 */
	Record getQuartzTaskByName(String jobName);
	
	/***
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @param cron
	 */
	void modifyJobTime(QuartzTaskBean bean);
}
