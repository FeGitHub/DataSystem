/***
 *  模板页面的js编写
 */
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