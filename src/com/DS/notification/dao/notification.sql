#sql("selectNotifications")
   select url,style,icon,sender,TIMESTAMPDIFF(MINUTE,operatetime, NOW()) as meta from notification
#end