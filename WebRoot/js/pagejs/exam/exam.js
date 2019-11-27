
/***
 * 随机抽题
 */
$("#exam").click(function(){	
	var filter=$('input:radio:checked').val();
	var successFn=function(data){
		//处理基本参数
		$("#size").html(data.size);		
		showModel();
		$("#key").val(data.id);
		$("#question").val(data.question);
		$("#answer").val(data.answer);
		//处理试题内容
		//alert(data.question);	
		//if(!PAMS.isNull(data.answer)){
		//	alert(data.answer);		
		//}
		
	}
	//var data={"filter":filter};
	PAMS.ajaxDone({url:"/exam/getExam",successFn:successFn,form:"filterForm"});
});



/***
 * 新增数据模态框
 */
$("#newQuestion").click(function(){	
	showModel();
});




/***
 * 添加新的试题
 */
PAMS.doShowModel(function(){
	var question=$("#question").val();
	if(PAMS.isNull(question)){
		PAMS.alert("试题不允许为空！");
		return;
	}
	
	PAMS.ajaxDone({"url":"/exam/addQuestion",form:"templateForm"});
});


/***
 * 清空弹出框的表单的内容
 */
 function clearModel(){
	 $("#question").val("");
	 $("#answer").val("");
	 $("#key").val("");
 }
 

 
 
 /***
  *  具体的模态框展示
  */
 function showModel(){
	   var htm = $($('#template').html());
	   var _html='<div>'+htm[0].outerHTML+'</div>';	
	   PAMS.showModel(_html);
	   clearModel();
 }
 
 