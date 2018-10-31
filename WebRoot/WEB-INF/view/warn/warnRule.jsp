<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>预警规则</title>
<link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/jquery.dataTables.min.css" />
<link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/bootstrap.min.css" />
<link href="${BASE_PATH}/css/Sco/sco.message.min.css" rel="stylesheet">
<script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script>
<script src="${BASE_PATH}/js/jquery.dataTables.min.js"></script>
<script src="${BASE_PATH}/js/Sco/sco.message.min.js"></script> 
</head>
<body>
	<div class="panel-body">
	<table id="table_id_warnRule" class="display">
    <thead>
        <tr>
            <th>预警名称</th>
            <th>预警类型</th>
            <th>最小范围</th>
            <th>最大范围</th>
            <th>预警处理人</th>
            <th>影响物料类型</th>
            <th>操作</th>                 
        </tr>
    </thead>
    <tbody>      
    </tbody>
</table>
</div>
<script src="${BASE_PATH}/js/warnRule.js"></script>
</body>
</html>