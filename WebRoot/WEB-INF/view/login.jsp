<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script> 
    	<script type="text/javascript" src="${BASE_PATH}/plug/layer/layer.js"></script><!--弹出框-->
    <link rel="stylesheet" type="text/css" href="${BASE_PATH}/css/test/main.css">
    <link rel="stylesheet" type="text/css" href="${BASE_PATH}/css/font-awesome.min.css">
    <title>登陆界面</title>
  </head>
  <body>
    <section class="material-half-bg">
      <div class="cover"></div>
    </section>
    <section class="login-content">
      <div class="logo">
        <h1>PAMS</h1>
      </div>
      <div class="login-box">
        <form class="login-form" id="loginForm">
          <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>登  陆</h3>
          <div class="form-group">
            <label class="control-label"></label>
            <input class="form-control" type="text" placeholder="账号" autofocus name="account">
          </div>
          <div class="form-group">
            <label class="control-label"></label>
            <input class="form-control" type="password" placeholder="密码" name="password">
          </div>
          <div class="form-group">
            <div class="utility">
              <p class="semibold-text mb-2"><a href="${BASE_PATH}/goRegister" data-toggle="flip">注册账号</a></p>
              <p class="semibold-text mb-2"><a href="#" data-toggle="flip">忘记密码 ?</a></p>
            </div>
          </div>
          <div class="form-group btn-container">
          <button type="button" class="btn btn-primary btn-block" id="loginBtn"><i class="fa fa-sign-in fa-lg fa-fw"></i>登  陆</button> 
          </div>
        </form>
      </div>
    </section>
    	<script type="text/javascript">
   		 var basepath= '${BASE_PATH}';
  		</script>
      <script src="${BASE_PATH}/js/pagejs/login.js"></script> 
  </body>
</html>