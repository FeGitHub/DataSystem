$(function(){	 
       $('#table_id_example').DataTable({//利用id来找到对应的表格，并将数据填充进去
    	language: {
            "url": basepath+"/json/datatables_language.json"//国际化文件的文件资源
        },
        ajax: {//利用ajax请求资源文件
            url: basepath+'/remind/getRemindDetails',
        },
        columns: [
                  { data: 'remindName' },
                  { data: 'remindText'},
                  { data: 'remindTime'},
                  { data: 'mail'}               
                  
              ]
    });
} );