<%@ page language="java" pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/view/component/headResource.jsp" %> --%>
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
	<%-- <h2 align="center">预警规则</h2>
	<hr><a href="${BASE_PATH}/warn/goWarnRuleAdd" class='btn table_btn btn-danger btn-sm'  target='_blank'>新增</a><hr> --%>
	<div class="panel-body">
	<table id="table_id_warnRule" class="display"><!--填充数据的表格，用id来标识这个表格 -->
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
<script type="text/javascript">
var basepath= '${BASE_PATH}';//用于单独js文件的路径定位
</script>
</body>
</html>