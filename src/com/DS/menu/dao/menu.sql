#sql("selectAllData")
   select id,pId,menuName as name,url,icon,open from menu
#end


#sql("insertDataBatch")
	insert into menu(id,pId,menuName,url,icon) values
	#for(x : cond)
		(#para(x.id),#para(x.pId),#para(x.menuName),#para(x.url),#para(x.icon)) #(for.last ? " ": ",")
	 #end
#end