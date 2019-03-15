/********
 *  工程任务树
 */
var setting;//ztree的设置
var tree="projectTree";
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
					onClick: onClick,//点击后的事件
					onRemove: zTreeOnRemove
				}
	        };
	  $.ajax({
			url:basepath+"/task/getProjectTree",
			type:"post",
			data:{"projectId":$("#projectId").val()},
			dataType:"json",
			success:function(data){		
				   // console.log(data);
					 $.fn.zTree.init($("#"+tree), setting, data); 			
			}
		});
  
   
});


function beforeClick(treeId, treeNode, clickFlag) {
	/*var url=treeNode.url;
	treeNode.url="wwww";*/
}

/****
 * function 激活时间输入框
 */
function activateDatetimepicker($object){	    	
	$object.datetimepicker({
         language:"zh-CN",
         bootcssVer:3,
         format: 'yyyy-mm-dd hh:ii:ss', 
         autoclose:true,
         todayHighlight: true,
         minView:0,		     
         weekStart:1
     }); 
}



function onClick(event, treeId, treeNode, clickFlag) {
    var htm = $($('#remindTemp').html());
	var _html='<div>'+htm[0].outerHTML+'</div>';
	$("#seeMethodModal .modal-body").html(_html);
	$("#seeMethodModal").modal("show");	
	showData(treeNode)
	activateDatetimepicker($('.form_date'));
	$("#nodeId").val(treeNode.id);
}

/****
 * 节点信息的详细修改
 */
$("body").on("click","#submitBtn",function(){
	var nodeId=$("#nodeId").val();
	var ztree=$.fn.zTree.getZTreeObj(tree);
	var node=ztree.getNodeByParam('id',nodeId);
	node.startDate=$("#startDate").val();
	node.endDate=$("#endDate").val();
	node.name=$("#taskName").val();
	node.depiction=$("#depiction").val();
	ztree.updateNode(node);
	$("#seeMethodModal").modal("hide");
})

      /****
	     * function 激活时间输入框
	     */
	    function activateDatetimepicker($object){	    	
	    	$object.datetimepicker({
		         language:"zh-CN",
		         bootcssVer:3,
		         format: 'yyyy-mm-dd hh:ii:ss', 
		         autoclose:true,
		         todayHighlight: true,
		         minView:0,		     
		         weekStart:1
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
    if(childrenSize>=99){
		 alert("子节点数太多");
		 return;
	 }
	 var preCode=treeNode.id+"00";
	 var nodeId=parseInt(preCode)+childrenSize+1;
	 var zTree = $.fn.zTree.getZTreeObj(tree);
	 var node=zTree.getNodeByParam('id',1);
     zTree.addNodes(treeNode, {      
    	 id:nodeId,
         pId: treeNode.id,
         name: "任务" + nodeId,
         projectId:$("#projectId").val(),
         userId:node.userId
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
	        params.push({ 
	        	"cId": MyNode[0].id,
	        	"pId":MyNode[0].pId,
	        	"taskName" :MyNode[0].name,
	        	"projectId":MyNode[0].projectId,
	        	"startDate":MyNode[0].startDate,
	        	"endDate":MyNode[0].endDate,
	        	"userId":MyNode[0].userId,
	        	"depiction":MyNode[0].depiction,
	        	"checked":MyNode[0].checked//选中的标志，表示已被处理
	        	});
	    }
	    var ztreeJson = JSON.stringify(params);
	    console.log(ztreeJson);
	    return ztreeJson;
}

/****
 * 更新工程树
 */
$("#updateBtn").click(function(){
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

/****
 *  移除节点后对兄弟节点id的更新
 * @param event
 * @param treeId
 * @param treeNode
 */
function zTreeOnRemove(event, treeId, treeNode) {
	var ztree=$.fn.zTree.getZTreeObj(treeId);
	var id=treeNode.id.toString();	
	var indexNode=id.substring(id.length-2,id.length);//删除的节点在原兄弟节点中的序号
	var pNode = treeNode.getParentNode();
	var pNodeLength=pNode.children.length;//删除后剩余的兄弟节点数
	var diff=parseInt(pNodeLength)-parseInt(indexNode)+1;//应该被更新的兄弟节点数
	var pNode;
	var updateNode;
	var updateId;
	for(var i=1;i<=diff;i++){
	  updateNode=parseInt(id)+parseInt(i);
      pNode=ztree.getNodeByParam('id',updateNode);
      updateId=parseInt(updateNode)-1;
      pNode.id=updateId;    
	}
}
/***
 * 编辑数据回显
 * @param taskName
 * @param startDate
 * @param endDate
 */
function showData(treeNode){
	   $("#taskName").val(treeNode.name);
	   $("#startDate").val(treeNode.startDate);
	   $("#endDate").val(treeNode.endDate);
	   $("#depiction").val(treeNode.depiction);
	}