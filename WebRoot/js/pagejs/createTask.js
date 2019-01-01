$(function(){
	 $('#taskForm').validationEngine({   
			promptPosition: 'centerReight',
			scroll:false
		});
	 
});



/***
 * 新增工程任务
 */
$("#submitBtn").click(function(){
	 if($("#taskForm").validationEngine('validate')){
		 $.ajax({
				url:basepath+"/task/createTask",
				type:"post",
				data:$("#taskForm").serialize(),
				dataType: "json",
				success:function(data){
					if(data.code==200){	
						toastrSuccess(data.msg,2000);	
						setTimeout(function(){
							window.location.href=basepath+"/task/goTaskList";
							},2000);
					}else{
						toastrError(data.msg,3000);
					}		
				}
			});
	 }
});