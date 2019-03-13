/****
 * 备忘提醒页面的js
 */
var remindTable;
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
	     remindTable=$('#remindTable').DataTable({
			language: {
		     "url": basepath+"/json/datatables_language.json"
		 },
		 bFilter: false, //去掉默认搜索框
		 bLengthChange: false, //去掉显示总页数
		 ordering: false, // 禁止排序
		 serverSide: true,//开启服务器模式
		 pageLength:8,//每页显示的数据的条数
		 ajax: {
		     url: basepath+'/task/getProjectList',
		     type: 'POST'
		 },
		 columns: [
		           { data: 'projectName' },    		         
		           { data: 'planStartDate'},
		           { data: 'plantFinshDate'},
		           { data: null,//操作部分
			         	  "render": function ( data, type, full, meta ) {            
			           		 var str = "<span id="+full.id+">";  
			           		 str+="<button class='btn table_btn btn-primary btn-sm edtiBtn'  target='_blank' data-id='"+full.id+"'>修改</button>";
			           		 str+="&nbsp;<button class='btn table_btn btn-danger btn-sm delBtn'  target='_blank' data-id='"+full.id+"'>删除</button>";
			           		 str+="&nbsp;<button class='btn table_btn btn-primary btn-sm detailBtn'  target='_blank' data-id='"+full.id+"'>详情</button>";
			           		 str+="</span>";
			           		 return str;
			           	}
			           }
		       ]
		});
		
	   /***
	    * function 表格绘画成功后激活弹出框
	    */
	    remindTable.on( 'draw', function () {	    
	    	 $("[data-toggle='popover']").popover();
		} );
	    
	    /***
	     * function 编辑表格行数据
	     */
		$("body").on("click",".edtiBtn",function(){
			var $operatingArea=$(this).parent();
			var id=$(this).data("id");		
		    var htm = $($('#remindTemp').html());
			var _html='<div>'+htm[0].outerHTML+'</div>';			
			$("#seeMethodModal .modal-body").html(_html);	
			$("#remindId").val(id);
			showEditData($operatingArea.parent().parent());
			activateDatetimepicker($('.form_date'));
			$("#seeMethodModal").modal("show");		
	   }); 
	   
		/***
	     * function 新增备忘事务
	     */
		$("body").on("click","#addBtn",function(){
		   var htm = $($('#remindTemp').html());
			var _html='<div>'+htm[0].outerHTML+'</div>';			
			$("#seeMethodModal .modal-body").html(_html);	
			activateDatetimepicker($('.form_date'));
			$("#seeMethodModal").modal("show");
	   }); 
		
		/***
	     * function 提交表单数据
	     */
		$("body").on("click","#submitBtn",function(){			
			if($("#remindForm").validationEngine('validate')){
				 window.location.href=basepath+"/task/createProject?projectName="+$("#firstId").val()+"&planStartDate="+$("#secondId").val()+"&plantFinshDate="+$("#thirdId").val();				
			}
	    }); 
			
		
		$("body").on("click",".detailBtn",function(){			
			var id=$(this).data("id");
			 window.location.href=basepath+"/task/goProjectTree?projectId="+id;
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
		        // startDate:new Date(),//过往时间不可以填
		         weekStart:1
		     }); 
	    }
	    
	    /***
	     * function 编辑数据回显
	     */
	    function showEditData($object){
	    	$("#firstId").val($object.find("td").eq(0).text());
	    	$("#secondId").val($object.find("td").eq(1).text());
	    	$("#thirdId").val($object.find("td").eq(2).text());	    	
	    }
	    
	    /***
	     * function 删除备忘事务
	     */
		$("body").on("click",".delBtn",function(){
			var id=$(this).data("id");
			var info={"projectId":id}
			layer.confirm('确定删除？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					$.ajax({
						url:basepath+"/task/delProject",
						type:"post",
						data:info,
						dataType:"json",
						success:function(data){
							if(data.code==200){							
								remindTable.ajax.reload(null,false);
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




/****
 * 重新加载页面数据
 */
function reloadTable(){
	var startDates = $("#datetimepicker1").val();
    var endDates = $("#datetimepicker2").val();
    var projectName=$("#projectName").val();
	var param = {
	    		 "startDates" :startDates,
		    	 "endDates":endDates,
		    	 "projectName":projectName
	    		};
	 remindTable.settings()[0].ajax.data = param;
	 remindTable.ajax.reload();
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
	$("#projectName").val("");
	reloadTable();
});

