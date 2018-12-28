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
        order by addTime desc
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
	
	#sql("delById")
		delete from ds_remind where id=#para(id)
	#end
	
	#sql("insertData")
		insert  into ds_remind (remindName,remindText,remindTime,mail,addTime) values (#para(remindName),#para(remindText),#para(remindTime),#para(mail),#para(addTime))
	#end
	
	#sql("updateData")
		update   ds_remind  set remindName=#para(remindName),remindText =#para(remindText),remindTime =#para(remindTime),mail =#para(mail)  where id=#para(id)
	#end
	