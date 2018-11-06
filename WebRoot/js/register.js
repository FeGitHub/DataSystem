/*****
 *  通过正则表达式和bootstrap的基本样式进行表单校验
 */
var regUsername = /^[a-zA-Z_][a-zA-Z0-9_]{4,19}$/;//用户名
var regPasswordSpecial = /[~!@#%&=;':",./<>_\}\]\-\$\(\)\*\+\.\[\?\\\^\{\|]/;//特殊字符
var regPasswordAlpha = /[a-zA-Z]/;//字母
var regPasswordNum = /[0-9]/;//数字
var password;//密码
var check = [false, false, false, false, false, false];//分别对应六个输入框的数据的正确与否

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

//手机号码 ,test方法为正则表达式匹配的文本搜索字符串
var regPhoneNum = /^[0-9]{11}$/
$('.container').find('input').eq(4).change(function() {
    if (regPhoneNum.test($(this).val())) {
        success($(this), 4);
    } else {
        fail($(this), 4, '手机号码只能为11位数字');
    }
});

//短信验证码
var regMsg = /111111/;
$('.container').find('input').eq(5).change(function() {
    if (check[4]) {//有正确的电话号码
        if (regMsg.test($(this).val())) {
            success($(this), 5);
        } else {
            fail($(this), 5, '短信验证码错误');
        }
    } else {
        $('.container').find('input').eq(4).parent().parent().removeClass('has-success').addClass('has-error');
    }

});

//获取短信的校验码
$('#loadingButton').click(function() {	
    if (check[4]) {
        $(this).removeClass('btn-primary').addClass('disabled');
        $(this).html('<span class="red">59</span> 秒后重新获取');
        var secondObj = $('#loadingButton').find('span');
        var secondObjVal = secondObj.text();
        //验证码重新生成的时间检验
        function secondCounter() {
            var secondTimer = setTimeout(function() {
                secondObjVal--;
                secondObj.text(secondObjVal);
                secondCounter();
            }, 1000);
            if (secondObjVal == 0) {
                clearTimeout(secondTimer);
                $('#loadingButton').text('重新获取校验码');
                $('#loadingButton').removeClass('disabled').addClass('btn-primary');
            }
        }
        secondCounter();
    } else {
        $('.container').find('input').eq(4).parent().parent().removeClass('has-success').addClass('has-error');
    }

})

//提交前的表单的校验
$('#submit').click(function(e) {
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
    	//表单数据合法后操作
    	alert("成功登陆");
    }
});

//重置表单数据
$('#reset').click(function() {
    $('input').slice(0, 6).parent().parent().removeClass('has-error has-success');
    $('.tips').hide();
    $('.glyphicon-ok').hide();
    $('.glyphicon-remove').hide();
    check = [false, false, false, false, false, false];
});