<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
        <h1>DS</h1>
      </div>
      <div class="login-box">
        <form class="login-form" action="${BASE_PATH}/login">
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
              <div class="animated-checkbox">
                <label>
                  <input type="checkbox"><span class="label-text">保持登陆</span>
                </label>
              </div>
              <p class="semibold-text mb-2"><a href="#" data-toggle="flip">忘记密码 ?</a></p>
            </div>
          </div>
          <div class="form-group btn-container">
         <!--form表单下的button没有指明types时，默认是submit -->
          <button class="btn btn-primary btn-block"><i class="fa fa-sign-in fa-lg fa-fw"></i>登  陆</button> 
          </div>
        </form>
      </div>
    </section>
  </body>
</html>