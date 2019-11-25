
/***
 * 随机抽题
 */
$("#exam").click(function(){	
	//简化请求
	var successFn=function(data){
		//alert(JSON.stringify(data));
		alert(data.question);	
		if(!PAMS.isNull(data.answer)){
			alert(data.answer);		
		}		
	}
	PAMS.ajaxDone({url:"/exam/getExam",successFn:successFn});
});



/***
 * 新增数据模态框
 */
$("#newQuestion").click(function(){	
   var htm = $($('#template').html());
   var _html='<div>'+htm[0].outerHTML+'</div>';	
   PAMS.showModel(_html);
});


/***
 * 添加新的试题
 */
PAMS.doShowModel(function(){
	var question=$("#qusetion").val();
	if(PAMS.isNull(question)){
		PAMS.alert("试题不允许为空！");
		return;
	}
	var answer=$("#answer").val();
	var data={"question":question,"answer":answer};
	PAMS.ajaxDone({"data":data,"url":"/exam/addQuestion"});
});

