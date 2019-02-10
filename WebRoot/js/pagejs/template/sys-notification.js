//模拟通知参数
var app_notification__title="你有4个新的通知";

//信息提示图标
var mailIcon="fa fa-envelope fa-stack-1x fa-inverse";//邮件图标
var workIcon="fa fa-hdd-o fa-stack-1x fa-inverse";
var transactionIcon="fa fa-money fa-stack-1x fa-inverse";
var style1="fa fa-circle fa-stack-2x text-primary";
var style2="fa fa-circle fa-stack-2x text-danger";
var style3="fa fa-circle fa-stack-2x text-success";

/*$(function(){
	param_bulidNotification();	
});*/


/*****
 * 构建参数测试
 */
function param_bulidNotification(){
	var context = {
            "notification": [
                  {
                      "url": "gsgf",
                      "message": "Lisa sent you a mail",
                      "style":style1,
                      "meta":"1分钟前",
                      "icon":mailIcon
                 },
                  {
               	  "url": "gsgf",
                     "message": "Lisa sent you a mail",
                     "style":style2,
                     "meta":"1分钟前",
                     "icon":workIcon
                 },
                  {
               	  "url": "gsgf",
                     "message": "Lisa sent you a mail",
                     "style":style3,
                     "meta":"1分钟前",
                     "icon":transactionIcon
                  }
              ]
         };
	bulidNotification(context);
}

/****
 * 构建信息通知主体
 * @param data
 */
function bulidNotification(data){
	var source = $("#appNotification").html();
	var template = Handlebars.compile(source);
	var _html = template(data);
	$(".app-notification__content").html(_html);
}