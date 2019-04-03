/****
 *  创建工程任务的页面js
 */
$(function(){	
	 $('#star').score({
	        fontAwesome: true,
	        score: $("#score").val(),
	        readOnly : true,
	        click: function(score, event){
	        	$("#score").val(score);	          
	        },
	    });
	 $(".form_date").datetimepicker({
	        language:"zh-CN",
	        bootcssVer:3,
	        format: 'yyyy-mm-dd hh:ii:ss',  
	        autoclose:true,
	        todayHighlight: true,
	        minuteStep: 1,
	        startDate:new Date(),//过往时间不可以填
	        weekStart:1
	    }); 
});


/****
 * 启动编辑或关闭编辑
 */
$("#change").click(function(){
	if($(this).is(":checked")){
		$('input').removeAttr("readonly"); 
		$('textarea').removeAttr("readonly"); 
		$('#star').score('readOnly', false);
		$("#submitBtn").show();
	}else{
		$('input').attr("readonly","readonly");
		$('textarea').attr("readonly","readonly"); 
		$('#star').score('readOnly', true);
		$("#submitBtn").hide();
	}
});




/***
 * 更新任务
 */
$("#submitBtn").click(function(){
	 if($("#taskForm").validationEngine('validate')){
		 $.ajax({
				url:basepath+"/task/updateTask",
				type:"post",
				data:$("#taskForm").serialize(),
				dataType: "json",
				success:function(data){
					if(data.code==200){	
						toastrSuccess(data.msg,2000);	
						$('input').attr("readonly","readonly");
						$('textarea').attr("readonly","readonly"); 
						$('#star').score('readOnly', true);
						$("#submitBtn").hide();
					}else{
						toastrError(data.msg,3000);
					}		
				}
			});
	 }
});




  
