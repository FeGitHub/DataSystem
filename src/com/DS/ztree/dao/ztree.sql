#sql("insertDataBatch")
	insert into ztree(id,pId,name,url) values
	#for(x : cond)
		(#para(x.id),#para(x.pId),#para(x.name),#para(x.url)) #(for.last ? " ": ",")
	 #end
#end

#sql("getZtreeJsonFromDB")
   select * from ztree
#end