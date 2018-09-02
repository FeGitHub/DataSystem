package com.DS.utils.test.quartz;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.DS.utils.quartz.QuartzManager;
/***
 * quartz
 * 用于测试调度器的修改任务类的时间及移除定时器任务
 * 配合Test_startQuartzDemo使用
 */
	public class Test_demoJob implements Job {  
	private static int a=0;  
	private static String Info="每2秒："; 
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(Info+new Date());  
		a++;  
		if(a==5){  
		System.out.println("修改时间"); 
		Info="每5秒：";
		QuartzManager.modifyJobTime("JobName", "JobGroupName", "syncJobTrigger", "sycJobGroup", "0/5 * * * * ?");  
		}  
		  
		if(a==10){  
		System.out.println("结束任务");  
		QuartzManager.removeJob("JobName", "JobGroupName", "syncJobTrigger", "sycJobGroup");  
		}  
	}
	 
	}  
