/********
 *  工程任务树
 *  
 */
var tree="projectTree";
$(function() {
	initTreeAndPieChart();
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
    var htm = $($('#taskTemplate').html());
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
$("body").on("click","#updateBtn",function(){
	var nodeId=$("#nodeId").val();
	if(nodeId!=""){//更新节点
		updataNodeAjax();
	}else{//更新工程
		updateProjectAjax();
	}
	
})

/***
 * 更新节点的ajax动作
 */
function  updataNodeAjax(){
	if($("#taskForm").validationEngine('validate')){
		var nodeId=$("#nodeId").val();
		var ztree=$.fn.zTree.getZTreeObj(tree);
		var node=ztree.getNodeByParam('id',nodeId);
		node.startDate=$("#startDate").val();
		node.endDate=$("#endDate").val();
		node.name=$("#taskName").val();
		node.description=$("#description").val();
		node.underway=$("input[name='underway']:checked").val();
		
		var data={
				"id":node.id,
				"startDate":node.startDate,
				"endDate":node.endDate,
				"taskName":node.name,
				"description":node.description,
				"underway":node.underway
				};
		var successFn=function(result){
			if(result.code==200){
				ztree.updateNode(node);
				toastrSuccess(result.msg,2000);				
			}else{
				toastrError(result.msg,2000);
			}		
		}
		PAMS.ajaxDone({data:data,url:"/task/updateProjectTask",successFn:successFn}); 
	  	$("#seeMethodModal").modal("hide");
	}
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
	 var zTree = $.fn.zTree.getZTreeObj(tree);
	 var nodeinfo={ 
	         pId: treeNode.id,
	         taskName: "新任务",
	         projectId:$("#projectId").val(),
	         userId:$("#userId").val(),
	         underway:0
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
					 toastrSuccess(data.msg,2000);		
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
	   var nodes = zTreeObj.getNodes();//当前树的节点信息
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
	        	"description":MyNode[0].description,
	        	"underway":MyNode[0].underway
	        	});
	    }
	    var ztreeJson = JSON.stringify(params);
	    return ztreeJson;
}

/****
 * 更新工程树
 */
/*$("#updateBtn").click(function(){
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
	});	*/

/****
 * 删除节点
 * @param event
 * @param treeId
 * @param treeNode  
 */
function zTreeOnRemove(event, treeId, treeNode) {
	var ids=getAllChildrenNodesStr(treeNode);
	var pNode = treeNode.getParentNode();
	var pId=pNode.id;
	$.ajax({
		url:basepath+"/task/delProjectTask",
		type:"post",
		data:{"pId":pId,"ids":ids},
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
	   $("#description").val(treeNode.description);
	   $(":radio[name='underway'][value='" + treeNode.underway + "']").prop("checked", "checked");
	}




//饼状图
function pieChart(undone,underway,done){
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
	         formatter: "{a} <br/>{b} : {c} ({d}%)",
	         enterable: true
	              
	     },
	     legend: {
	         orient: 'vertical',
	         left: 'left',
	         data: ['未开始','进行中','已完成']
	     },
	     series : [
	         {
	             name: '工程进度',
	             type: 'pie',
	             radius : '55%',
	             center: ['50%', '60%'],
	             data:[	                    
	                 {value:undone, name:'未开始'},
	                 {value:underway, name:'进行中'},
	                 {value:done, name:'已完成'}
	              
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
		 pieChart.on('click',function(a,b){
			 showchecked(parseInt(a.dataIndex));
			 })
	 }
}

/****
 * 更新工程任务
 */
/*function updateProjectTask(node){  
	var data={
			"id":node.id,
			"startDate":node.startDate,
			"endDate":node.endDate,
			"taskName":node.name,
			"description":node.description,
			"underway":node.underway
			};
	console.log(data);
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
*/

/***
 * 处理根节点不能删除
 * @param treeId
 * @param treeNode
 * @returns {Boolean}
 */
function setRemoveBtn(treeId, treeNode) { 
    if(treeNode.level == 0) {
        return false;
    }
    else {
        return true;
    }
}


/****
 * 初始化饼状图数据
 */
function initTreeAndPieChart(){
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
	                enable: true,
	                showRemoveBtn: setRemoveBtn
	            },
	            callback: {
					onClick: onClick,//点击后的事件
					//onRemove: zTreeOnRemove,//删除节点后的事件
					beforeRemove: beforeRemove
				}
	        };
	  $.ajax({
			url:basepath+"/task/getProjectTree",
			type:"post",
			data:{"projectId":$("#projectId").val()},
			dataType:"json",
			success:function(data){		
					 $.fn.zTree.init($("#"+tree), setting, data.projectTree);
					 pieChart(parseInt(data.undone),parseInt(data.underway),parseInt(data.done));
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


/****
 * 节点移除前的确认
 * @param treeId 树的id
 * @param treeNode 当前操作节点
 * @returns {Boolean}  
 */
function beforeRemove(treeId, treeNode) {
	var ids=getAllChildrenNodesStr(treeNode);
	var pNode = treeNode.getParentNode();
	var pId=pNode.id;
	var msg="您确定要删除"+treeNode.name+"吗？";
	layer.confirm(msg, {			
	    btn: ['确定', '取消'], //按钮
	    skin: 'btnClass',
	    icon: 2,
	    title: "提示",
}, function () {
   layer.closeAll('dialog');         
   remove(ids,pId,treeNode);  
});
	return false;
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


//递归，获取所有子节点
function getAllChildrenNodes(treeNode,result){
      if (treeNode.isParent) {
        var childrenNodes = treeNode.children;
        if (childrenNodes) {
            for (var i = 0; i < childrenNodes.length; i++) {
                result += ',' + childrenNodes[i].id;
                result = getAllChildrenNodes(childrenNodes[i], result);
            }
        }
    }
    return result;
}

/***
 * 得到叶子节点字符串
 * @returns 
 */
function getAllChildrenNodesStr(treeNode){
	var str ='' ;
    str = getAllChildrenNodes(treeNode,str);
    str = str + ',' + treeNode.id;
    str = str.substr(1);
    console.log("str:"+str);
    return str;
}

/***
 * 节点信息移除
 */
function remove(ids,pId,treeNode){
	var zTreeObj = $.fn.zTree.getZTreeObj(tree);
	$.ajax({
		url:basepath+"/task/delProjectTask",
		type:"post",
		data:{"pId":pId,"ids":ids},
		dataType:"json",
		success:function(data){	
			if(data.code==200){
				toastrSuccess(data.msg,2000);
				zTreeObj.removeNode(treeNode);
			}else{
				toastrError(data.msg,2000);
			}		
		} 
	}); 
}

/****
 * 展示选中饼状图的对应状态节点
 * @param way
 * 0-未开始
 * 1-进行中
 * 2-已结束
 */
function showchecked(way){
	   var zTreeObj = $.fn.zTree.getZTreeObj(tree);
	   var nodes = zTreeObj.getNodes();//当前树的节点信息
	   var act=zTreeObj.transformToArray(nodes);//转换成数组
	   for (var i=0, l=act.length; i < l; i++) {
		   if(act[i].underway==way&&!act[i].isParent){
			   zTreeObj.checkNode(act[i], true, false,false);
		   }else{
			   zTreeObj.checkNode(act[i], false, false,false);
		   }	  		 
	   }
}

/****
 * 工程信息修改模态框
 */
$("#projectInfBtn").click(function(){
	    var htm = $($('#projectTemplate').html());
		var _html='<div>'+htm[0].outerHTML+'</div>';
		$("#seeMethodModal .modal-body").html(_html);
		showProjectInfo();
		$("#seeMethodModal").modal("show");				
		activateDatetimepicker($('.form_date'));
});



/***
 * 展示工程信息
 */
function showProjectInfo(){
	var projectId=$("#projectId").val();
	$("#nodeId").val("");//用于区别节点信息的更新
	if(projectId==null){
		toastrError("工程主键为null",2000);
		return;
	}
	$.ajax({
		url:basepath+"/task/getProjectInfo",
		type:"post",
		data:{"id":projectId},
		dataType:"json",
		success:function(data){	
			if(data.code==200){
				var project=data.project;
				$("#projectName").val(project.projectName);
				$("#startDate").val(project.startDate);
				$("#finshDate").val(project.finshDate);
				$("#actuallyFinshDate").val(project.actuallyFinshDate);
			}else{
				toastrError(data.msg,2000);
			}		
		} 
	}); 
}

/***
 *  更新工程信息
 */
function  updateProjectAjax(){
	var projectId=$("#projectId").val();
	if(projectId==null){
		toastrError("工程主键为null",2000);
		return;		
	}
	var param={
			"projectName":$("#projectName").val(),
			"startDate":$("#startDate").val(),
			"finshDate":$("#finshDate").val(),
			"id":projectId
	};
	var actuallyFinshDate=$("#actuallyFinshDate").val();
	if(actuallyFinshDate!=null&&actuallyFinshDate!=""){
		param["actuallyFinshDate"]=actuallyFinshDate;
	}
	if($("#projectForm").validationEngine('validate')){
		$.ajax({
			url:basepath+"/task/updataProject",
			type:"post",
			data:param,
			dataType:"json",
			success:function(data){	
				if(data.code==200){			
					toastrSuccess(data.msg,2000);				
				}else{
					toastrError(data.msg,2000);
				}		
			} 
		}); 
		$("#seeMethodModal").modal("hide");
	}
	
}





$("#analyseProjectInfBtn").click(function(){
	    var projectId=$("#projectId").val();
		$.ajax({
			url:basepath+"/task/getProjectAnalyse",
			type:"post",
			data:{"projectId":projectId},
			dataType:"json",
			success:function(data){							
				if(data.code==200){				
					showResult(data);
					toastrSuccess(data.msg,2000);				
				}else{
					toastrError(data.msg,2000);
				}		
			} 
		}); 	 
});


function showResult(data){
	var info=data.info;
	 var _html="工程权重参数有"+data.args+"<br>";
	_html+="计划完成时间："+info.planTime+"<br>";
	_html+="工程任务数："+info.projectTaskNum+"<br>";
	$('#result').html(_html);
	$('.card').css("display","block");

}