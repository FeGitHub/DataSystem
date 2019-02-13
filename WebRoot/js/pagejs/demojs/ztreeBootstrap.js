//ztree-bootstrap

/********
 * testZNodes:每一个节点的信息，父id,自身id，自身节点名
 */
var testZNodes;//展示的节点信息
var setting;//ztree的设置
var newCount = 1;//新节点默认序号
$(document).ready(function() {	
	//设置	  
	  setting = {
	            view: {
	                addHoverDom: addHoverDom,
	                removeHoverDom: removeHoverDom,
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
	paramToZtreeBootstrap();
	//====
	/*	$.ajax({
			url:basepath+"/demo/getZtreeJsonFromDB",
			type:"post",
			dataType:"json",
			success:function(ztree){	
				$.fn.zTree.init($("#treeDemo"), setting, ztree);  			
			}
		});*/
    $.fn.zTree.init($("#treeDemo"), setting, testZNodes);    
  
   
});
//=============

function beforeClick(treeId, treeNode, clickFlag) {
	/*var url=treeNode.url;
	treeNode.url="wwww";*/
}
function onClick(event, treeId, treeNode, clickFlag) {
	layer.prompt({title: '修改菜单链接',value:treeNode.url, formType: 3}, function(text, index){
		  layer.close(index);	
		  treeNode.url=text;
		    layer.msg('修改链接为：'+ text);
		});
}
//==================
/***
 * 测试
 */
$("#testBtn").click(function(){
	  var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
	  var ztreeJson=getZtreeNodesInfo(zTreeObj);
	  $.ajax({
		  url:basepath+"/demo/getZtreeJsonFromView",
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
 * 添加节点
 * @param treeId
 * @param treeNode
 */
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId +
        "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function() {//新增节点事件
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.addNodes(treeNode, {
            id: (100 + newCount),
            pId: treeNode.id,
            name: "new node" + (newCount++)
        });
        return false;
    });
};

/****
 * 移除节点
 * @param treeId
 * @param treeNode
 */
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
};

/****
 *得到树形节点的信息
 */
function getZtreeNodesInfo(zTreeObj){
	   var nodes = zTreeObj.getNodes();//当前数的节点信息
	   var act=zTreeObj.transformToArray(nodes);//转换成数组
	   var MyNode="";//用于被遍历的节点的载体
	   var str="";//最终拼接的信息	  
	   var params = [];  
	    for(var i=0;i<act.length;i++)
	    {
	    	zTreeObj.selectNode(act[i],true);//选中目前遍历的节点
	        MyNode=zTreeObj.getSelectedNodes();	
	        //========
	       //console.log(MyNode[0]);
	        //==========
	        if(MyNode[0].pId==null){
	        	MyNode[0].pId=0;
	        }
	        params.push({ "id": MyNode[0].id, "pId":MyNode[0].pId,"name" :MyNode[0].name,"url":MyNode[0].url});
	    }
	    var ztreeJson = JSON.stringify(params);
	    console.log(ztreeJson);
	    return ztreeJson;
}

/*******
 * tree-bootstrap测试参数准备
 */
function paramToZtreeBootstrap(){
//第二个
	  var zNodes =[
	           	{id:1, pId:0, name:"[core] 基本功能 演示",open:true},
	           	{id:101, pId:1, name:"标准 JSON 数据"},
	           	{id:102, pId:1, name:"简单 JSON 数据"},
	           	
	           	{id:2, pId:0, name:"单选框功能 演示", open:false},
	           	{id:201, pId:2, name:"Checkbox 勾选操作"},
	           	{id:206, pId:2, name:"Checkbox演示"},
	           	
	           	{id:3, pId:0, name:"编辑功能 演示", open:false},
	           	{id:301, pId:3, name:"拖拽 节点 基本控制"},
	           	{id:302, pId:3, name:"拖拽 节点 高级控制"}
	           	];
  //第三个
	  var zNodes3 =[
	   			{ id:1, pId:0, name:"zTree Home", url:"http://www.treejs.cn/", target:"_blank"},
	   			{ id:2, pId:0, name:"zTree in Google", url:"http://code.google.com/p/jquerytree/", target:"_blank"},
	   			{ id:3, pId:0, name:"zTree in Iteye", url:"http://ztreeapi.iteye.com/", target:"_blank"},
	   			{ id:4, pId:0, name:"Nothing...", url:"", target:"_blank", click:"alert('我是不会跳转的...');"}
	   		];
	  
 //第四个
	  var zNodes4 =[
		           	{id:1, pId:0, name:"[core] 基本功能 演示",open:true},
		           	{id:101, pId:1, name:"标准 JSON 数据", url:"http://www.treejs.cn/", target:"_blank"},
		           	{id:102, pId:1, name:"简单 JSON 数据",url:"http://www.treejs.cn/"},
		           	
		           	{id:2, pId:0, name:"单选框功能 演示", open:false},
		           	{id:201, pId:2, name:"Checkbox 勾选操作", url:"http://www.treejs.cn/", target:"_blank"},
		           	{id:206, pId:2, name:"Checkbox演示", url:"http://www.treejs.cn/", target:"_blank"},
		           	
		           	{id:3, pId:0, name:"编辑功能 演示", open:false},
		           	{id:301, pId:3, name:"拖拽 节点 基本控制", url:"http://www.treejs.cn/", target:"_blank"},
		           	{id:302, pId:3, name:"拖拽 节点 高级控制", url:"http://www.treejs.cn/", target:"_blank"}
		           	];
	  
	 //第五个
	  var zNodes5 =[
	            	{id:1, pId:0, name:"数据表格",open:true},
		           	{id:101, pId:1, name:"备忘提醒", url:"/remind/goRemindList"},
		           	{id:102, pId:1, name:"调度任务",url:"/qrtz/goQrtzTaskList"},
		           	
		           	{id:2, pId:0, name:"测试区域", open:true},
		           	{id:201, pId:2, name:"测试页面", url:"/test/goTestPage"},
		    
		           	{id:3, pId:0, name:"案例页面", open:true},
		           	{id:301, pId:3, name:"Ztree-bootstrap", url:"/demo/goTreePage"},
		           	{id:302, pId:3, name:"ECharts", url:"/demo/goEChartsPage"},
		           	
		           	{id:4, pId:0, name:"任务模块", open:true},
		           	{id:401, pId:4, name:"工程任务", url:"/task/goTaskList"},
		           	];
	  testZNodes=zNodes5;	  

}
  
       