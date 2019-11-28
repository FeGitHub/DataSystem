
/***
 * 随机抽题
 */
$("#exam").click(function(){	
	var filter=$('input:radio:checked').val();
	var successFn=function(data){
		showModel();	
		PAMS.fillForm(data);	
		showModelData("update");
	}
	PAMS.ajaxDone({url:"/exam/getExam",successFn:successFn,form:"filterForm"});
});



/***
 * 新增数据模态框
 */
$("#newQuestion").click(function(){	
	showModel();
	showModelData("add");
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
  *  具体的模态框展示
  */
 function showModel(){
	   var htm = $($('#template').html());
	   var _html='<div>'+htm[0].outerHTML+'</div>';	
	   PAMS.showModel(_html);
 }
 
 
 /***
  * 处理弹出框的数据展示问题
  */
 function showModelData(type){
	 //新增：隐藏按钮框
	 if(type=="add"){
		 $("#operator").css("display","none");	
		 $("#showAnswer").css("display","");
	 }else{
		 //修改与展示，隐藏答案框
		 $("#operator").css("display","");
		 $("#showAnswer").css("display","none"); 
	 }
}
 

 /***
  * 添加答案显示控制开关改变事件（动态添加的html,增加方法要用on）
  */
 $("body").on("change","input[type=radio][name=show]",function(){
	  var show = $(this).val();
	     if(show=='1'){//展示答案
			 $("#showAnswer").css("display","");	 
		}else{//隐藏
			 $("#showAnswer").css("display","none");	 
		}
	})	
 
 
 
 
 
 
 