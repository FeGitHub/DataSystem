package com.DS.utils.test.quartz;
import com.DS.utils.quartz.QuartzManager;
public class TestMain_startQuartzDemo {
/***
 * 用于新增一个测试的调度器任务，以此测试效果
 */
public static void main(String[] args) {		
		QuartzManager.addJob("JobName", "JobGroupName", "syncJobTrigger", "sycJobGroup", Test_demoJob.class, "0/2 * * * * ?","描述");	
	}
}

