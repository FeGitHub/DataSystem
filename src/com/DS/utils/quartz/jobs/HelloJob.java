package com.DS.utils.quartz.jobs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/***
 * 
 * @author jeff
 * 用于测试的基本job类
 */
public class HelloJob implements Job{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("hello world!");
		
	}

}
