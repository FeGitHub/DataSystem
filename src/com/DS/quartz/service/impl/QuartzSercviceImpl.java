package com.DS.quartz.service.impl;
import java.text.ParseException;

import org.quartz.Job;

import com.DS.Bean.QuartzTaskBean;
import com.DS.quartz.dao.QuartzDao;
import com.DS.quartz.service.QuartzService;
import com.DS.utils.CronUtil;
import com.DS.utils.quartz.QuartzManager;
import com.jfinal.plugin.activerecord.Record;
/***
 * @author jeff
 * 调度任务服务实现层
 */
public class QuartzSercviceImpl implements QuartzService {
	
	/***
	 * 根据调度任务获取调度任务的相关信息
	 * @param jobName 调度任务名
	 * @return
	 */
	@Override
	public Record getQuartzTaskByName(String jobName) {
		QuartzDao q=new QuartzDao();
		return q.getQuartzTaskByName(jobName);
	}	
	
	/***
	 * 改变调度器任务触发时间
	 */
	@Override
	public void modifyJobTime(QuartzTaskBean bean) {
		String Cron="";
		try {
			Cron = CronUtil.getCron("day", bean.getDateStr());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		QuartzManager.modifyJobTime(bean.getJobName(), bean.getJobGroup(), bean.getTriggerGroup(), bean.getTriggerName(),Cron,bean.getDescription());		
	}


	@Override
	public void addJob(QuartzTaskBean bean){
		String temp = "com.DS.utils.quartz.jobs."+bean.getJobClassStr();
		bean.setJobClassStr(temp);
	    Class jobClass=null;
		try {
			jobClass = Class.forName(bean.getJobClassStr());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		QuartzManager.addJob(bean.getJobName(), bean.getJobName(), bean.getTriggerName(), bean.getTriggerGroup(), jobClass, bean.getCron(), bean.getDescription());
		
	}
   
	  public void getAllJob(){
		  
	  }
}
