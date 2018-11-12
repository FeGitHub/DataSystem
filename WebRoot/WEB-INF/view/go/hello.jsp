<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎界面</title>
  <script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script> 
  <script src="${BASE_PATH}/js/bootstrap.min.js"></script>
    <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/bootstrap.min.css" /><!--引进bootstrap的样式 -->
</head>
<body>
   <div class="panel-body">
	   <h1 align="center">欢迎界面</h1>
	  <a  id="test"  data-container="body" data-toggle="popover" data-placement="right" data-content="">
         Popover on 右侧
     </a>
	</div>
	<script>
$(function () { 
	$("[data-toggle='popover']").popover();
	$("#test").attr("data-content","测试成功");
});
</script>
</body>
</html>