#sql("insertDataBatch")
	insert into finance (monenyValue,financeTime,weekType) values
	#for(x : cond)
		(#para(x.monenyValue),#para(x.financeTime),#para(x.weekType)) #(for.last ? " ": ",")
	 #end
#end