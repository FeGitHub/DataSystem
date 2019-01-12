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
                         "url": basepath+"/go/goMenu",          
                         "name":"首页",
                         "icon":dashboard
                    },
                  {                  
                      "url": "#",         
                      "name":"数据表格",
                      "icon":laptop,
                      "subMenuList":[
	                                  {
	                                	  "name":"备忘提醒",
	                                	  "url":basepath+"/remind/goRemindList"
	                                  },
	                                  {
	                                	  "name":"调度任务",
	                                	  "url":basepath+"/qrtz/goQrtzTaskList"
	                                  }
                                 ]
                 },
                 {             
                     "url": "#",              
                     "name":"测试区域",
                     "icon":edit,
                     "subMenuList":[
	                                  {
	                                	  "name":"测试页面",
	                                	  "url":basepath+"/test/goTestPage"
	                                  }
                                ]
                },
                {           
                    "url": "#",              
                    "name":"案例页面",
                    "icon":list,
                    "subMenuList":[
	                                  {
	                                	  "name":"Ztree-bootstrap",
	                                	  "url":basepath+"/demo/goTreePage"
	                                  },
	                                  {
	                                	  "name":"ECharts",
	                                	  "url":basepath+"/demo/goEChartsPage"
	                                  }
                               ]
               },
               {
                   "url": "#",              
                   "name":"任务模块",
                   "icon":text,
                   "subMenuList":[
	                                  {
	                                	  "name":"工程任务",
	                                	  "url":basepath+"/task/goTaskList"
	                                  }
                              ]
              },
              ]
         };
	
	//bulidApp(context);

		$.ajax({
			url:basepath+"/menu/getTreeMenu",
			type:"post",
			dataType:"json",
			success:function(context){
				bulidApp(context)
			}
		});
	//========
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