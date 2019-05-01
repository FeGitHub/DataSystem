var dashboard="app-menu__icon fa fa-dashboard";
var laptop= "app-menu__icon fa fa-laptop";
var edit="app-menu__icon fa fa-edit";
var list="app-menu__icon fa fa-th-list";
var text="app-menu__icon fa fa-file-text";

var testZNodes;//展示的节点信息
var setting;//ztree的设置
var switchFlag=1;//0--静态资源  1--数据库
var tree="menuTree";
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
					onClick: onClick,//点击后的事件
					onRemove: zTreeOnRemove
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
			success:function(menu){				
				$(".overlay").remove();
				$.fn.zTree.init($("#menuTree"), setting, menu);  			
			}
		});   
	}
}

function beforeClick(treeId, treeNode, clickFlag) {
}

/****
 * 菜单节点点击事件
 * @param event
 * @param treeId
 * @param treeNode
 * @param clickFlag
 */
function onClick(event, treeId, treeNode, clickFlag) {
/*	layer.prompt({title: '修改菜单链接',value:treeNode.url, formType: 3}, function(text, index){
		  layer.close(index);	
		  treeNode.url=text;
		    layer.msg('修改链接为：'+  treeNode.url);
		});*/
	    var htm = $($('#menuTemplate').html());
		var _html='<div>'+htm[0].outerHTML+'</div>';
		$("#seeMethodModal .modal-body").html(_html);
		var nodeId=treeNode.id;
		if(nodeId==""||nodeId==null){
			toastrError("节点id为空");
			return;
		}
		$("#nodeId").val(nodeId);
		showData(treeNode);
		$("#seeMethodModal").modal("show");		
}

/***
 * 修改菜单数据(更新整个树)
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
	 var zTree = $.fn.zTree.getZTreeObj(tree);
	 var nodeinfo={ 
	         pId: treeNode.id,
	         name: "新菜单",	        
	     };		
	  $.ajax({
			url:basepath+"/menu/addMenuNode",
			type:"post",
			data:nodeinfo,
			dataType:"json",
			success:function(data){		
				if(data.code==200){
					 nodeinfo.id=data.id;
					 nodeinfo.name="新菜单";
					 zTree.addNodes(treeNode, nodeinfo);   		
				}else{
					toastrError(data.msg,2000);
				}		  
			}
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
	   var nodes = zTreeObj.getNodes();//当前树的节点信息
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
	        params.push({ 
	        	"id": MyNode[0].id, 
	        	"pId":indexNodePid,
	        	"name" :MyNode[0].name,
	        	"url":MyNode[0].url,
	        	"icon":indexNodeIcon,
	        	"checked":MyNode[0].checked,
	        	"menu_level":MyNode[0].menu_level
	        	});
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
	                
	                {id:2, pId:1, name:"首页",icon:dashboard,open:true,checked:false},
	                
	            	{id:3, pId:1, name:"数据表格",icon:laptop,open:true,checked:false},
		           	{id:4, pId:3, name:"备忘提醒", url:"/remind/goRemindList",checked:false},
		           	{id:5, pId:3, name:"调度任务",url:"/qrtz/goQrtzTaskList",checked:false},
		           	
		           	{id:6, pId:1, name:"测试区域",icon:edit, open:true,checked:false},
		           	{id:7, pId:6, name:"测试页面", url:"/test/goTestPage",checked:false},
		    
		           	{id:8, pId:1, name:"案例页面", icon:list,open:true,checked:false},
		           	{id:9, pId:8, name:"Ztree-bootstrap", url:"/demo/goTreePage",checked:false},
		           	{id:10, pId:8, name:"ECharts", url:"/demo/goEChartsPage",checked:false},
		           	
		           	{id:11, pId:1, name:"任务模块",icon:text, open:true,checked:false},
		           	{id:12, pId:11, name:"工程任务", url:"/task/goTaskList",checked:false},
		           	
		           	{id:13, pId:1, name:"系统设置",icon:text, open:true,checked:false},
		           	{id:14, pId:13, name:"菜单设置", url:"/menu/goMenu",checked:false},
		           	];
	  testZNodes=zNodes5;	  

}
  

function zTreeOnRemove(event, treeId, treeNode) {
	/*var ztree=$.fn.zTree.getZTreeObj(treeId);
	var id=treeNode.id.toString();
	console.log("indexNode===="+id);
	var indexNode=id.substring(id.length-2,id.length);
	console.log("indexNode===="+parseInt(indexNode));
	var pNode = treeNode.getParentNode();
	if(pNode==null){
		return;
	}
	var pNodeLength=pNode.children.length;
	console.log("父节点的孩子数"+pNodeLength);
	var diff=parseInt(pNodeLength)-parseInt(indexNode)+1;
	console.log("diff===="+diff);
	var pNode;
	var updateNode;
	var updateId;
	for(var i=1;i<=diff;i++){
	  updateNode=parseInt(id)+parseInt(i);
      console.log("updateNode=="+updateNode);
      pNode=ztree.getNodeByParam('id',updateNode);
      updateId=parseInt(updateNode)-1;
      pNode.id=updateId;
      console.log("pNode.id=="+pNode.id);
      console.log("=========");
	}*/
}      


/***
 * 编辑数据回显
 * @param taskName
 * @param startDate
 * @param endDate
 */
function showData(treeNode){
	   $("#name").val(treeNode.name);
	   $("#url").val(treeNode.url); 
	   var check=1;
	   if(treeNode.menu_level!=1){
		   check=2;
	   }
	   $(":radio[name='visable'][value='" +check+ "']").prop("checked", "checked");
	}




/***
 * 更新节点
 */
function  updataNode(){
	if($("#menuForm").validationEngine('validate')){
		//以下为节点更新信息
		var nodeId=$("#nodeId").val();
		if(nodeId==""||nodeId==null){
			toastrError("节点id为空");
			return;
		}
		var name=$("#name").val();
		var url=$("#url").val();
		var check=$("input[name='visable']:checked").val();		
		var ztree=$.fn.zTree.getZTreeObj(tree);		
		var node=ztree.getNodeByParam('id',nodeId);		
		node.name=name;
		node.url=url;
		node.menu_level=check;
		var pNode = node.getParentNode();//父节点
		//以下处理相关逻辑
		if(check==1){
			pNode.menu_level=1;
		}
		//更新操作
		ztree.updateNode(node);	
		if(pNode!=null){
			ztree.updateNode(pNode);
		}
		//本节点是父节点
		if(node.isParent&&check==2){//父节点不可见，其下节点必不可见
			 var childrenNodes = node.children;
			 if (childrenNodes){
				 for (var i = 0; i < childrenNodes.length; i++) {
	                 childrenNodes[i].menu_level=check;	  
	                 ztree.updateNode(childrenNodes[i]);
	            } 
			 }
			
		}
	  	$("#seeMethodModal").modal("hide");
	}
}


/****
 * 节点信息的详细修改
 */
$("body").on("click","#updateBtn",function(){
	var nodeId=$("#nodeId").val();
	if(nodeId!=""){//更新节点
		updataNode();
		//toastrSuccess("节点更新成功",3000);
	}	
})