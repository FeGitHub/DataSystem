/***
 * 获取分页的目标任务数据列表
 */
  #sql("getTargetList")
	    SELECT * FROM  task where 1=1
	    and projectId is null
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
	    SELECT count(1) as total FROM  task where 1=1   
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
		insert into task (taskName,addTime,goal,deadline,description) values (#para(taskName),#para(addTime),#para(goal),#para(deadline),#para(description))
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
     select * from task where to_days(deadline) = to_days(now()) where userId=#para(userId)
  #end