/***
 * 获取个人信息
 */
#sql("getUserInfoByAccount")
	select * from user where account=#para(account)   
#end
	
