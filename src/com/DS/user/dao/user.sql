/***
 * 获取个人信息
 */
#sql("getUserInfoByAccount")
	select * from user where account=#para(account)   
#end

#sql("addData")
	insert into user(account,password,mail) values (#para(account),#para(password),#para(mail))   
#end