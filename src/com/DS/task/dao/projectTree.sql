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
	insert into project_tree (cId,pId,taskName,userId,projectId,startDate,endDate,checked,depiction) values
	#for(x : cond)
		(#para(x.cId),#para(x.pId),#para(x.taskName),#para(x.userId),#para(x.projectId),#para(x.startDate),#para(x.endDate),#para(x.checked),#para(x.depiction)) #(for.last ? " ": ",")
	 #end
#end

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
	select cId as id,pId,taskName as name,
	       projectId,open,checked,startDate,
	       endDate,userId,depiction 
	from project_tree 
	where projectId=#para(projectId)
	and userId=#para(userId)
#end

/****
 * 获取工程任务树的实际执行任务
 */
#sql("getProjectTreeTask")
	select * 
	from project_tree 
	where cId 
	not in (select distinct pId from project_tree where projectId=#para(projectId)and userId=#para(userId)) 
	and userId=#para(userId)
	#if(dateLimit)
	  to_days(now())>=to_days(startDate) and to_days(now())<=to_days(endDate)
	#end
#end