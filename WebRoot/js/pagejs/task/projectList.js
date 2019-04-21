var table;
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
	     table=$('#projectTable').DataTable({
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
		           { data: 'startDate'},
		           { data: 'finshDate'},
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
	    table.on( 'draw', function () {	    
	    	 $("[data-toggle='popover']").popover();
		} );

	    /***
	     * function 编辑表格行数据
	     */
		$("body").on("click",".edtiBtn",function(){
			var $operatingArea=$(this).parent();
			var id=$(this).data("id");		
		    var htm = $($('#template').html());
			var _html='<div>'+htm[0].outerHTML+'</div>';			
			$("#seeMethodModal .modal-body").html(_html);	
			showEditData($operatingArea.parent().parent());
			activateDatetimepicker($('.form_date'));
			$("#projectId").val(id);//模板生成后执行
			$("#seeMethodModal").modal("show");		
	   }); 
	   
		/***
	     * function 打开新增工程模态框
	     */
		$("body").on("click","#addBtn",function(){
		    var htm = $($('#template').html());
			var _html='<div>'+htm[0].outerHTML+'</div>';			
			$("#seeMethodModal .modal-body").html(_html);	
			activateDatetimepicker($('.form_date'));
			$("#projectId").val("");
			$("#seeMethodModal").modal("show");
	   }); 
		
		/***
	     * function 新增工程请求
	     */
		$("body").on("click","#submitBtn",function(){
		    var id=$("#projectId").val();
			if(id!=""){
				updateProjectAjax(id);
			}else{
				createProjectAjax();
			}			
		}); 
		
		/***
		 * 创建工程的ajax
		 */
		function  createProjectAjax(){			
			if($("#projectForm").validationEngine('validate')){		
				var data={
						"projectName":$("#projectName").val(),
						"startDate":$("#startDate").val(),
                        "finshDate":$("#finshDate").val(),
						};
				$.ajax({
					url:basepath+"/task/createProject",
					type:"post",
					data:data,
					dataType:"json",
					success:function(data){
						if(data.code==200){							
							window.location.href=basepath+"/task/goProjectTree?projectId="+data.projectId;							
						}else{							
							toastrError(data.msg,3000);
						}							
					}
				});					
			}
	    
		}
		
		/****
		 * 更新工程的ajax
		 */
		function updateProjectAjax(id){
			if(id==null){
				toastrError("工程主键为null",2000);
				return;		
			}
			var param={
					"projectName":$("#projectName").val(),
					"startDate":$("#startDate").val(),
					"finshDate":$("#finshDate").val(),
					"id":id
			};
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
				reloadTable();
				$("#seeMethodModal").modal("hide");
		}
	}
		
		/***
		 * 跳转到工程任务详情页面
		 */
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
	    	$("#projectName").val($object.find("td").eq(0).text());
	    	$("#startDate").val($object.find("td").eq(1).text());
	    	$("#finshDate").val($object.find("td").eq(2).text());	    	
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
								table.ajax.reload(null,false);
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
    var projectNameKey=$("#projectNameKey").val();
	var param = {
	    		 "startDates" :startDates,
		    	 "endDates":endDates,
		    	 "projectName":projectNameKey
	    		};
	 table.settings()[0].ajax.data = param;
	 table.ajax.reload();
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
	$("#projectNameKey").val("");
	reloadTable(); 
});



