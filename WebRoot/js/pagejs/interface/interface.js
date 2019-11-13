

/***
 * 测试接口数据
 */
$("#test").click(function(){	
	var serviceid=$("#serviceid").val();
	$.ajax({
		url:basepath+"/interface/getJson",
		type:"post",
		data:{
			"serviceid":serviceid,
		},
		dataType:"json",
		error: function (data) {
            alert(data);
        },
		success:function(data){
			alert(JSON.stringify(data));					
		}
	});
	//简化请求
	//var successFn=function(data){
	//	alert(JSON.stringify(data));		
	//}
	//ajaxDone({url:basepath+"/interface/getJson",successFn:successFn,data:{"serviceid":serviceid}});
});



/***
 *  进一步封装Ajax
 * @param param
 */
 function ajaxDone(param){
	 //检验基本参数是否完整
	 var url=param.url;
	 if(url==null||url==''){
		 alert("请求资源地址不能为空！");
		 return;
	 }
	 var postData=param.data==null?{}:param.data;//请求数据
	 var successFn=param.successFn==null?function(data){}:param.successFn;//请求成功方法
	 $.ajax({
			url:url,
			type:"post",
			data:postData,
			dataType:"json",
			error: function (data) {
	            alert(data);
	        },
			success:function(data){
				successFn(data);					
			}
		});
	 
	 
 }
      
	   
		
       