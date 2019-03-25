/****
 * 获取用户菜单数据
 */
#sql("selectMenuData")
   select * from menu where pId!=0 order by CONVERT(id,SIGNED)
#end

/***
 * 获取用户菜单的ztree树
 */
#sql("selectAllData")
   select * from menu  order by CONVERT(id,SIGNED)
#end

#sql("deleteAll")
	delete  from menu where 1=1
#end

#sql("insertDataBatch")
	insert into menu(id,pId,name,url,icon,checked) values
	#for(x : cond)
		(#para(x.id),#para(x.pId),#para(x.name),#para(x.url),#para(x.icon),#para(x.checked)) #(for.last ? " ": ",")
	 #end
#end