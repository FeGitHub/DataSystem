<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单预警判定</title>
<link href="${BASE_PATH}/css/bootstrap.min.css" rel="stylesheet">
<script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script>
<script src="${BASE_PATH}/js/bootstrap.min.js"></script>
<%-- <link href="${BASE_PATH}/css/buttons.css" type="text/css" rel="stylesheet"> --%>
<link href="${BASE_PATH}/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet">
<link href="${BASE_PATH}/css/orderCheck.css" type="text/css" rel="stylesheet">
 <script src="${BASE_PATH}/js/jquery.ztree.all.min.js"></script>
</head>
<body> 
    <div align="center">
			 <h2>订单预警判定</h2>
		  		<ul id="tree" class="ztree" style="width:230px; overflow:auto;"></ul> 
		  		物料：<input type="text" readonly="readonly" id="materValue"> 
		  		节点：<input type="text" readonly="readonly" id="nodeValue">
		  		单价：<input type="text" id="priceValue">
		  		数量：<input type="text" id="amountValue">
		  		<button type="button" class="btn btn-primary" id="ztreeBtn">测试</button>		
    </div>      
    <hr>
    <div align="center">
    	  <button  class="btn btn-primary" id="testBtn">预警判断</button>
    	  <a href="/go/goMenu" class="btn btn-primary">返回主页</a>
    </div>
    <script type="text/javascript" src="${BASE_PATH}/js/orderCheck.js"></script>
    <script type="text/javascript">
	var basepath= '${BASE_PATH}';
    </script>
</body>

</html>

