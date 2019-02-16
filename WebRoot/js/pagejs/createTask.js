/****
 *  创建工程任务的页面js
 */
$(function(){
	pieChart();
	 $('#taskForm').validationEngine({   
			promptPosition: 'centerReight',
			scroll:false
		});	
	 buildTree();//构建工程任务树
	
});



/***
 * 新增工程任务
 */
$("#submitBtn").click(function(){
	 if($("#taskForm").validationEngine('validate')){
		 $.ajax({
				url:basepath+"/task/createTask",
				type:"post",
				data:$("#taskForm").serialize(),
				dataType: "json",
				success:function(data){
					if(data.code==200){	
						toastrSuccess(data.msg,2000);	
						setTimeout(function(){
							window.location.href=basepath+"/task/goTaskList";
							},2000);
					}else{
						toastrError(data.msg,3000);
					}		
				}
			});
	 }
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

/****
 * 移除节点
 * @param treeId
 * @param treeNode
 */
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
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
	 var preCode=treeNode.id+"0";	 
	 var nodeId=parseInt(preCode)+childrenSize+1;
	 var zTree = $.fn.zTree.getZTreeObj("treeDemo");
     zTree.addNodes(treeNode, {       
    	 id:nodeId,
         pId: treeNode.id,      
         name: "node" + nodeId
     });
}

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
	        params.push({ "id": MyNode[0].id, "pId":MyNode[0].pId,"name" :MyNode[0].name,"url":MyNode[0].url});
	    }
	    var ztreeJson = JSON.stringify(params);
	    console.log(ztreeJson);
	    return ztreeJson;
}

/***
 * 构建工程任务树
 */
function buildTree(){
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
	            }	           
	        };
	 var zNodes =[
	                {id:1, pId:0, name:"工程任务",open:true,checked:false},
	                
	                {id:11, pId:1, name:"首页",icon:dashboard,open:true,checked:false},
	                
	            	{id:12, pId:1, name:"数据表格",icon:laptop,open:true,checked:false},
		           	{id:121, pId:12, name:"备忘提醒", url:"/remind/goRemindList",checked:false},
		           	{id:122, pId:12, name:"调度任务",url:"/qrtz/goQrtzTaskList",checked:false},
		           	
		           	{id:13, pId:1, name:"测试区域",icon:edit, open:true,checked:false},
		           	{id:131, pId:13, name:"测试页面", url:"/test/goTestPage",checked:false},
		    
		           	{id:14, pId:1, name:"案例页面", icon:list,open:true,checked:false},
		           	{id:141, pId:14, name:"Ztree-bootstrap", url:"/demo/goTreePage",checked:false},
		           	{id:142, pId:14, name:"ECharts", url:"/demo/goEChartsPage",checked:false},
		           	
		           	{id:15, pId:1, name:"任务模块",icon:text, open:true,checked:false},
		           	{id:151, pId:15, name:"工程任务", url:"/task/goTaskList",checked:false},
		           	
		           	{id:16, pId:1, name:"系统设置",icon:text, open:true,checked:false},
		           	{id:161, pId:16, name:"菜单设置", url:"/menu/goMenu",checked:false},
		           	];	  
	  $.fn.zTree.init($("#treeDemo"), setting, zNodes); 
}
  
$("#testBtn").click(function(){
	  var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
	  var ztreeJson=getZtreeNodesInfo(zTreeObj);		  
});

function pieChart(){
	 var dom = document.getElementById("pieChart");
	 var pieChart = echarts.init(dom);
	 var app = {};
	 option = null;
	 option = {
	     title : {
	         text: '工程任务',
	         subtext: '纯属虚构',
	         x:'center'
	     },
	     tooltip : {
	         trigger: 'item',
	         formatter: "{a} <br/>{b} : {c} ({d}%)"
	     },
	     legend: {
	         orient: 'vertical',
	         left: 'left',
	         data: ['直接访问','搜索引擎']
	     },
	     series : [
	         {
	             name: '访问来源',
	             type: 'pie',
	             radius : '55%',
	             center: ['50%', '60%'],
	             data:[
	                 {value:735, name:'直接访问'},
	                
	                 {value:1548, name:'搜索引擎'}
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
		 pieChart.setOption(option, true);
	 }
}