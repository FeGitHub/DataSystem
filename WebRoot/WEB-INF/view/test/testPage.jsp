<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>测试区域</title>
</head>
<body>
<div class="tile">
	测试模板
</div>

<div class="tile" id="testId">
		
</div>
<script id="complainListData" type="text/x-handlebars-template">
 {{#each this}}
		<div>
			<h1>{{title}}</h1>
		<div class="body">
			{{body}}
		</div>
	</div>
{{/each}}
</script>  
<script type="text/javascript" src="${BASE_PATH}/js/pagejs/testPage.js"></script>
</body>
</html>