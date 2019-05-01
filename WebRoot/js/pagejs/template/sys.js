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