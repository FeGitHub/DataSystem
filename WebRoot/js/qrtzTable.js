/***
 * quartz任务调度信息页面
 */
$(function(){	 
       $('#table_id_example').DataTable({//利用id来找到对应的表格，并将数据填充进去
    	language: {
            "url": basepath+"/json/datatables_language.json"//国际化文件的文件资源
        },
        ajax: {//利用ajax请求资源文件
            url: basepath+'/qrtz/getJobDetails',
        },
        columns: [
                  { data: 'JOB_NAME' },//任务名
                  { data: 'JOB_GROUP'},//任务组
                  { data: 'DESCRIPTION'},//任务描述
                  { data: 'JOB_CLASS_NAME'},//任务类名
                  { data: 'CRON_EXPRESSION'},//表达式
                  { data: null,//操作
                	  "render": function ( data, type, full, meta ) {//此方法可以对返回的json数据进行加工            
                  		 var str = "";  
                  		 str+="<button class='btn table_btn btn-danger btn-sm'  target='_blank'>操作</button>";
                  		 return str;
                  	}
                  }
                  
              ]
    });
} );