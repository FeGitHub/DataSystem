#sql("insertDataBatch")
	insert into ztree(id,pId,name) values
	#for(x : cond)
		(#para(x.id),#para(x.pId),#para(x.name)) #(for.last ? " ": ",")
	 #end
#end