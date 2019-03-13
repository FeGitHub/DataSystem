
var regUsername = /^[a-zA-Z_][a-zA-Z0-9_]{4,19}$/;//用户名
var regPasswordSpecial = /[~!@#%&=;':",./<>_\}\]\-\$\(\)\*\+\.\[\?\\\^\{\|]/;//特殊字符
var regPasswordAlpha = /[a-zA-Z]/;//字母
var regPasswordNum = /[0-9]/;//数字
var checkMail = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
var password;//密码
var check = [false, false, false, false, false];
var countdown=60; 

$(function(){
	/*if($.cookie("captcha")){
		countdown = $.cookie("captcha");
		settime($("#btn"));
	}*/
	
});

function settime(val) { 
	if (countdown == 0) { 
	val.removeAttribute("disabled");    
	val.value="免费获取验证码"; 
	countdown = 60; 
	//$.cookie("captcha", countdown, {path: '/', expires: (1/86400)*countdown});
	} else { 
	val.setAttribute("disabled", true); 
	val.value="重新发送(" + countdown + ")"; 
	countdown--; 
	//$.cookie("captcha", countdown, {path: '/', expires: (1/86400)*countdown});
	setTimeout(function() { 
		settime(val) 
		},1000) 
	} 	
}

function sendCode(val){	
	$obj=$('.container').find('input').eq(4);	
	if (checkMail.test($obj.val())) {
		sendAction();
		settime(val);
    } else {
    	 fail($obj, 4, '无效邮箱地址');
    }
	
}


//校验成功函数
function success(Obj, counter) {
    Obj.parent().parent().removeClass('has-error').addClass('has-success');
    $('.tips').eq(counter).hide();
    $('.glyphicon-ok').eq(counter).show();
    $('.glyphicon-remove').eq(counter).hide();
    check[counter] = true;
}

// 校验失败函数
function fail(Obj, counter, msg) {
    Obj.parent().parent().removeClass('has-success').addClass('has-error');
    $('.glyphicon-remove').eq(counter).show();
    $('.glyphicon-ok').eq(counter).hide();
    $('.tips').eq(counter).text(msg).show();
    check[counter] = false;
}

// 用户名匹配
$('.container').find('input').eq(0).change(function() {
    if (regUsername.test($(this).val())) {
        success($(this), 0);
    } else if ($(this).val().length < 5) {
        fail($(this), 0, '用户名太短，不能少于5个字符');
    } else {
        fail($(this), 0, '用户名只能为英文数字和下划线,且不能以数字开头')
    }
});


// 匹配字母、数字、特殊字符至少两种的函数
function atLeastTwo(password) {
    var a = regPasswordSpecial.test(password) ? 1 : 0;
    var b = regPasswordAlpha.test(password) ? 1 : 0;
    var c = regPasswordNum.test(password) ? 1 : 0;
    return a + b + c;

}
//密码校验
$('.container').find('input').eq(1).change(function() {
    password = $(this).val();
    if ($(this).val().length < 8) {
        fail($(this), 1, '密码太短，不能少于8个字符');
    } else {
        if (atLeastTwo($(this).val()) < 2) {
            fail($(this), 1, '密码中至少包含字母、数字、特殊字符的两种')
        } else {
            success($(this), 1);
        }
    }
});


// 再次输入密码校验
$('.container').find('input').eq(2).change(function() {
    if ($(this).val() == password) {
        success($(this), 2);
    } else {
        fail($(this), 2, '两次输入的密码不一致');
    }
});


//验证码校验
$('.container').find('input').eq(3).change(function() {
    var IsBy = $.idcode.validateCode();
    if (IsBy) {
        success($(this), 3);
    } else {
        fail($(this), 3, '验证码输入错误');
    }
});



$('.container').find('input').eq(4).change(function() {
    if (checkMail.test($(this).val())) {
        success($(this), 4);
    } else {
        fail($(this), 4, '无效邮箱地址');
    }
});






//提交前的表单的校验
$('#submit').click(function(e) {
	//====
	console.log($("#register").serialize());
	//===
    if(!check.every(function(value){
            return value == true
        })) {
        e.preventDefault();
        for (key in check) {
            if (!check[key]) {
                $('.container').find('input').eq(key).parent().parent().removeClass('has-success').addClass('has-error')
            }
        }
    }else{  	
    	$.ajax({
    		url:basepath+"/user/register",
    		data:$("#register").serialize(),
    		type:"post",
    		dataType:"json",
    		success:function(data){
    			if(data.code==200){
    				toastrSuccess(data.msg,3000);
    			}else{
    				toastrError(data.msg,3000);
    			}
    		}
    	});
    	 
    }
});

//重置表单数据
$('#reset').click(function() {
    $('input').slice(0, 5).parent().parent().removeClass('has-error has-success');
    $('.tips').hide();
    $('.glyphicon-ok').hide();
    $('.glyphicon-remove').hide();
    check = [false, false, false, false, false];
});

function back(){
	window.location.href=basepath+"/";
}

function sendAction(){
	var mailAdress=$("#mailAdress").val();
	$.ajax({
		url:basepath+"/demo/sendCode",
		data:{"mailAdress":mailAdress},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.code==200){
				
			}else{
				
			}
		}
	});
}
 