<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		  <title>数据展示</title>
		  <script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script> 
		  <script src="${BASE_PATH}/js/bootstrap.min.js"></script>
		  <script src="${BASE_PATH}/js/toastr.min.js"></script>
		  <script type="text/javascript" src="${BASE_PATH}/plug/layer/layer.js"></script>
		  <link href="${BASE_PATH}/css/bootstrap.min.css" type="text/css" rel="stylesheet">		
          <link href="${BASE_PATH}/css/toastr.min.css" type="text/css" rel="stylesheet">		
		  <link rel="stylesheet" type="text/css" href="${BASE_PATH}/css/pagecss/go/csvdata.css">		 
	</head>
	<body>
	   <input type="text"  id="obj" style="display:none;">
		<div class="container">
			<table class="table" id="tableList">
			<!--   <thead> 
			      <tr><td></td><td></td></tr> 
			  </thead>	 -->
			      <tr><td>计划花费时间</td><td><span id="planTime"></span></td></tr>
			      <tr><td>工程任务数量</td><td><span id="projectTaskNum"></span></td></tr>			
			      <tr><td>工程时间内系统总任务数</td><td><span id="taskInProject"></span></td></tr>
			      <tr><td>预计花费时间</td><td><span id="actualyTime"></span></td></tr>
			      <tr><td>目前进度情况</td><td><span id="process"></span></td></tr>
			   <tfoot><tr><td colspan='2'></td></tr></tfoot>										
			</table>					
		</div>	
		<script type="text/javascript">
   		 var basepath= '${BASE_PATH}';
       </script>		
		 <script src="${BASE_PATH}/js/pagejs/go/showAnalyse.js"></script> 
	</body>
</html>