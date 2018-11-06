<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>弹出框插件</title>
 <script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script> 
</head>
<body>
   <div class="panel-body">
	   <h1 align="center">layer--打开注释测试功能</h1>
	</div>
</body>
 <script type="text/javascript" src="${BASE_PATH}/js/layer/layer.js"></script>
 <script type="text/javascript">
 /* layer.alert('您尚未登录，请登录！', {
     skin: 'btnClass',
     icon: 5,
     title: "提示",
     closeBtn: 0,
     yes: function () {
         $(location).attr("href", "/");
     },

 }); */
 //=============
	 /*  layer.confirm('您确定要退出吗？', {
            btn: ['确定', '取消'], //按钮
            skin: 'btnClass',
            icon: 2,
            title: "提示",
        }, function () {
            layer.closeAll('dialog');
           
            $(location).attr("href", "login.html");
        }); */
 
 /* layer.open({
	  type: 1,
	  skin: 'layui-layer-rim', //加上边框
	  area: ['420px', '240px'], //宽高
	  content: 'html内容'
	}); */
	
	layer.alert('墨绿风格，点击确认看深蓝', {
		  skin: 'layui-layer-molv' //样式类名
		  ,closeBtn: 0
		}, function(){
		  layer.alert('偶吧深蓝style', {
		    skin: 'layui-layer-lan'
		    ,closeBtn: 0
		    ,anim: 4 //动画类型
		  });
		});
 </script>
</html>