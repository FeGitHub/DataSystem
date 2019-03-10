/***
 * 获取分页的备忘提醒数据
 */
#sql("getRemindDetails")
	    SELECT * FROM  remind where 1=1
	    #if(startDates)
            and remindTime >= #para(startDates)
        #end
        #if(endDates)
            and remindTime <= #para(endDates)
        #end
        #if(taskName)
            and subject like concat('%', #para(taskName), '%')
        #end 
        and userId=#para(userId)
        order by addTime desc
        #if(start)
        	  limit #para(start),#para(length)
	     #end 
	#end
	
/**
 * 获取过滤的备忘提醒数据的总条数
 */	
	#sql("getSize")
	    SELECT count(1) as total FROM  remind where 1=1   
	     #if(startDates)
            and remindTime >= #para(startDates)
        #end
        #if(endDates)
            and remindTime <= #para(endDates)
        #end
        #if(taskName)
            and subject like concat('%', #para(taskName), '%')
        #end      
	#end
	
	#sql("delById")
		delete from remind where id=#para(id)
	#end
	
	#sql("insertData")
		insert  into remind (subject,content,remindTime,mail,addTime,userId,userName) values (#para(subject),#para(content),#para(remindTime),#para(mail),#para(addTime),#para(userId),#para(userName))
	#end
	
	#sql("updateData")
		update   remind  set subject=#para(subject),content =#para(content),remindTime =#para(remindTime),mail =#para(mail)  where id=#para(id)
	#end
	