/***
 * quartz任务调度信息页面
 */
$(function(){	 
       $('#table_id_example').DataTable({//利用id来找到对应的表格，并将数据填充进去
    	language: {
            "url": basepath+"/json/datatables_language.json"//国际化文件的文件资源
        },
        ajax: {//利用ajax请求资源文件
            url: basepath+'/warn/getWarnRuleDetails',
        },
        columns: [
                  { data: 'early_warning_name' },
                  { data: 'early_warning_type'},
                  { data: 'early_warning_type'},//修改
                  { data: 'early_warning_transactor'},
                  { data: 'material_type'},
                  { data: null,//操作
                	  "render": function ( data, type, full, meta ) {           
                  		 var str = "";  
                  		 str+="<button class='btn table_btn btn-danger btn-sm'  target='_blank'>操作</button>";
                  		 return str;
                  	}
                  }
                  
              ]
    });
} );