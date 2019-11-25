package com.DS.utils.quartz;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DS.exception.BaseException;
/***
 * @author jeff
 * quartz调度器的主要部分
 * 管理调度器任务的新增，修改，删除，暂停
 */
public class QuartzManager {      
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();  
    
    final static Logger logger = LoggerFactory.getLogger(QuartzManager.class);
    /***
     * 新增调度器任务
     * @param jobName 任务名
     * @param jobGroupName 任务组
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组
     * @param jobClass 具体要调度的类
     * @param cron 时间表达式
     * @throws SchedulerException 
     */
    public static Map<String,Object> addJob(String jobName, String jobGroupName,   
 String triggerName, String triggerGroupName, Class jobClass, String cron,String Description) throws SchedulerException { 
    	Map<String,Object> resultMap=new HashMap<String,Object>();
    	resultMap.put("code", 200);
       
            Scheduler sched = schedulerFactory.getScheduler();    
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).withDescription(Description).build(); 
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger(); 
            triggerBuilder.withIdentity(triggerName, triggerGroupName);  
            triggerBuilder.startNow();          
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));          
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();      
            sched.scheduleJob(jobDetail, trigger);        
            if (!sched.isShutdown()) {    
                sched.start();    
            }  
            logger.info("定时任务添加成功");           
        return resultMap;
    }    
  
    /***
     * 重载
     * @param jobName 任务名
     * @param jobGroupName 任务组
     * @param triggerName  触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass 任务类
     * @param cron 表达式
     * @param Description 描述
     * @param mapData 参数
     */
    public static void addJob(String jobName, String jobGroupName,   
    		 String triggerName, String triggerGroupName, Class jobClass, String cron,String Description,Map<String,String> mapData) { 
    		        try {    
    		            Scheduler sched = schedulerFactory.getScheduler();    
    		            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();    		           
    		            for (String in:mapData.keySet()){
    		            	jobDetail.getJobDataMap().put(in, mapData.get(in)); 		            	
    		            }   		    
    		            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger(); 
    		            triggerBuilder.withIdentity(triggerName, triggerGroupName).withDescription(Description);  
    		            triggerBuilder.startNow();          
    		            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));          
    		            CronTrigger trigger = (CronTrigger) triggerBuilder.build();      
    		            sched.scheduleJob(jobDetail, trigger);        
    		            if (!sched.isShutdown()) {    
    		                sched.start();    
    		            }    
    		            logger.info("定时任务添加成功");       
    		        } catch (Exception e) {    
    		            throw new RuntimeException(e);    
    		        }    
     }    
   
    /***
     * 修改调度器时间
     * @param jobName 任务名
     * @param jobGroupName 任务组
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param cron  表达式
     */
    public static void modifyJobTime(String jobName,   
            String jobGroupName, String triggerName, String triggerGroupName, String cron,String Description) {    
        try {    
            Scheduler sched = schedulerFactory.getScheduler();    
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);  
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);    
            if (trigger == null) {    
                return;    
            }    
            String oldTime = trigger.getCronExpression();    
            if (!oldTime.equalsIgnoreCase(cron)) {                 
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();                   
                triggerBuilder.withIdentity(triggerName, triggerGroupName).withDescription(Description);  
                triggerBuilder.startNow();               
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));                
                trigger = (CronTrigger) triggerBuilder.build();               
                sched.rescheduleJob(triggerKey, trigger);              
            }    
            System.out.println("modifyJobTime success");
        } catch (Exception e) {    
            throw new RuntimeException("QuartzManager change time fail...");    
        }    
    }    
  
  
    /****
     * 删除调度任务
     * @param jobName 
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @throws SchedulerException 
     */
    public static void removeJob(String jobName, String jobGroupName,    
            String triggerName, String triggerGroupName) throws SchedulerException {        
            Scheduler sched = schedulerFactory.getScheduler();    
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);   
            sched.pauseTrigger(triggerKey);
            sched.unscheduleJob(triggerKey);   
            sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));
            logger.info("定时任务成功删除");       
    }    
  
  
    /***
     * 激活所有任务
     */
    public static void startJobs() {    
        try {    
            Scheduler sched = schedulerFactory.getScheduler();    
            sched.start();    
            logger.info("定时任务成功启动"); 
        } catch (Exception e) {  
        	 logger.error("定时任务启动失败..."); 
            throw new RuntimeException(e);    
        }    
    }    
  
   /***
    * 暂停所有的任务
    */
    public static void shutdownJobs() {    
        try {    
            Scheduler sched = schedulerFactory.getScheduler();    
            if (!sched.isShutdown()) {    
                sched.shutdown();    
            }    
        } catch (Exception e) {    
            throw new RuntimeException(e);    
        }    
    }   
    
    /** 
     * 停止调度Job任务 
     * @param triggerkey 
     * @return 
     * @throws SchedulerException 
     */  
    public  boolean unscheduleJob(TriggerKey triggerkey)  
            throws SchedulerException{  
    	 Scheduler scheduler = schedulerFactory.getScheduler(); 
        return scheduler.unscheduleJob(triggerkey);  
    }  
    
    /** 
     * 重新恢复触发器相关的job任务  
     * @param triggerkey 
     * @param trigger 
     * @return 
     * @throws SchedulerException 
     */  
    public  Date rescheduleJob(TriggerKey triggerkey, Trigger trigger)  
    throws SchedulerException{  
    	 Scheduler scheduler = schedulerFactory.getScheduler(); 
        return scheduler.rescheduleJob(triggerkey, trigger);  
    }  
    
    
    public static void main(String[] args){
    	startJobs();
    }
}  