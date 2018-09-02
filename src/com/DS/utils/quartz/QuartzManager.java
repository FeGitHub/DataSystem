package com.DS.utils.quartz;
import org.quartz.*;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
/***
 * 
 * @author jeff
 * quartz调度器的主要部分
 * 管理调度器任务的新增，修改，删除，暂停
 */
public class QuartzManager {      
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();   
    /***
     * 新增调度器任务
     * @param jobName 任务名
     * @param jobGroupName 任务组
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组
     * @param jobClass 具体要调度的类
     * @param cron 时间表达式
     */
    public static void addJob(String jobName, String jobGroupName,   
            String triggerName, String triggerGroupName, Class jobClass, String cron) {    
        try {    
            Scheduler sched = schedulerFactory.getScheduler();    
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();  
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();    
            triggerBuilder.withIdentity(triggerName, triggerGroupName);  
            triggerBuilder.startNow();          
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));          
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();      
            sched.scheduleJob(jobDetail, trigger);        
            if (!sched.isShutdown()) {    
                sched.start();    
            }    
        } catch (Exception e) {    
            throw new RuntimeException(e);    
        }    
    }    
  
  
   
    /***
     * 修改调度器时间
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @param cron
     */
    public static void modifyJobTime(String jobName,   
            String jobGroupName, String triggerName, String triggerGroupName, String cron) {    
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
                triggerBuilder.withIdentity(triggerName, triggerGroupName);  
                triggerBuilder.startNow();               
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));                
                trigger = (CronTrigger) triggerBuilder.build();               
                sched.rescheduleJob(triggerKey, trigger);              
            }    
        } catch (Exception e) {    
            throw new RuntimeException(e);    
        }    
    }    
  
  
    /***
     * 删除任务
     */
    public static void removeJob(String jobName, String jobGroupName,    
            String triggerName, String triggerGroupName) {    
        try {    
            Scheduler sched = schedulerFactory.getScheduler();    
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);   
            sched.pauseTrigger(triggerKey);
            sched.unscheduleJob(triggerKey);   
            sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {    
            throw new RuntimeException(e);    
        }    
    }    
  
  
    /***
     * 激活所有任务
     */
    public static void startJobs() {    
        try {    
            Scheduler sched = schedulerFactory.getScheduler();    
            sched.start();    
        } catch (Exception e) {    
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
}  