var dashboard="app-menu__icon fa fa-dashboard";
var laptop= "app-menu__icon fa fa-laptop";
var edit="app-menu__icon fa fa-edit";
var list="app-menu__icon fa fa-th-list";
var text="app-menu__icon fa fa-file-text";
$(function(){
	param_bulidMenu();	
});


/*****
 * 构建参数测试
 */
function param_bulidMenu(){
	var context = {
            "menuTree": [
                     {               
                         "url": "#",          
                         "title":"首页",
                         "icon":dashboard
                    },
                  {                  
                      "url": "#",         
                      "menuName":"数据表格",
                      "icon":laptop,
                      "subMenuList":[
	                                  {
	                                	  "subMenuName":"备忘提醒",
	                                	  "subMenuUrl":basepath+"/remind/goRemindList"
	                                  },
	                                  {
	                                	  "subMenuName":"调度任务",
	                                	  "subMenuUrl":basepath+"/qrtz/goQrtzTaskList"
	                                  }
                                 ]
                 },
                 {             
                     "url": "#",              
                     "menuName":"测试区域",
                     "icon":edit,
                     "subMenuList":[
	                                  {
	                                	  "subMenuName":"测试页面",
	                                	  "subMenuUrl":basepath+"/test/goTestPage"
	                                  }
                                ]
                },
                {           
                    "url": "#",              
                    "menuName":"案例页面",
                    "icon":list,
                    "subMenuList":[
	                                  {
	                                	  "subMenuName":"Ztree-bootstrap",
	                                	  "subMenuUrl":basepath+"/demo/goTreePage"
	                                  },
	                                  {
	                                	  "subMenuName":"ECharts",
	                                	  "subMenuUrl":basepath+"/demo/goEChartsPage"
	                                  }
                               ]
               },
               {
                   "url": "#",              
                   "menuName":"任务模块",
                   "icon":text,
                   "subMenuList":[
	                                  {
	                                	  "subMenuName":"工程任务",
	                                	  "subMenuUrl":basepath+"/task/goTaskList"
	                                  }
                              ]
              },
              ]
         };
	bulidApp(context);
}

/****
 * 构建菜单主体
 * @param data
 */
function bulidApp(data){
	var source = $("#appMenu").html();
	var template = Handlebars.compile(source);
	var _html = template(data);
	$(".app-menu").html(_html);
}