<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/component/headResource.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>备忘提醒</title>
</head>
<body>
	<h2 align="center">备忘提醒</h2>
	<hr><a href="${BASE_PATH}/warn/goWarnRuleAdd" class='btn table_btn btn-danger btn-sm'  target='_blank'>新增</a><hr>
	<table id="table_id_example" class="display"><!--填充数据的表格，用id来标识这个表格 -->
    <thead>
        <tr>
            <th>事项简称</th>
            <th>具体事项</th>
            <th>截止时间</th>
            <th>提醒邮箱</th>            
        </tr>
    </thead>
    <tbody>      
    </tbody>
</table>
</body>
<script src="${BASE_PATH}/js/remindList.js"></script>
<script type="text/javascript">
var basepath= '${BASE_PATH}';//用于单独js文件的路径定位
</script>
</html>