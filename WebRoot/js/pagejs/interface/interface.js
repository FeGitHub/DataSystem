

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
});
      
	   
		
       