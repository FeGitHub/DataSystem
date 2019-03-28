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
 * 获取系统中所有用户
 */
#sql("getAllUser")
	select * from user 
#end


/****
 * 获取用户列表数据
 */
#sql("getUserList")
	select * from user where 1=1
	 #if(start)
        	 limit #para(start),#para(length)
	 #end 
#end



/**** 
 * 获取用户列表数据的总数
 */
#sql("getUserListSize")
	select count(1) as total from user 
	#if(start)
        	  limit #para(start),#para(length)
	 #end
#end


