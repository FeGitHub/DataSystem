/***
 * 随机抽题(被抽中的数据10分钟后才会参与抽签)
 */
#sql("getExam")
SELECT
	* 
FROM
	exam 	
    #if(filter)
      WHERE
        opertime < date_sub( now( ), INTERVAL 10 MINUTE ) 
     #end  
  ORDER BY  RAND() LIMIT 1
#end




#sql("getExamSize")
SELECT
	count(1)  as size
FROM
	exam 	
    #if(filter)
      WHERE
        opertime < date_sub( now( ), INTERVAL 10 MINUTE ) 
     #end  
#end



/***
 * 添加新的试题(操作时间减少10分钟用于立即查询)
 */
#sql("addQuestion")
	 insert into exam (question,opertime,answer) values (#para(question),date_sub(now( ), INTERVAL 10 MINUTE ),#para(answer))
#end