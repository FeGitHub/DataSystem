#sql("selectMenuData")
   select * from menu where pId!=0
#end

#sql("selectAllData")
   select * from menu
#end

#sql("deleteAll")
	delete  from menu where 1=1
#end

#sql("insertDataBatch")
	insert into menu(id,pId,name,url,icon) values
	#for(x : cond)
		(#para(x.id),#para(x.pId),#para(x.name),#para(x.url),#para(x.icon)) #(for.last ? " ": ",")
	 #end
#end