$("#delBtn").click(function(){
		layer.confirm('您确定要删除吗？', {			
		    btn: ['确定', '取消'], //按钮
		    skin: 'btnClass',
		    icon: 2,
		    title: "提示",
	}, function () {
		 layer.closeAll('dialog');     
	});
});