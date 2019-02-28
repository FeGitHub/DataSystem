/***
 * 获取分页的备忘提醒数据
 */
  #sql("getTaskList")
	    SELECT * FROM  task where 1=1
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
 * 获取过滤的备忘提醒数据的总条数
 */	
	#sql("getSize")
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
     select userId as id,name from user__project where projectId=#para(projectId)
  #end
  
  
  #sql("getTaskByProjectId")
      select taskName as name,DATE_FORMAT(start,'%Y/%m/%d') as start,DATE_FORMAT(end,'%Y/%m/%d') as end,userId as id  from task where projectId=#para(projectId)
  #end