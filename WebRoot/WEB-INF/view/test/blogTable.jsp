<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>博客数据</title>
</head>
<body>
<div class="panel-body">
	<table id="table_id_example" class="display"><!--填充数据的表格，用id来标识这个表格 -->
    <thead>
        <tr>
            <th>第一列</th>
            <th>第二列</th>
            <th>第三列</th>
            <th>第四列</th>                 
        </tr>
    </thead>
    <tbody>      
    </tbody>
</table>
</div >
<script src="${BASE_PATH}/js/bootstrap.min.js"></script><!-- bootstrap的最小js支持 -->
<script src="${BASE_PATH}/js/blogTable.js"></script>
<script type="text/javascript">
var basepath= '${BASE_PATH}';
</script>
</body>

</html>