

$("#test").click(function(){	
	//简化请求
	var successFn=function(data){
		//alert(JSON.stringify(data));
		alert(data.msg);		
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
	var data={"question":question};
	PAMS.ajaxDone({"data":data,"url":"/exam/addQuestion"});
});

