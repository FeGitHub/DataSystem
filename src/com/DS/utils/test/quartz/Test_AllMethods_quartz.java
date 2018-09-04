package com.DS.utils.test.quartz;
import java.util.Date;
import com.DS.utils.quartz.QuartzManager;
/***
 * 用于测试quartz任务的增删改查
 *
 */
public class Test_AllMethods_quartz {
	//这些参数配置用于指定某一个具体的job类
	/*private final static String JobName="JobName";
	private final static String JobGroupName="JobGroupName";
	private final static String syncJobTrigger="syncJobTrigger";
	private final static String sycJobGroup="sycJobGroup";
	private final static String Cron="0/2 * * * * ?";
	private final static String jobClassName="com.DS.utils.quartz.jobs.HelloJob";
	private final static String Description="描述";*/
	
	
	/*private final static String JobName="DBBackup";
	private final static String JobGroupName="DBBackupGroupName";
	private final static String syncJobTrigger="DBBackupTrigger";
	private final static String sycJobGroup="DBBackupJobGroup";
	private final static String Cron="0 0 21 * * ?";
	private final static String jobClassName="com.DS.utils.quartz.jobs.DBBackupJob";
	private final static String Description="每天晚上九点备份数据库";*/
	
	private final static String JobName="sqldel";
	private final static String JobGroupName="sqldelGroupName";
	private final static String syncJobTrigger="sqldelTrigger";
	private final static String sycJobGroup="sqldelJobGroup";
	private final static String Cron="0 0 1 * * ?";
	private final static String jobClassName="com.DS.utils.quartz.jobs.DeleteSqlFileJob";
	private final static String Description="每天早上1点删除冗余数据备份";
	public static void main(String[] args) {	
		addJob();
		//removeJob();
		//startJobs();
	}
	/***
	 * 新增定时器任务
	 */
	public static void addJob(){
		Class jobClass;
		try {
			jobClass = Class.forName(jobClassName);//根据包名和类名找到相应的类
			QuartzManager.addJob(JobName, JobGroupName, syncJobTrigger, sycJobGroup, jobClass,Cron,Description);
			System.out.println("-----------调度器任务已添加----------");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	/***
	 * 删除定时器任务
	 */
	public static void removeJob(){
		QuartzManager.removeJob(JobName, JobGroupName, syncJobTrigger, sycJobGroup);
		System.out.println("-----------调度器任务已被删除----------");
	}
	
	/***
	 * 启动全部定时器任务
	 */
	public static void startJobs(){
		QuartzManager.startJobs();
	}
}
