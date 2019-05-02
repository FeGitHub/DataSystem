$(function(){
	
  })

$("#loginBtn").click(function(){
	submitForm();
});

document.onkeydown=function(event){
	event = event|| window.event;
	if (event.keyCode == 13){
	 submitForm();//提交
	}
}

function submitForm(){
	$.ajax({
		url:basepath+"/login",
		data:$("#loginForm").serialize(),
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.code==200){
				window.location.href=basepath+result.url;
			}else{
				layer.alert(result.msg, {icon: 5});
			}
		}
	});
}