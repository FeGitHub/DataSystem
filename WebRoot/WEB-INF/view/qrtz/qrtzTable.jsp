<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/component/headResource.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>调度任务</title>
</head>
<body>
<div class="panel-body">
	<table id="table_id_example" class="display"><!--填充数据的表格，用id来标识这个表格 -->
    <thead>
        <tr>
            <th>调度任务</th>
            <th>任务组名</th>
            <th>任务描述</th>
            <th>具体类名</th>
            <th>表达式值</th>
            <th>操作</th>                 
        </tr>
    </thead>
    <tbody>      
    </tbody>
</table>
</div>
<script src="${BASE_PATH}/js/bootstrap.min.js"></script><!-- bootstrap的最小js支持 -->
<script src="${BASE_PATH}/js/qrtzTable.js"></script>
<script src="${BASE_PATH}/js/jquery.dataTables.min.js"></script><!--引进dataTables的js支持--> 
</body>

</html>