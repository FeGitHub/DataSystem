/***
 * 订单预警判定的js
 */
$(function(){
	 treeMenu();
});


/***
 * 树形物料菜单的处理
 * ztree
 */
function treeMenu(){	
    var setting={
        check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
        },
        callback : {
            onCheck : onCheck
          }      
    };
    
    //================
  /*  var zTreeNodes1=[
{"name":"毕设","open":true,children:[
    {"name":"前端设计","open":true,children:[
			{"name":"bootstrap页面框架"},		
          ]},
    {"name":"后端设计","open":true,children:[
                                   			{"name":"后台逻辑","open":true,children:[
                                   			                    			{"name":"普通业务逻辑"},	
                                   			                    			{"name":"机器学习算法"},
                                   			                             ]}
                                   		
                                             ]},
    {"name":"数据库设计","open":true,children:[
                          			{"name":"表的设计"},  
                          			{"name":"..."},  
                                    ]}
]}
];*/  
    //==============
    var url=basepath+"/warn/getTree";
    $.post(url,function(zTreeNodes){
    	$.fn.zTree.init($("#tree"), setting, zTreeNodes); 
	  },"json");
}

/***
 * 判定触发
 */
$("#ztreeBtn").click(function(){	
	var node=$("#nodeValue").val();
	var price=$("#priceValue").val();
	var amount=$("#amountValue").val();
	console.log(node+","+price+","+amount);
	
	
});

/***
 * ztree勾选触发事件
 */
function onCheck(e, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	 var nodes = treeObj.getCheckedNodes(true);  	
	 for (var i = 0; i < nodes.length; i++) {//例子PS：nodes[i].name
		  $("#materValue").val(nodes[i].name); 
		  $("#nodeValue").val(nodes[i].id);
	  }	 
  /*//取消单选的代码
	  * for (var i = 0; i < nodes.length; i++) {
	  nodes[i].checked = false;
	  treeObj.updateNode(nodes[i]);
 }*/	
}

/***
 * 测试
 */
$("#testBtn").click(function(){	
	var url=basepath+"/warn/test";
	$.post(url,function(){
		alert("dd");
	});
});