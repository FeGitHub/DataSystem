/****
 * 备忘提醒页面的js
 */
var taskTable;
$(function(){	
	$(".quaryTime").datetimepicker({
        language:"zh-CN",
        bootcssVer:3,
        format: 'yyyy-mm-dd', 
        autoclose:true,
        todayHighlight: true,
        minView:2,       
        weekStart:1
    }); 	
		taskTable=$('#taskTable').DataTable({
			language: {
		     "url": basepath+"/json/datatables_language.json"
		 },
		 bFilter: false, //去掉默认搜索框
		 bLengthChange: false, //去掉显示总页数
		 ordering: false, // 禁止排序
		 serverSide: true,//开启服务器模式
		 pageLength:8,//每页显示的数据的条数
		 ajax: {
		     url: basepath+'/task/getTargetList',
		     type: 'POST'
		 },
		 columns: [
		           { data: 'taskName' },	     		         
		           { data: 'addTime'},
		           { data: 'goal'},
		           { data: 'start'},
		           { data: 'end'},
		           { data: null,//操作部分
		         	  "render": function ( data, type, full, meta ) {            
		           		 var str = "<span id="+full.taskId+">";  
		           		 str+="<button class='btn table_btn btn-primary btn-sm taskDetailBtn'  target='_blank' data-id='"+full.taskId+"'>详情</button>";
		           		 str+="&nbsp;<button class='btn table_btn btn-danger btn-sm delBtn'  target='_blank' data-id='"+full.taskId+"'>删除</button>";
		           		 str+="</span>";
		           		 return str;
		           	}
		           }
		       ]
		});
			   
	    
	    /***
	     * function 删除任务
	     */
		$("body").on("click",".delBtn",function(){
			var taskId=$(this).data("id");
			var info={"taskId":taskId}
			layer.confirm('确定删除？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					$.ajax({
						url:basepath+"/task/delTarget",
						type:"post",
						data:info,
						dataType:"json",
						success:function(data){
							if(data.code==200){							
								taskTable.ajax.reload(null,false);
								toastrSuccess(data.msg,3000);
							}else{							
								toastrError(data.msg,3000);
							}							
						}
					});					 
					layer.closeAll();
				});
	   }); 	 	    
} );


/***
 *  跳转到具体任务详情页面
 */
$("body").on("click",".taskDetailBtn",function(){	
	 var taskId=$(this).data("id");
	 window.location.href=basepath+"/task/goTargetDetail?taskId="+taskId;
	
});


/****
 * 重新加载页面数据
 */
function reloadTable(){
	var startDates = $("#datetimepicker1").val();
    var endDates = $("#datetimepicker2").val();
    var taskName=$("#taskName").val();
	var param = {
	    		 "startDates" :startDates,
		    	 "endDates":endDates,
		    	 "taskName":taskName
	    		};
	taskTable.settings()[0].ajax.data = param;
	taskTable.ajax.reload();
}

/****
 * 查询信息过滤
 */
$("#querys").click(function(){	
	reloadTable();
});

/****
 * 重置过滤条件
 */
$("#reset").click(function(){
	$("#datetimepicker1").val("");
	$("#datetimepicker2").val("");
	$("#taskName").val("");
	reloadTable();	
});

$("#createTask-btn").click(function(){
	  window.location.href=basepath+"/task/goCreateTarget";
});