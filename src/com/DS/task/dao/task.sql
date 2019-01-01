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
		insert  into task (taskName,addTime) values (#para(taskName),#para(addTime))
	#end	
	
	#sql("delById")
		delete from task where taskId=#para(taskId)
	#end