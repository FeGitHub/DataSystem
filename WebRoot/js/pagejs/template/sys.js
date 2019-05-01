/***
 *  模板页面的js编写
 */


//登出
$("#loginOut").click(function(){
		layer.confirm('您确定要退出吗？', {			
				    btn: ['确定', '取消'], //按钮
				    skin: 'btnClass',
				    icon: 2,
				    title: "提示",
		}, function () {
		    layer.closeAll('dialog');       
		    var url=basepath+"/login/signOut";
		    $(location).attr("href",url );
		});
		
	
});




//登出
$("#account").click(function(){
		layer.confirm('您确定要退出吗？', {			
				    btn: ['确定', '取消'], //按钮
				    skin: 'btnClass',
				    icon: 2,
				    title: "提示",
		}, function () {
		    layer.closeAll('dialog');       
		    var url=basepath+"/login/signOut";
		    $(location).attr("href",url );
		});	
});






/****
 * 查询
 */
$("#search").click(function(){	
   if($("#keyword").val()==""){
	   return;
   }
	$.ajax({
		url:basepath+"/menu/goMenuUrlByName",
		type:"post",
		dataType:"json",
		data:{"keyword":$("#keyword").val()},
		success:function(data){
			if(data!=null){
				window.location.href=basepath+data.url;
			}else{
				$("#keyword").val("查无记录");
			}		
		},
		error:function(){
			
		}
	});
});


//用户账号检验操作
$("#dropdown-item-userid").click(function(){
	layer.prompt({title: '输入原密码，并确认', formType: 1}, function(pass, index){		
		$.ajax({
			url:basepath+"/user/checkUser",
			type:"post",
			dataType:"json",
			data:{"pass":pass},
			success: function (data) {
				layer.close(index);
				if(data.code==200){					 
					    var htm = $($('#pamsUserTemplate').html());
						var _html='<div>'+htm[0].outerHTML+'</div>';			
						$("#pamsModal .modal-body").html(_html);
						$("#pamsModal").modal("show");	
				} else{
					toastrError(data.msg,3000);
				}  				 
			},
			error: function () {
				toastrError("请求失败");
			}
		});
		});
});



function pamsCheckMail(field, rules, i, options){
	var re=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
	var mail=$("#mail").val();
	if(!re.test(mail)){
		 rules.push('required');
		 return "* 无效的邮箱地址";
	}	
}




function pamsCheckPwd(field, rules, i, options){
   var pamsPwd=$("#pamsPwd").val();
   var pamsPwdcheck=$("#pamsPwdcheck").val();
   if(pamsPwd!=pamsPwdcheck){
	   rules.push('required');
		 return "* 两次输入的密码不一致";
   } 
}

/****
 *  更新用户信息
 */
$("body").on("click","#pamsUpdateBtn",function(){
	if($("#pamsUserForm").validationEngine('validate')){
	
		$("#pamsModal").modal("hide");			
	}
});

