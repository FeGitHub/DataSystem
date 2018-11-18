package com.DS.dao;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
/***
 * @author jeff
 * quartz的dao层
 */
public class QuartzDao {
	/***
	 *  通过调度任务名获取调度任务信息
	 * @param jobName
	 * @return
	 */
   public  Record getQuartzTaskByName(String jobName){
	   String sql=" select detail.JOB_NAME,detail.JOB_GROUP,triggers.DESCRIPTION,detail.JOB_CLASS_NAME,cron.CRON_EXPRESSION,triggers.TRIGGER_NAME,triggers.TRIGGER_GROUP from qrtz_job_details as detail "
				  +" LEFT JOIN qrtz_triggers as triggers on triggers.JOB_NAME=detail.JOB_NAME and triggers.JOB_GROUP=detail.JOB_GROUP and triggers.SCHED_NAME=detail.SCHED_NAME"
				  +" LEFT JOIN qrtz_cron_triggers as cron on cron.TRIGGER_NAME=triggers.TRIGGER_NAME and cron.TRIGGER_GROUP=triggers.TRIGGER_GROUP and cron.SCHED_NAME=triggers.SCHED_NAME"
	   			  +" where detail.JOB_NAME='"+jobName+"'";
	   Record jobDetails=Db.findFirst(sql);	
	   return jobDetails;
   }
}
