
#sql("deleteAll")
	delete  from project_tree where 1=1
	and projectId=#para(projectId)
#end


#sql("deleteProject")
   delete from project where id=#para(projectId)
#end


#sql("insertDataBatch")
	insert into project_tree (cId,pId,taskName,userId,projectId,startDate,endDate,checked) values
	#for(x : cond)
		(#para(x.cId),#para(x.pId),#para(x.taskName),#para(x.userId),#para(x.projectId),#para(x.startDate),#para(x.endDate),#para(x.checked)) #(for.last ? " ": ",")
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
	
	
#sql("getProjectById")
	select cId as id,pId,taskName as name,projectId,open,checked,startDate,endDate from project_tree where projectId=#para(projectId)
#end