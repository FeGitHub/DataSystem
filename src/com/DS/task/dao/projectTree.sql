/***************************
 ******工程任务树sql*********
 ***************************/



#sql("deleteAll")
	delete  from project_tree where 1=1
	and projectId=#para(projectId)
#end



#sql("deleteProject")
   delete from project where id=#para(projectId)
#end

/****
 * 插入工程任务树详细信息
 */
#sql("insertDataBatch")
	insert into project_tree (id,pId,taskName,userId,projectId,startDate,endDate,schedule,depiction) values
	#for(x : cond)
		(#para(x.id),#para(x.pId),#para(x.taskName),#para(x.userId),#para(x.projectId),#para(x.startDate),#para(x.endDate),#para(x.schedule),#para(x.depiction)) #(for.last ? " ": ",")
	 #end
#end

/****
 * 获取用户的工程分页数据
 */
#sql("getPTaskList")
   select * from project where userId=#para(userId)
   #if(projectName)
            and projectName like concat('%', #para(projectName), '%')
    #end 
     #if(startDates)
            and addTime >= #para(startDates)
     #end
     #if(endDates)
            and addTime <= #para(endDates)
     #end
    #if(start)
        	 limit #para(start),#para(length)
	#end 
#end

/****
 * 获取用户的任务工程的总数量
 */
#sql("getPTaskListSize")
	    SELECT count(1) as total FROM  project where 1=1   
	     #if(startDates)
            and addTime >= #para(startDates)
        #end
        #if(endDates)
            and addTime <= #para(endDates)
        #end
        #if(projectName)
            and projectName like concat('%', #para(projectName), '%')
        #end         
            and  userId=#para(userId)

	#end
	

/****
 * 获取用户的工程任务树信息
 */
#sql("getProjectById")
	select id,pId,taskName as name,
	       projectId,open,schedule as checked,startDate,
	       endDate,userId,depiction 
	from project_tree 
	where projectId=#para(projectId)
	and userId=#para(userId)
	#if(schedule)
	    and schedule=#para(schedule)
	#end
	#if(underway)
	    and underway=#para(underway)
	#end
#end

/****
 * 获取工程任务树的实际执行任务
 */
#sql("getProjectTreeTask")
	select * 
	from project_tree 
	where id 
	not in (select distinct pId from project_tree where  userId=#para(userId)) 
	and userId=#para(userId)
	#if(dateLimit)
	and to_days(now())>=to_days(startDate) and to_days(now())<=to_days(endDate)
	#end
	#if(projectId)
	   and projectId=#para(projectId)
	#end
	#if(schedule)
	    and schedule=#para(schedule)
	#end
	#if(underway)
	    and underway=#para(underway)
	#end	
#end

/***
 * 得到工程任务的进度信息
 */
#sql("getProjectSchedule")
	SELECT
	COUNT(id) AS tasksize,
    COUNT( CASE WHEN  underway='true' and schedule='false' THEN id END ) AS underway,
    COUNT( CASE WHEN  schedule='true' THEN id END ) AS done 	 	
    FROM
	    project_tree  where userId =#para(userId) AND projectId =#para(projectId)
	     AND id not in (select distinct pId from project_tree where  userId=#para(userId) AND projectId =#para(projectId))  
#end