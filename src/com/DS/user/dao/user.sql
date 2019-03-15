/***
 * 获取个人信息
 */
#sql("getUserInfoByAccount")
	select * from user where account=#para(account)   
#end

/***
 * 增加新的用户
 */
#sql("addData")
	insert into user(account,password,mail) values (#para(account),#para(password),#para(mail))   
#end

/***
 * 获取系统中所有用户的id
 */
#sql("getAllUser")
	select * from user 
#end