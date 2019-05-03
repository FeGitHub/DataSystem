package com.DS.utils.quartz.jobs;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.DS.utils.common.CSVUtil;
/***
 * 
 * @author jeff
 * 用于测试的基本job类
 */
public class HelloJob implements Job{
	private static Logger logger = Logger.getLogger(HelloJob.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("***************HELLO***********WORLD*************************");			
	}
	
	
}
