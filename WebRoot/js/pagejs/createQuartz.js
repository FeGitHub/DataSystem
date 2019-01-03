/****
 * 创建调度器任务页面的js
 */
$(function(){
	 $('#qrtzForm').validationEngine({   
			promptPosition: 'centerReight',
			scroll:false
		});
	getAllJobList();
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


//转换表达式的值
$("#transferBtn").click(function(){
	var dateStr=$("#dateStr").val();
	var period=$("input[name='period']:checked").val();
	var weekType=$("#weekType").val();
	$.ajax({
		url:basepath+"/qrtz/transfer",
		data:{
			"dataStr":dateStr,
			"period":period,
			"weekType":weekType
			},
		type:"post",
		dataType: "json",
		success:function(data){
			if(data.code==200){
				$("#cron").val(data.cron);
				$("#description").val(" ( "+data.comment+" )");
			}			
		}
	});
});


/***
 * 获取job类下拉项
 */
function getAllJobList(){
	$.ajax({
		url:basepath+"/qrtz/getAllJob",
		type:"post",
		dataType: "json",
		success:function(data){
			var _html="";
			if(data.code==200){
				$.each(data.allJobList,function(i,item){
					_html+= '<option value="'+item+'">'+item+'</option>';
				});
				$("#jobClass").html(_html);								
			}			
		}
	});
}

/***
 * 新增调度任务
 */
$("#submitBtn").click(function(){
	//console.log($("#qrtzForm").serialize());
	 if($("#qrtzForm").validationEngine('validate')){
		 $.ajax({
				url:basepath+"/qrtz/createQuartzTask",
				type:"post",
				data:$("#qrtzForm").serialize(),
				dataType: "json",
				success:function(data){
					if(data.code==200){	
						toastrSuccess(data.msg,1000);
						setTimeout(function(){
							window.location.href=basepath+"/qrtz/goQrtzTaskList";
							},1000);					
					}else{
						toastrError(data.msg,3000);
					}		
				}
			});
	 }
});