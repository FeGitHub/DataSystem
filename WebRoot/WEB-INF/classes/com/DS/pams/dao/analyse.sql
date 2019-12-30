
#sql("getAnalyseByType")
	select * from analyse where analyseType=#para(analyseType) and userId=#para(userId)
#end


