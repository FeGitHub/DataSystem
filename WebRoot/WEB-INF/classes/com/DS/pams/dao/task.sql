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
         #if(taskType)
            and taskType =#para(taskType)
        #end 
        order by score desc,
        addTime desc
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
         #if(taskType)
            and taskType =#para(taskType)
        #end 
	#end
	
	
	/****
	 * 新增数据（其实可以直接用DB或model直接处理）
	 */
    #sql("insertData")
		insert into task (taskName,addTime,goal,description,userId) values (#para(taskName),#para(addTime),#para(goal),#para(description),#para(userId))
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
      select projectName as name,DATE_FORMAT(startDate,'%Y/%m/%d') as start,DATE_FORMAT(finshDate,'%Y/%m/%d') as end,userId as id  from project where userId=#para(userId)
  #end
  
  /***
   * 得到用户当天需要完成的任务
   */
  #sql("getTodayTask")
     select * from task where 1=1
      and to_days(now())>=to_days(start) 
      and to_days(now())<=
      CASE 
         WHEN  end is null THEN to_days(start) 
         ELSE  to_days(end) 
	  END 
      and userId=#para(userId)
  #end
  
  
  /***
   * 得到日历表任务安排
   */
  #sql("getTaskCalendar")
    select taskId as id,taskName as title,start,description, end,
    CASE
        WHEN  underway=0 THEN 'grey' 
        WHEN  underway=1 THEN 'grey'
        else  '#3a87ad'
    END  as color
    from task where userId=#para(userId) and start is not null
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
  
  
  /***
   * 获取用户的未分配任务
   */
  #sql("getUndisTasks")
     select * from task where start is null and userId=#para(userId) limit 10
  #end
  
/***
 * 更新任务类型
 */  
#sql("updateTaskType")
   update task set taskType=#para(taskType) where taskId=(select taskId from project_Tree where id=#para(id))
#end



/*****
 * 获取用户的分析数据
 */
#sql("getAnalyseDataByUser")
    SELECT
       case 
	        when x.end is null
		    then 24
		    else HOUR( TIMEDIFF( x.START,x.END ) ) 
	   end plantime,
	x.score AS level,
	(select count(1) from task as y where (y.start>=x.start and x.end<=y.start) or (y.end>=x.start and x.end<=y.end) or(y.start<=x.start and y.end>=x.end)) as tasknum,
	(select count(1) from task as y where y.end>=DATE_SUB(x.end,INTERVAL 7 DAY) and y.end<=x.end) as taskNumInWeek
	FROM
		task AS x
	WHERE
	start IS NOT NULL
	and taskType!=2
	and userId=#para(userId)
#end


