/***
 * 获取分页的目标任务数据列表
 */
  #sql("getTargetList")
	    SELECT * FROM  task where 1=1
	    and userId =#para(userId)
	    #if(startDates)
            and addTime >= #para(startDates)
        #end
        #if(endDates)
            and addTime <= #para(endDates)
        #end
        #if(taskName)
            and taskName like concat('%', #para(taskName), '%')
        #end 
        order by addTime desc
        #if(start)
        	  limit #para(start),#para(length)
	     #end 
	#end
	
/**
 * 获取过滤的目标任务数据的总条数
 */	
	#sql("getTargetListSize")
	    SELECT count(1) as total FROM  task where userId =#para(userId) 
	     #if(startDates)
            and addTime >= #para(startDates)
        #end
        #if(endDates)
            and addTime <= #para(endDates)
        #end
        #if(taskName)
            and taskName like concat('%', #para(taskName), '%')
        #end      
	#end
	
    #sql("insertData")
		insert into task (taskName,addTime,goal,deadline,description,userId) values (#para(taskName),#para(addTime),#para(goal),#para(deadline),#para(description),#para(userId))
	#end	
	
	#sql("delById")
		delete from task where taskId=#para(taskId)
	#end
	
	
 /****
  * 通过工程任务id找到这个工程的所有用户
  */
  #sql("getUserByProjectId")
       select  distinct userId as id,userName as name from project where userId=#para(userId)
  #end
  
  
  #sql("getTaskByProjectId")
      select projectName as name,DATE_FORMAT(planStartDate,'%Y/%m/%d') as start,DATE_FORMAT(plantFinshDate,'%Y/%m/%d') as end,userId as id  from project where userId=#para(userId)
  #end
  
  /***
   * 得到用户当天需要完成的任务
   */
  #sql("getTodayTask")
     select * from task where to_days(deadline) = to_days(now()) and userId=#para(userId)
  #end
  
  
  /***
   * 得到日历表任务安排
   */
  #sql("getTaskCalendar")
    select taskId as id,taskName as title,start,description, end from task where userId=#para(userId)
  #end
  
  
 #sql("updateTaskCalendar")
     update task 	    
	       set taskName=#para(taskName),	      
           #if(start)
            start=#para(start), 
           #end           
            #if(end)
              end=#para(end),
           #end
            #if(description)
              description=#para(description),
           #end 
              taskId=#para(taskId)
       where taskId=#para(taskId)
  #end