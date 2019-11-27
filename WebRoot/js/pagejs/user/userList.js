/****
 * 用户列表页面
 */
var showTable;
$(function(){	
	activate($('.quaryTime'));//激活时间选择器
	showModel();//初始化模态框
	initTable();//初始化数据表格   	  
} );


/***
 * function 编辑表格行数据
 */
$("body").on("click",".edtiBtn",function(){
	var $operatingArea=$(this).parent();
	var id=$(this).data("id");		
	$("#recordId").val(id);
	showEditData($operatingArea.parent().parent());
	$("#seeMethodModal").modal("show");		
}); 

/***
 * function 打开模态框
 */
$("body").on("click","#addBtn",function(){
    var htm = $($('#template').html());
	var _html='<div>'+htm[0].outerHTML+'</div>';			
	$("#seeMethodModal .modal-body").html(_html);	
	$("#seeMethodModal").modal("show");
}); 





/****
 * 重新加载页面数据
 */
function reloadTable(){
	var startDates = $("#datetimepicker1").val();
    var endDates = $("#datetimepicker2").val();
    var account=$("#account").val();
	var param = {
	    		 "startDates":startDates,
		    	 "endDates":endDates,
		    	 "account":account
	    		};
	 showTable.settings()[0].ajax.data = param;
	 showTable.ajax.reload();
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
	$("#account").val("");
	reloadTable();
});

/***
 * 
 * @param field
 * @param rules
 * @param i
 * @param options
 * @returns {String}
 */
function checkMail(field, rules, i, options){
	var re=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
	var mail=$("#mail").val();
	if(!re.test(mail)){
		 rules.push('required');
		 return "* 无效的邮箱地址";
	}	
}



function showModel(){
	    var htm = $($('#template').html());
		var _html='<div>'+htm[0].outerHTML+'</div>';			
		$("#seeMethodModal .modal-body").html(_html);	
		$(".form_date").datetimepicker({
	         language:"zh-CN",
	         bootcssVer:3,
	         format: 'yyyy-mm-dd', 
	         autoclose:true,
	         todayHighlight: true,
	         minView:2,       
	         weekStart:1
	     }); 	
}

/***
 * function 用户信息操作
 */
$("body").on("click","#submitBtn",function(){	
	var recordId=$("#recordId").val();
	if(recordId!=null&&recordId!=""){
		if($("#templateForm").validationEngine('validate')){
			$.ajax({
				url:basepath+"/user/updateUser",
				type:"post",
				dataType:"json",
				data:$("#templateForm").serialize(),
				success:function(data){
					$("#recordId").val("");
					$("#seeMethodModal").modal("hide");
					if(data.code==200){
						showTable.ajax.reload(null,false);
						toastrSuccess(data.msg,3000);
					}else{
						toastrError(data.msg,3000);
					}
				}					
			});			
		}		
	}else{
		if($("#templateForm").validationEngine('validate')){
			$.ajax({
				url:basepath+"/user/register",
				type:"post",
				dataType:"json",
				data:$("#templateForm").serialize(),
				success:function(data){
					$("#seeMethodModal").modal("hide");
					if(data.code==200){
						showTable.ajax.reload(null,false);
						toastrSuccess(data.msg,3000);
					}else{
						toastrError(data.msg,3000);
					}
				}					
			});			
		}
	}
	
}); 
	

/****
 * function 激活时间输入框
 */
function activate($object){	    	
	$object.datetimepicker({
         language:"zh-CN",
         bootcssVer:3,
         format: 'yyyy-mm-dd hh:ii:ss', 
         autoclose:true,
         todayHighlight: true,
         minView:0,
         startDate:new Date(),//过往时间不可以填
         weekStart:1
     }); 
}


/***
 * function 删除
 */
$("body").on("click",".delBtn",function(){
	var id=$(this).data("id");
	var info={"id":id}
	layer.confirm('确定删除？', {
		  btn: ['确定','取消'] //按钮
		}, function(){				 
			layer.closeAll();
		});
}); 	 	    



/***
 * function 编辑数据回显
 */
function showEditData($object){
	var recordId=$("#recordId").val();
	/*$("#subjectId").val($object.find("td").eq(0).text());
	$("#contentId").val($object.find("td").eq(1).text());
	$("#mail").val($object.find("td").eq(2).text());*/   
	$.ajax({
		url:basepath+"/user/getUser",
		type:"post",
		dataType:"json",
		data:{"id":recordId},
		success:function(data){			
			if(data.code==200){
				var user=data.user;
				$("#mail").val(user.mail);					
				$("#recordId").val(user.id);		
				$("#accountId").val(user.account);
				var userFlag=user.userFlag;
				var leve=2;
				if(user.leve==1){
					leve=1;
				}
				$(":radio[name='userFlag'][value='" +userFlag+ "']").prop("checked", "checked");
				$(":radio[name='leve'][value='" +leve+ "']").prop("checked", "checked");
				
			}else{
				toastrError(data.msg,3000);
			}
		}					
	});
}


/****
 *  初始化数据表格
 */
function initTable(){
    showTable=$('#showTable').DataTable({
		language: {
	     "url": basepath+"/json/datatables_language.json"
	 },
	 bFilter: false, //去掉默认搜索框
	 bLengthChange: false, //去掉显示总页数
	 ordering: false, // 禁止排序
	 serverSide: true,//开启服务器模式
	 pageLength:8,//每页显示的数据的条数
	 ajax: {
	     url: basepath+'/user/getUserList',
	     type: 'POST'
	 },
	 columns: [
	           { data: 'account' },    		          
	           { data: 'leve'},
	           { data: 'mail'},
	           { data: null,
	         	  "render": function ( data, type, full, meta ) {            
	           		 var str = "<span id="+full.id+">";  
	           		 str+="<button class='btn table_btn btn-primary btn-sm edtiBtn'  target='_blank' data-id='"+full.id+"'>修改</button>";
	           		 str+="&nbsp;<button class='btn table_btn btn-danger btn-sm delBtn'  target='_blank' data-id='"+full.id+"'>删除</button>";
	           		 str+="</span>";
	           		 return str;
	           	}
	           }
	       ]
	});
}