/********
 *  工程任务树
 */
var tree="projectTree";
$(function() {
	initTable();
	initShowProgess();

});


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


/*****
 * 点击节点打开编辑框
 * @param event
 * @param treeId
 * @param treeNode
 * @param clickFlag
 */
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
	var data={
			"id":node.id,
			"startDate":node.startDate,
			"endDate":node.endDate,
			"taskName":node.name,
			"depiction":node.depiction,
			"schedule":node.checked
			};
  	$.ajax({
		url:basepath+"/task/updateProjectTask",
		type:"post",
		data:data,
		dataType:"json",
		success:function(data){	
			if(data.code==200){
				ztree.updateNode(node);
				toastrSuccess(data.msg,2000);				
			}else{
				toastrError(data.msg,2000);
			}		
		}
	});
	$("#seeMethodModal").modal("hide");
})

     

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
	 var zTree = $.fn.zTree.getZTreeObj(tree);
	 var nodeinfo={ 
	         pId: treeNode.id,
	         taskName: "新任务",
	         projectId:$("#projectId").val(),
	         userId:$("#userId").val()
	     };		
	  $.ajax({
			url:basepath+"/task/addProjectTask",
			type:"post",
			data:nodeinfo,
			dataType:"json",
			success:function(data){		
				if(data.code==200){
					 nodeinfo.id=data.id;
					 nodeinfo.name="新任务";
					 zTree.addNodes(treeNode, nodeinfo);   		
				}else{
					toastrError(data.msg,2000);
				}		  
			}
		});
		
}




/****
 * 
 * @param treeId
 * @param treeNode
 */
function removeHoverDom(treeId, treeNode) {
   // $("#addBtn_" + treeNode.tId).unbind().remove();
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
	        	"id":MyNode[0].id,
	        	"pId":MyNode[0].pId,
	        	"taskName" :MyNode[0].name,
	        	"projectId":MyNode[0].projectId,
	        	"startDate":MyNode[0].startDate,
	        	"endDate":MyNode[0].endDate,
	        	"userId":MyNode[0].userId,
	        	"depiction":MyNode[0].depiction,
	        	"schedule":MyNode[0].checked//选中的标志，表示已被处理
	        	});
	    }
	    var ztreeJson = JSON.stringify(params);
	    //console.log(ztreeJson);
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
 * 删除节点
 * @param event
 * @param treeId
 * @param treeNode
 */
function zTreeOnRemove(event, treeId, treeNode) {
	//console.log(treeNode.id);
	$.ajax({
		url:basepath+"/task/delProjectTask",
		type:"post",
		data:{"id":treeNode.id},
		dataType:"json",
		success:function(data){	
			if(data.code==200){
				toastrSuccess(data.msg,2000);				
			}else{
				toastrError(data.msg,2000);
			}		
		}
	});
	
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




//饼状图
function pieChart(done,undone,underway){
	 var dom = document.getElementById("pieChart");
	 var pieChart = echarts.init(dom);
	 pieChart.showLoading({
		  text : '正在加载数据'
		});  //增加提示
	 var app = {};
	 var option = null;
	 option = {
	     title : {
	         text: '工程进度',
	         subtext: '',
	         x:'center'
	     },
	     tooltip : {
	         trigger: 'item',
	         formatter: "{a} <br/>{b} : {c} ({d}%)"
	     },
	     legend: {
	         orient: 'vertical',
	         left: 'left',
	         data: ['已完成','未开始','进行中']
	     },
	     series : [
	         {
	             name: '工程进度',
	             type: 'pie',
	             radius : '55%',
	             center: ['50%', '60%'],
	             data:[
	                 {value:done, name:'已完成'},
	                 {value:undone, name:'未开始'},
	                 {value:underway, name:'进行中'}                
	             ],
	             itemStyle: {
	                 emphasis: {
	                     shadowBlur: 10,
	                     shadowOffsetX: 0,
	                     shadowColor: 'rgba(0, 0, 0, 0.5)'
	                 }
	             }
	         }
	     ]
	 };
	 ;
	 if (option && typeof option === "object") {
		 pieChart.hideLoading();  //提示关闭
		 pieChart.setOption(option, true);
	 }
}

/****
 * 更新工程任务
 */
function updateProjectTask(node){  
	var data={
			"id":node.id,
			"startDate":node.startDate,
			"endDate":node.endDate,
			"taskName":node.name,
			"depiction":node.depiction,
			"schedule":node.checked
			};
  	$.ajax({
		url:basepath+"/task/updateProjectTask",
		type:"post",
		data:data,
		dataType:"json",
		success:function(data){	
			if(data.code==200){
				toastrSuccess(data.msg,2000);				
			}else{
				toastrError(data.msg,2000);
			}		
		}
	});
}

/****
 * 初始化数据表格
 */
function initTable(){
	//设置	  
	 var setting = {
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
					onClick: onClick,//点击后的事件
					onRemove: zTreeOnRemove//删除节点后的事件
				}
	        };
	  $.ajax({
			url:basepath+"/task/getProjectTree",
			type:"post",
			data:{"projectId":$("#projectId").val()},
			dataType:"json",
			success:function(data){		
				    //console.log(data);
					 $.fn.zTree.init($("#"+tree), setting, data.projectTree);
					 pieChart(parseInt(data.done),parseInt(data.undone),parseInt(data.underway));
					 $(".overlay").remove();
			}
		});
}


/***
 * 展示剩余进度时间
 */
function showProgess(){
	layer.msg($("#progress").data("msg"));

}


/***
 * 初始化任务进度条
 */
function initShowProgess(){
   if(parseInt($("#progress").data("pnum"))>8){
	   var msg=$("#progress").data("msg");
	   $("#progress").text(msg);
   }
}

