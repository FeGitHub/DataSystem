<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>测试表单</title>
</head>
<body>
   <div class="panel-body">
	   <h1 align="center">欢迎界面</h1>
	   <form action="/remind/addRemindDetail" method="post" class="form-horizontal ">
			<div class="form-group">
				<label class="col-md-3 control-label" for="account-input">登录名</label>
				<div class="col-md-9">
					<input type="text" id="account-input" name="record.remindName" class="form-control" placeholder="登录名">
				</div>
			</div>
		<div class="form-group">
			<label class="col-md-3 control-label" for="realname-input">真实姓名</label>
			<div class="col-md-9">
				<input type="text" id="realname-input" name="record.remindText" class="form-control" placeholder="真实姓名">
			</div>
		</div>
		<button class="btn">提交</button>
	</form>
</body>
</html>