/********
 * testZNodes:每一个节点的信息，父id,自身id，自身节点名
 */
var testZNodes;//展示的节点信息
var setting;//ztree的设置
var newCount = 1;//新节点默认序号
var tree="treeDemo";
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
	  var zNodes =[{id:1, pId:0, name:"工程任务",projectId:$("#projectId").val(),open:true}];
	   $.fn.zTree.init($("#"+tree), setting, zNodes);
  
   
});


function beforeClick(treeId, treeNode, clickFlag) {
	/*var url=treeNode.url;
	treeNode.url="wwww";*/
}
function onClick(event, treeId, treeNode, clickFlag) {
	layer.prompt({title: '修改菜单链接',value:treeNode.projectId, formType: 3}, function(text, index){
		  layer.close(index);	
		  treeNode.projectId=text;
		    layer.msg('修改链接为：'+ text);
		});
}

/***
 * 测试
 */
$("#testBtn").click(function(){
	 var zTreeObj = $.fn.zTree.getZTreeObj(tree);
	  var ztreeJson=getZtreeNodesInfo(zTreeObj);
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
	 var zTree = $.fn.zTree.getZTreeObj(tree);
     zTree.addNodes(treeNode, {      
    	 id:nodeId,
         pId: treeNode.id,
         name: "任务" + nodeId,
         projectId:$("#projectId").val()
     });
}




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
	        if(MyNode[0].pId==null){
	        	MyNode[0].pId=0;
	        }
	        params.push({ "cId": MyNode[0].id, "pId":MyNode[0].pId,"taskName" :MyNode[0].name,"projectId":MyNode[0].projectId,"startDate":MyNode[0].startDate,"endDate":MyNode[0].endDate});
	    }
	    var ztreeJson = JSON.stringify(params);
	    console.log(ztreeJson);
	    return ztreeJson;
}

 /****
  * 创建工程任务树
  */
/*$("#addBtn").click(function(){
	var json={"planStartDate":"2019-03-12 00:27:39","plantFinshDate":"2019-03-12 00:27:39","projectName":"测试"};
	$.ajax({
		url:basepath+"/task/createProject",
		type:"post",
		data:json,
		dataType:"json",
		success:function(data){	
			if(data.code==200){
				toastrSuccess(data.msg,2000);
				$("#projectId").val(data.projectId);
				 var zNodes =[{id:1, pId:0, name:"工程任务",projectId:data.projectId,open:true}];
				 $.fn.zTree.init($("#"+tree), setting, zNodes); 
			}else{
				toastrError("操作失败",3000);
			}
			
		}
	});
});*/
    

$("#submitBtn").click(function(){
	  var zTreeObj = $.fn.zTree.getZTreeObj(tree);
	  var pTaskJson=getZtreeNodesInfo(zTreeObj);
	  	$.ajax({
		url:basepath+"/task/submitProject",
		type:"post",
		 data:{"pTaskJson":pTaskJson},
		dataType:"json",
		success:function(data){	
			if(data.code==200){
				toastrSuccess(data.msg,2000);				
			}else{
				toastrError(data.msg,2000);
			}		
		}
	});
	});	