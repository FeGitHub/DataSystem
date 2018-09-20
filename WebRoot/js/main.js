	/***
	 * 主管理页面的js
	 */
	$(function () {	
		//链接打开的页面在主体frame表格显示
		$("#menu a").each(function(){
				if($(this).attr("href")!=""&&$(this).attr("class")!="inMyPlace"){
					 $(this).attr("target",'iframe_a');	
				}			
	});		
		//弹出框数据表格填充
	    $('#table_id_example').DataTable({//利用id来找到对应的表格，并将数据填充进去	    	
	 	language: {//语言国际化
	         "url": basepath+"/json/datatables_language.json"//国际化文件的文件资源
	     },
	     ajax: {//利用ajax请求资源文件
	         url: basepath+'/test/getTable',//请求资源的位置,加"/"表示服务器根目录，不加"/"表示当前请求页面的位置
	     },
	     columns: [
	               { data: 'id' },
	               { data: 'title'},              
	               { data: 'content',
	                "className":"content",
	                "render": function ( data, type, full, meta ) {          
	                  		 var str = data;                                  
	                  		 return '加工:'+str;
	                  	}
	               },
	               { data: null, //第四列
	             	"render": function(data, type, full, meta){
	             		 var str ="<nobr>";
	             		 str += "<a class='btn table_btn btn-success btn-sm' href='"+basepath+"/BlogData/editBlog?id="+full.id+"' target='_blank'>"+
	                      "<i class='fa fa-edit fa-fw'></i>"+
	                      "编辑"+"</a> ";
	             		 return str +="</nobr>";
	             	}  
	               }
	           ]
	 });
	    //触发弹出框
	    $("#info").click(function(){
	    	 $("#cost_editBtn").click();//点击a标签,用于弹出模态框
	    });
} );