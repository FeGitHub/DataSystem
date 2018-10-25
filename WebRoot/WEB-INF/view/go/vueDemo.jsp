<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>vueæ¡ä¾</title>
	<meta charset="UTF-8">
	<script src="${BASE_PATH}/js/vue.js"></script>
	<script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script><!--å¼è¿jqueryçæå°æ¯æ -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>

	<div id="first">
		<h1>å§åï¼{{name}}</h1><!-- ç´æ¥ä»dataä¸­åå¼ -->
		<h1>å·¥ä½ï¼{{work}}</h1><!-- ç´æ¥ä»dataä¸­åå¼ -->
		<p>ææå¼:{{sayhello()}}</p><!-- ä»å½æ°ä¸­åè¿åå¼ -->
		<p>{{sayAxiosData()}}</p><!-- æ§è¡å½æ° -->
		<p>{{sayAjaxData()}}</p><!-- æ§è¡å½æ° -->
		

	</div>
	<script src="${BASE_PATH}/js/vueTest.js"></script>
	<script type="text/javascript">
	$(function(){
		var url="/json/data3.json";
		$.post(url,function(data){	
			alert(data.c[0].name);		
		},"json");
	});
	</script>
</body>
</html>