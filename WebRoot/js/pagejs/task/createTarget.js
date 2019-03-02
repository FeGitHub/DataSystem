/****
 *  创建目标任务的页面js
 */
$(function(){	
	 $('#taskForm').validationEngine({   
			promptPosition: 'centerReight',
			scroll:false
		});		
		$(".form_date").datetimepicker({
	        language:"zh-CN",
	        bootcssVer:3,
	        format: 'yyyy-mm-dd hh:ii:ss',  
	        autoclose:true,
	        todayHighlight: true,
	        minuteStep: 1,
	       // startDate:new Date(),//过往时间不可以填
	        weekStart:1
	    }); 
});



/***
 * 新增工程任务
 */
$("#submitBtn").click(function(){
	//console.log("==="+$("#taskForm").serialize());
	 if($("#taskForm").validationEngine('validate')){
		 $.ajax({
				url:basepath+"/task/createTarget",
				type:"post",
				data:$("#taskForm").serialize(),
				dataType: "json",
				success:function(data){
					if(data.code==200){	
						toastrSuccess(data.msg,2000);	
						setTimeout(function(){
							window.location.href=basepath+"/task/goTargetList";
							},2000);
					}else{
						toastrError(data.msg,3000);
					}		
				}
			});
	 }
});







  


