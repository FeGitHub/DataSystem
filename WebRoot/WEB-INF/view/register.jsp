<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户注册</title>
<script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script>  
 <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/pagecss/user/register/register.css" />
  <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/pagecss/user/register/jquery.idcode.css" />
 <link type="text/css" rel="styleSheet"  href="${BASE_PATH}/css/bootstrap.min.css" />
  <script src="${BASE_PATH}/js/bootstrap.min.js"></script>
  <script src="${BASE_PATH}/js/toastr.min.js"></script><!--信息提示框-->
  <link href="${BASE_PATH}/css/toastr.min.css" type="text/css" rel="stylesheet">
</head>
<body>
 <section class="material-half-bg">
      <div class="cover"></div>
    </section>
    <div class="panel-body">
    <!--验证表单开始-->
<div class="container">
    <div class="col-md-6 ">
        <form id="register">
            <div class="form-group has-feedback">
                <label for="username">用户名</label>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                    <input id="username" name="account" class="form-control" placeholder="请输入用户名" maxlength="20" type="text">
                </div>
                <span style="color:red;display: none;" class="tips"></span>
                <span style="display: none;" class=" glyphicon glyphicon-remove form-control-feedback"></span>
                <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <label for="password">密码</label>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                    <input id="password" class="form-control" placeholder="请输入密码" maxlength="20" type="password">
                </div>
                <span style="color:red;display: none;" class="tips"></span>
                <span style="display: none;" class="glyphicon glyphicon-remove form-control-feedback"></span>
                <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <label for="passwordConfirm">确认密码</label>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                    <input id="passwordConfirm" name="password" class="form-control" placeholder="请再次输入密码" maxlength="20" type="password">
                </div>
                <span style="color:red;display: none;" class="tips"></span>
                <span style="display: none;" class="glyphicon glyphicon-remove form-control-feedback"></span>
                <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-7">
                    <div class="form-group has-feedback">
                        <label for="idcode-btn">验证码</label>
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-qrcode"></span></span>
                            <input id="idcode-btn" class="form-control" placeholder="请输入验证码" maxlength="4" type="text">
                        </div>
                        <span style="color:red;display: none;" class="tips"></span>
                        <span style="display:none;" class="glyphicon glyphicon-remove form-control-feedback"></span>
                        <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
                    </div>
                </div>
                <div class="col-xs-5" style="padding-top: 30px">
                    <div id="idcode" style="background: transparent;"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-7">
            <div class="form-group has-feedback">
                <label for="mailAdress">邮箱地址</label>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
                    <input id="mailAdress" name="mail" class="form-control" placeholder="请输入邮箱地址" maxlength="20" type="text">
                </div>
                <span style="color:red;display: none;" class="tips"></span>
                <span style="display: none;" class="glyphicon glyphicon-remove form-control-feedback"></span>
                <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>
            </div>
               <div class="col-xs-5" style="padding-top: 30px">
                    <input class="form-control btn btn-primary"  type="button" id="btn" value="获取验证码" onclick="sendCode(this)" /> 
                </div>
            </div>
            <div class="row">
                <div class="col-xs-7">
                    <div class="form-group has-feedback">
                        <label for="idcode-btn">邮箱验证码</label>
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-qrcode"></span></span>
                            <input id="mailcode-btn" name="formCode" class="form-control" placeholder="请输入邮箱验证码" maxlength="6" type="text">
                        </div>
                        <span style="color:red;display: none;" class="tips"></span>
                        <span style="display:none;" class="glyphicon glyphicon-remove form-control-feedback"></span>
                        <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
                    </div>
                </div>
                <div class="col-xs-5" style="padding-top: 30px">
                    <div id="idcode" style="background: transparent;"></div>
                </div>
            </div>
            <div class="form-group">
                <input class="form-control btn btn-primary" id="submit" value="立&nbsp;&nbsp;即&nbsp;&nbsp;注&nbsp;&nbsp;册" type="button">
            </div>
            <div class="form-group">
                <input value="重置" id="reset" class="form-control btn btn-danger" type="reset">
            </div>
            <div class="form-group">
                <input value="返回"  class="form-control btn btn-primary" onclick="back()" type="button">
            </div>
        </form>
    </div>
</div>
<!--验证表单结束-->
</div>
<script type="text/javascript">
   		 var basepath= '${BASE_PATH}';
</script>
  <script src="${BASE_PATH}/js/plugjs/jquery.cookie.js"></script>
  <script src="${BASE_PATH}/js/pagejs/user/register/idcode.js"></script>
  <script src="${BASE_PATH}/js/pagejs/user/register/register.js"></script>
  <script type="text/javascript"> 
 
</script> 
</body>
</html>
