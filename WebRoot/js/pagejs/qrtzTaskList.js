/***
 * quartz任务调度信息页面
 */
var qrtzTable;
$(function(){	 
        qrtzTable=$('#qrtzTable').DataTable({
    	language: {
            "url": basepath+"/json/datatables_language.json"//国际化文件的文件资源
        },
        ordering: false, //禁止排序
        ajax: {
            url: basepath+'/qrtz/getJobDetails',
            type: 'POST'
        },
         bFilter: false, //去掉默认搜索框
		 bLengthChange: false, //去掉显示总页数
		 ordering: false, // 禁止排序
		 serverSide: true,
		 pageLength:8,
        columns: [                
                  /*{ data: 'JOB_NAME' },
                  { data: 'JOB_GROUP'},*/
                  { data: 'DESCRIPTION'},//任务描述
                  { data: 'JOB_CLASS_NAME'},//任务类名
                  { data: 'CRON_EXPRESSION'},//表达式
                  { data: null,//操作部分
		         	  "render": function ( data, type, full, meta ) {            
			           		 var str = "";  
			           		// str+="<button class='btn table_btn btn-primary btn-sm edtiBtn'  target='_blank' >修改</button>";
			           		 str+="&nbsp;<button class='btn table_btn btn-danger btn-sm delBtn'  target='_blank' data-jobName='"+full.JOB_NAME+"' data-jobGroup='"+full.JOB_GROUP+"' data-triggerName='"+full.TRIGGER_NAME+"' data-triggerGroup='"+full.TRIGGER_GROUP+"' >删除</button>";			        
			           		 return str;
			           	}
			           }                
              ]
    });      
      
	   
		
       
		/***
	     * function 删除调度任务
	     */
		$("body").on("click",".delBtn",function(){	
			var $this=$(this);
			layer.confirm('确定删除？', {
				  btn: ['是的','取消'] 
				}, function(){
					$.ajax({
						url:basepath+"/qrtz/removeJob",
						type:"post",
						data:{
							jobName:$this.data("jobname"),
							jobGroupName:$this.data("jobgroup"),
							triggerName:$this.data("triggername"),
							triggerGroupName:$this.data("triggergroup")
						},
						dataType:"json",
						success:function(data){
							if(data.code==200){
								toastrSuccess(data.msg,3000);
								qrtzTable.ajax.reload(null,false);//重新加载当前页表格数据								
							}							
						}
					});
					layer.closeAll();
				});		
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
		         minuteStep: 1,
		         startDate:new Date(),//过往时间不可以填
		         weekStart:1
		     }); 
	    }      	    
	    
	  
	 	    
} );

/***
 * 重新过滤表格数据
 */
function queryTable(){
        var queryJobName=$("#queryJobName").val();
	    var param = {
		    	 "jobName":queryJobName
	    };
	    qrtzTable.settings()[0].ajax.data = param;
	    qrtzTable.ajax.reload();
}
/****
 * 查询数据
 */
$("#querys").click(function(){	
	queryTable();
});

/***
 * 重置
 */
$("#reset").click(function(){
	$("#queryJobName").val("")
	queryTable();	
});

/***
 * 跳转到新增调度任务的页面
 */
$("#create").click(function(){
	window.location.href=basepath+"/qrtz/goCreateQuartz";
});