<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/component/headResource.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <title>用户登录</title>  
     <link type="text/css" rel="stylesheet" href="${BASE_PATH}/css/login.css" />
</head>
<body>
    <form id="from" action="${BASE_PATH}/login" method="post">
    <div class="mycenter">
        <div class="mysign">
            <div class="col-lg-11 text-center text-info">
                <h2>请登录</h2>
            </div>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="username" placeholder="请输入账户名" required
                    autofocus />
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10">
                <input type="password" class="form-control" name="password" placeholder="请输入密码" required
                    autofocus />
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10 mycheckbox checkbox">
                <input type="checkbox" class="col-lg-1">记住密码</input>
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10">
                <button type="submit" id="btn" class="btn btn-success col-lg-12">
                                                 登录</button>
            </div>
        </div>
    </div>
    </form>
</body>
<script src="${BASE_PATH}/js/login.js"></script>
<script>
	
</script>
</html>