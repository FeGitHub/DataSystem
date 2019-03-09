
/****
 * 获取用户的通知信息
 */
#sql("selectNotifications")
 select id,level,type,sender,subject,content,readFlag,
   CASE 
  WHEN TIMESTAMPDIFF(MINUTE,operatetime, NOW())>=1440 THEN
	 operatetime
  WHEN TIMESTAMPDIFF(MINUTE,operatetime, NOW())>60 and TIMESTAMPDIFF(MINUTE,operatetime, NOW())<1440 THEN
	concat(floor(TIMESTAMPDIFF(MINUTE,operatetime, NOW())/60),'小时前')
  else 
  concat(TIMESTAMPDIFF(MINUTE,operatetime, NOW()),'分钟前')
END AS meta   
   from notification
where userId=#para(userId)
order by operatetime desc
	#if(limitSize)
	   limit 0,#para(limitSize)
	#end
	#if(start && pageSize)
	   limit #para(start),#para(pageSize)
	#end
#end

/****
 * 获取用用户通知信息的条数
 */
#sql("getNotificationSize")
   select count(*) as size from notification where userId=#para(userId)
#end

/***
 * 新增通知信息
 */
#sql("addNotification")
  insert into notification (level,type,sender,subject,content,operatetime,userId) values (#para(level),#para(type),#para(sender),#para(subject),#para(content),now(),#para(userId))
#end

/***
 * 删除通知信息
 */
#sql("delNotification")
  delete from notification where id=#para(id) and userId=#para(userId)
#end


/***
 * 批量删除
 */
#sql("batchDel")
   delete from notification where id in (
     #for(id : ids)
        #para(id) #(for.last ? " ": ",")
      #end
   )
#end





/****
 * 修改通知信息
 */
#sql("updateNotification")
    update notification
      #if(readFlag)
         set readFlag=#para(readFlag)
      #end
       #if(subject)
         set subject=#para(subject)
      #end
      where id=#para(id)
      and userId=#para(userId)
#end