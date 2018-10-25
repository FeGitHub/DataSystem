<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>调度任务</title>
<link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/jquery.dataTables.min.css" /><!--引进dataTables的样式 -->
<link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/bootstrap.min.css" /><!--引进bootstrap的样式 -->
<link href="${BASE_PATH}/css/Sco/sco.message.min.css" rel="stylesheet">
<script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script><!--引进jquery的最小支持 -->
<script src="${BASE_PATH}/js/jquery.dataTables.min.js"></script><!--引进dataTables的js支持-->
<script src="${BASE_PATH}/js/Sco/sco.message.min.js"></script><!--引进信息提示框的js支持-->
</head>
<body>
<h2 align="center">调度任务</h2>
	<hr><a href="" class='btn table_btn btn-danger btn-sm'  target='_blank'>新增</a><hr>
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
</body>
<script src="${BASE_PATH}/js/bootstrap.min.js"></script><!-- bootstrap的最小js支持 -->
<script src="${BASE_PATH}/js/qrtzTable.js"></script>
<script type="text/javascript">
var basepath= '${BASE_PATH}';//用于单独js文件的路径定位
</script>
</html>