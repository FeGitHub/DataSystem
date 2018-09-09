/***
 * 登录页面的js
 */
$(function(){
	if (window != top) {//防止session过期，拦截器设置的跳转页面在iframe打开
		top.location.href = location.href; 
	}

})