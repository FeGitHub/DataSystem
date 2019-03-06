/****
 * 获取用户的通知信息
 */
#sql("selectNotifications")
 select id,url,style,icon,sender,subject,content,
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
#end

#sql("getNotificationSize")
   select count(*) as size from notification where userId=#para(userId);
#end
