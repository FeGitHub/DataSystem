/********
 * testZNodes:每一个节点的信息，父id,自身id，自身节点名
 */
var dashboard="app-menu__icon fa fa-dashboard";
var laptop= "app-menu__icon fa fa-laptop";
var edit="app-menu__icon fa fa-edit";
var list="app-menu__icon fa fa-th-list";
var text="app-menu__icon fa fa-file-text";

var testZNodes;//展示的节点信息
var setting;//ztree的设置
var newCount = 1;//新节点默认序号
var switchFlag=1;//0--静态资源  1--数据库
$(document).ready(function() {	
	//设置	  
	initMenu();	
});

/****
 * 树形展示设置
 */
function initSetting(){
	  setting = {
	            view: {
	                addHoverDom: addHoverDomBtn,
	                removeHoverDom: removeHoverDomBtn,
	                selectedMulti: false
	            },
	            check: {
	                enable: true
	            },
	            data: {
	                simpleData: {
	                    enable: true
	                },	           
	            },         
	            edit: {
	                enable: true
	            },
	            callback: {
					beforeClick: beforeClick,//点击前最开始的事件
					onClick: onClick//点击后的事件
				}
	        };
}

/***
 * 初始化菜单
 */
function initMenu(){
	initSetting();
	if(switchFlag==0){
		paramToZtreeBootstrap();
		$.fn.zTree.init($("#menuTree"), setting, testZNodes);   
	}else{
		$.ajax({
			url:basepath+"/menu/getZtreeJsonFromDB",
			type:"post",
			dataType:"json",
			success:function(ztree){
				$(".overlay").remove();
				$.fn.zTree.init($("#menuTree"), setting, ztree);  			
			}
		});   
	}
}

function beforeClick(treeId, treeNode, clickFlag) {
}
function onClick(event, treeId, treeNode, clickFlag) {
	layer.prompt({title: '修改菜单链接',value:treeNode.url, formType: 3}, function(text, index){
		  layer.close(index);	
		  treeNode.url=text;
		    layer.msg('修改链接为：'+  treeNode.url);
		});
}

/***
 * 修改菜单数据
 */
$("#updateMenuBtn").click(function(){
	  var zTreeObj = $.fn.zTree.getZTreeObj("menuTree");
	  var ztreeJson=getZtreeNodesInfo(zTreeObj);
	  $.ajax({
		  url:basepath+"/menu/updateZtreeJson",
		  type:"post",
		  data:{"ztreeJson":ztreeJson},
	      dataType:"json",
	      success:function(data){
	    	  if(data.code==200){
	    			toastrSuccess(data.msg,3000);
	    	  }else{
	    			toastrError(data.msg,3000);
	    	  }
	      }
	  });
});

/****
 * 控制台打印菜单信息
 */
$("#testMenuBtn").click(function(){
	  var zTreeObj = $.fn.zTree.getZTreeObj("menuTree");
	  var ztreeJson=getZtreeNodesInfo(zTreeObj);
	  console.log(ztreeJson);
})


/****
 * 添加节点
 * @param treeId
 * @param treeNode
 */
function addHoverDomBtn(treeId, treeNode) {	
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0||treeNode.pId>2) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId +
        "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);//显示的新增按钮
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function() {//新增节点事件
    	addNodeInfo(treeNode);
        return false;
    });
};

/***
 * 将新增的节点的信息添加到父节点内
 * @param treeNode
 */
function addNodeInfo(treeNode){
	 var childrenSize;
	 if(treeNode.children!=null){
		 childrenSize=treeNode.children.length;		
	 }else{
		 childrenSize=0;
	 }
	 var preCode=treeNode.id+"00";	 
	 var nodeId=parseInt(preCode)+childrenSize+1;
	 var zTree = $.fn.zTree.getZTreeObj("menuTree");
     zTree.addNodes(treeNode, {
        // id: (100 + newCount),
    	 id:nodeId,
         pId: treeNode.id,
        // name: "new node" + (newCount++)
         name: "new node" + nodeId
     });
}

/****
 * 移除节点
 * @param treeId
 * @param treeNode
 */
function removeHoverDomBtn(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
};

/****
 *得到树形节点的信息
 */
function getZtreeNodesInfo(zTreeObj){
	   var nodes = zTreeObj.getNodes();//当前数的节点信息
	   var act=zTreeObj.transformToArray(nodes);//转换成数组
	   var MyNode="";//用于被遍历的节点的载体
	   var params = [];  
	   var indexNodePid;
	   var indexNodeIcon;
	    for(var i=0;i<act.length;i++)
	    {
	    	zTreeObj.selectNode(act[i],true);//选中目前遍历的节点
	        MyNode=zTreeObj.getSelectedNodes();	  
	        indexNodePid=MyNode[0].pId==null?0:MyNode[0].pId;
	        indexNodeIcon=MyNode[0].icon==null?dashboard:MyNode[0].icon;	      
	        params.push({ "id": MyNode[0].id, "pId":indexNodePid,"name" :MyNode[0].name,"url":MyNode[0].url,"icon":indexNodeIcon,"checked":MyNode[0].checked});
	    }
	    var ztreeJson = JSON.stringify(params);
	    return ztreeJson;
}

/*******
 * tree-bootstrap测试参数准备
 */
function paramToZtreeBootstrap(){
	  var zNodes5 =[
	                {id:1, pId:0, name:"菜单编辑",open:true,checked:false},
	                
	                {id:101, pId:1, name:"首页",icon:dashboard,open:true,checked:false},
	                
	            	{id:102, pId:1, name:"数据表格",icon:laptop,open:true,checked:false},
		           	{id:10201, pId:102, name:"备忘提醒", url:"/remind/goRemindList",checked:false},
		           	{id:10202, pId:102, name:"调度任务",url:"/qrtz/goQrtzTaskList",checked:false},
		           	
		           	{id:103, pId:1, name:"测试区域",icon:edit, open:true,checked:false},
		           	{id:10301, pId:103, name:"测试页面", url:"/test/goTestPage",checked:false},
		    
		           	{id:104, pId:1, name:"案例页面", icon:list,open:true,checked:false},
		           	{id:10401, pId:104, name:"Ztree-bootstrap", url:"/demo/goTreePage",checked:false},
		           	{id:10402, pId:104, name:"ECharts", url:"/demo/goEChartsPage",checked:false},
		           	
		           	{id:105, pId:1, name:"任务模块",icon:text, open:true,checked:false},
		           	{id:10501, pId:105, name:"工程任务", url:"/task/goTaskList",checked:false},
		           	
		           	{id:106, pId:1, name:"系统设置",icon:text, open:true,checked:false},
		           	{id:10601, pId:106, name:"菜单设置", url:"/menu/goMenu",checked:false},
		           	];
	  testZNodes=zNodes5;	  

}
  
       