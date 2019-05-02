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
	if(dateStr==""){
		toastrError("转换参数为空",3000);
		return;
	}
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
					_html+= '<option value="'+item+'">'+showText(item)+'</option>';
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


function  showText(classInfo){
	var text="";
	var pageName="com.DS.utils.quartz.jobs.";//调度任务都放于此包，便与反射扫描
	switch(classInfo)
	{
	case pageName+"DBBackupJob"://定时备份系统的数据库资源
		text="备份数据库(系统级)";
	  break;
	case pageName+"DeleteSqlFileJob"://按照一定规则去删除冗余数据库资源
		text="删除冗余数据备份(系统级)";
	  break;
	case pageName+"GetTodayNotify"://给予用户每日的任务统计
		text="统计每日用户通知(系统级)";
	  break;
	case pageName+"HelloJob":
		text="测试hello(测试级)";
	  break;
	case pageName+"RemindJob":
		text="今日任务提醒(测试级)";
	  break;
	case pageName+"SayHelloToEveryone":
		text="测试群发用户信息(测试级)";
	  break;
	case pageName+"TestPython":
		text="测试python(测试级)";
	  break;
	case pageName+"PYML":
		text="算法模型训练测试(测试级)";
	  break;
	default:
		text=classInfo;
	}
	return text;	
}


function showOrHide(obj){	
	if($(obj).val()=="week"){
		$("#weekGroup").show();
	}else{
		$("#weekGroup").hide();
	}
}