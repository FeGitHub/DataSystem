$("#loginBtn").click(function(){
	$.ajax({
		url:basepath+"/login",
		data:$("#loginForm").serialize(),
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.code==200){
				window.location.href=basepath+result.url;
			}else{
				layer.alert(result.msg, {icon: 6});
			}
		}
	});
});