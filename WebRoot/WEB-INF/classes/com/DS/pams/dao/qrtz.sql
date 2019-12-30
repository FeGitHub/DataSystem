/***
 * 获取分页的备忘提醒数据
 */
#sql("getJobDetails")
	  select detail.JOB_NAME,detail.JOB_GROUP,detail.DESCRIPTION,detail.JOB_CLASS_NAME,cron.CRON_EXPRESSION,triggers.TRIGGER_NAME,triggers.TRIGGER_GROUP from qrtz_job_details as detail  LEFT JOIN qrtz_triggers as triggers on triggers.JOB_NAME=detail.JOB_NAME and triggers.JOB_GROUP=detail.JOB_GROUP and triggers.SCHED_NAME=detail.SCHED_NAME LEFT JOIN qrtz_cron_triggers as cron on cron.TRIGGER_NAME=triggers.TRIGGER_NAME and cron.TRIGGER_GROUP=triggers.TRIGGER_GROUP and cron.SCHED_NAME=triggers.SCHED_NAME
	  where 1=1
        #if(jobName)
            and detail.JOB_NAME like concat('%', #para(jobName), '%')
        #end 
       
        #if(start)
        	  limit #para(start),#para(length)
	     #end 
#end
	

#sql("getSize")
	    SELECT count(1) as total FROM  qrtz_job_details as detail  LEFT JOIN qrtz_triggers as triggers on triggers.JOB_NAME=detail.JOB_NAME and triggers.JOB_GROUP=detail.JOB_GROUP and triggers.SCHED_NAME=detail.SCHED_NAME LEFT JOIN qrtz_cron_triggers as cron on cron.TRIGGER_NAME=triggers.TRIGGER_NAME and cron.TRIGGER_GROUP=triggers.TRIGGER_GROUP and cron.SCHED_NAME=triggers.SCHED_NAME
	    where 1=1   
        #if(jobName)
            and detail.JOB_NAME like concat('%', #para(jobName), '%')
        #end      
	#end
