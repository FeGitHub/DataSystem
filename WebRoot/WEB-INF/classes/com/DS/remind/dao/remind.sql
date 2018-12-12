/***
 * 获取分页的备忘提醒数据
 */
#sql("getRemindDetails")
	    SELECT * FROM  ds_remind where 1=1
	    #if(startDates)
            and remindTime >= #para(startDates)
        #end
        #if(endDates)
            and remindTime <= #para(endDates)
        #end
        #if(taskName)
            and remindName like concat('%', #para(taskName), '%')
        #end 
        order by remindTime desc
        #if(start)
        	  limit #para(start),#para(length)
	     #end 
	#end
	
/**
 * 获取过滤的备忘提醒数据的总条数
 */	
	#sql("getSize")
	    SELECT count(1) as total FROM  ds_remind where 1=1   
	     #if(startDates)
            and remindTime >= #para(startDates)
        #end
        #if(endDates)
            and remindTime <= #para(endDates)
        #end
        #if(taskName)
            and remindName like concat('%', #para(taskName), '%')
        #end      
	#end

