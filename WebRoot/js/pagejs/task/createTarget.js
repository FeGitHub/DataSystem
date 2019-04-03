/****
 *  创建目标任务的页面js
 */
$(function(){		
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
		  $('#star').score({
		        fontAwesome: true,
		        score: 1,
		        click: function(score, event){
		        	$("#score").val(score);
		            //alert('Class Name: '+this.className+'\n' + 'Score: '+score+'\n' + 'Event Type: '+event.type+'\n');
		        },
		    });
});



/***
 * 新增任务
 */
$("#submitBtn").click(function(){
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


/****
 * 展开或隐藏时间框
 */
$("#change").click(function(){
	if($(this).is(":checked")){
		 $("#show").show();
	}else{
		$("#show").hide();
	}
});




  


