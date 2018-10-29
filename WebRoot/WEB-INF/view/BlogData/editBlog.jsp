<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理页面</title>
<link href="${BASE_PATH}/css/bootstrap.min.css" rel="stylesheet">
<script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script>
<script src="${BASE_PATH}/js/bootstrap.min.js"></script>
<script src="${BASE_PATH}/js/Sco/sco.message.min.js"></script><!--引进信息提示框的js支持-->
<link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/main.css" />
<link href="${BASE_PATH}/css/Sco/sco.message.min.css" rel="stylesheet">
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
    <h2 align="center">博客数据编辑</h2>
    <div style="padding: 100px 100px 10px;" align="center">
	<form class="bs-example bs-example-form" role="form" style="width:450px;"  id="editform" >
		<div class="input-group">
			<span class="input-group-addon">id</span>
			<input type="text" class="form-control" value="${record.id}" name="id" disabled="disabled">
		</div>
		<br>
		<div class="input-group">
			<span class="input-group-addon">title</span>
			<input type="text" class="form-control" value="${record.title}" name="title">
		</div>
		<br>
		<div class="input-group">
			<span class="input-group-addon">content</span>
			<input type="text" class="form-control" value="${record.content}" name="content">
		</div><br>
		<button id="submitBtn" type="button" class="btn btn-default ">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${BASE_PATH}/go/goMenu" class="btn btn-default ">返回</a>
	</form>
</div>
    
</body>
<script type="text/javascript" src="${BASE_PATH}/js/editBlog.js"></script>
<script type="text/javascript">
var basepath= '${BASE_PATH}';
</script>
</html>