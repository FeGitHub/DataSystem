/***
 * 随机抽题
 */
#sql("getExam")
	   SELECT * FROM exam ORDER BY RAND() LIMIT 1
#end


/***
 * 添加新的试题
 */
#sql("addQuestion")
	 insert into exam (question,opertime) values (#para(question),now())
#end