

/***
 * 测试接口数据
 */
$("#test").click(function(){	
	var serviceid=$("#serviceid").val();
	//简化请求
	var successFn=function(data){
		alert(JSON.stringify(data));		
	}
	PAMS.ajaxDone({url:"/interface/getJson",successFn:successFn,data:{"serviceid":serviceid}});
});




       