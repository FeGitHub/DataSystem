<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/component/headResource.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理页面</title>
<link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/main.css" />
<link href="${BASE_PATH}/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet">
 <script src="${BASE_PATH}/js/jquery.ztree.all.min.js"></script>
<style type="text/css">
	.input-group-addon{
	   width:70px;
	}
	.input-group{
	  width:380px;
	}
</style>
</head>
<body>
	<!--头部导航开始开始 -->
	<div class="navbar navbar-duomi navbar-static-top" role="navigation">
    	<div class="container-fluid">
     		<div class="navbar-header">
           	<a class="navbar-brand" href="${BASE_PATH}/go/goMenu" id="logo">数据管理系统</a>
			</div>
		</div>
    </div>
    <!--头部导航结束 -->
    <h2 align="center">预警规则新增</h2>
    <div style="padding: 100px 100px 10px;" align="center">
	<form class="bs-example bs-example-form" role="form" style="width:450px;"  id="editform">
		<div class="input-group">
			<span class="input-group-addon">预警名称</span>
			<input type="text" class="form-control"  name="title">
		</div>
		<br>
		<div class="input-group">
			<span class="input-group-addon">预警类型</span>
			<input type="text" class="form-control"  name="content">
		</div>
		<br>
		<div class="input-group">
			<span class="input-group-addon">波动范围</span>
			<input type="text" class="form-control"  name="content">
		</div>
		<br>
		<div class="input-group">
			<span class="input-group-addon">物料类型</span>
			<input type="text" class="form-control"  name="content">
		</div>
		<br>
		<div class="input-group">
			<span class="input-group-addon">预处理人</span>
			<input type="text" class="form-control"  name="content">
		</div>
		<br>
		<button id="submitBtn" type="button" class="btn btn-default ">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${BASE_PATH}/go/goMenu" class="btn btn-default ">返回</a>
	</form>
	
</div>
    <hr>
    <div align="center">
    	<ul id="tree" class="ztree" style="width:230px; overflow:auto;"></ul>
    	<button  class="btn btn-default" id="testBtn">测试</button>
    </div>     
</body>
<script type="text/javascript" src="${BASE_PATH}/js/warnRuleAdd.js"></script>
<script type="text/javascript">
	var basepath= '${BASE_PATH}';
</script>
</html>