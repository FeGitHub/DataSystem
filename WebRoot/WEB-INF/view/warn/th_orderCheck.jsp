<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TH-订单预警</title>
<link href="${BASE_PATH}/css/bootstrap.min.css" rel="stylesheet">
<script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script>
<script src="${BASE_PATH}/js/bootstrap.min.js"></script>
<script src="${BASE_PATH}/js/toastr.min.js"></script>
<link href="${BASE_PATH}/css/toastr.min.css" type="text/css" rel="stylesheet">
<link href="${BASE_PATH}/css/buttons.css" type="text/css" rel="stylesheet">
<link href="${BASE_PATH}/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet">
<link href="${BASE_PATH}/css/orderCheck.css" type="text/css" rel="stylesheet">
 <script src="${BASE_PATH}/js/jquery.ztree.all.min.js"></script>
</head>
<body> 
    <div align="center">
			 <h2>TH-订单预警</h2>
		  		<button type="button" class="btn btn-primary" id="ztreeBtn">测试</button>	<hr>
		  		<button type="button" class="btn btn-primary" id="test1">toastrSuccess</button>
		  		<button type="button" class="btn btn-primary" id="test2">toastrInfo</button>
		  		<button type="button" class="btn btn-primary" id="test3">toastrError</button>
    </div>      
   
</body>
<script type="text/javascript">
	var basepath= '${BASE_PATH}';
	$(function(){
		
	});
	$("#test1").click(function(){
		toastrSuccess("dddd",3000);
	});
	$("#test2").click(function(){
		toastrInfo("dddd",3000);
	});
	$("#test3").click(function(){
		toastrError("dddd",3000);
	});
</script>
</html>

