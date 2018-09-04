$(function(){	 
       $('#table_id_example').DataTable({//利用id来找到对应的表格，并将数据填充进去
    	language: {//语言国际化
            "url": "/json/datatables_language.json"//国际化文件的文件资源
        },
        ajax: {//利用ajax请求资源文件
            url: '/qrtz/getJobDetails',//请求资源的位置,加"/"表示服务器根目录，不加"/"表示当前请求页面的位置
        },
        columns: [
                  { data: 'JOB_NAME' },//第一列
                  { data: 'JOB_GROUP'},//第二列 
                  { data: 'DESCRIPTION'},//第二列   
                  { data: 'JOB_CLASS_NAME'}//第二列   
                        
              ]
    });
} );