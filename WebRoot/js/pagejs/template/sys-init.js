/***
 *  模板页面的js编写
 */
$(function(){
	param_bulidMenu();//构建菜单
	param_bulidNotification();//构建信息通知栏	
})

//登出
/*$("#loginOut").click(function(){
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
});*/