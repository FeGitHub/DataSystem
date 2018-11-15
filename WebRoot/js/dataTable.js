 /***
  * 博客数据表格
  */  
$(function(){	 
       $('#table_id_example').DataTable({
    	language: {//语言国际化
            "url": basepath+"/json/datatables_language.json"
        },
        ajax: {//利用ajax请求资源文件
            url: basepath+'/test/getTable',
        },
        columns: [
                  { data: 'id' },//第一列
                  { data: 'title'},//第二列                  
                  { data: 'content',//第三列
                   "className":"content",//赋予这个td的类
                   "render": function ( data, type, full, meta ) {//此方法可以对返回的json数据进行加工            
                     		 var str = data;                                  
                     		 return '加工:'+str;
                     	}
                  },
                  { data: null, //第四列
                	"render": function(data, type, full, meta){
                		 var str ="";
                		 //编辑按钮
                		 str += "<a class='btn table_btn btn-success btn-sm' href='"+basepath+"/BlogData/editBlog?id="+full.id+"' target='_blank'>"+
                         "<i class='fa fa-edit fa-fw'></i>"+
                         "编辑"+"</a> ";
                         //删除按钮                      
                		 str += "<button class='btn table_btn btn-danger btn-sm'  target='_blank' id='"+full.id+"'>"+
                         "<i class='fa fa-edit fa-fw'></i>"+
                         "删除"+"</button> ";
                		 return str;
                	}  
                  }                 
              ]
    });
} );

//删除动作
$("#table_id_example").on("click",".btn-danger",function(){
	var id=$(this).attr("id");
	 confirm(id);
})


//重写的确认框
function confirm(id) {
    if ($("#myConfirm").length > 0) {
        $("#myConfirm").remove();
    } 
    var html = "<div class='modal fade' id='myConfirm' >"
            + "<div class='modal-backdrop in' style='opacity:0; '></div>"
            + "<div class='modal-dialog' style='z-index:2901; margin-top:60px; width:400px; '>"
            + "<div class='modal-content'>"
            + "<div class='modal-header'  style='font-size:16px; '>"
            + "<span class='glyphicon glyphicon-envelope'>&nbsp;</span>信息！<button type='button' class='close' data-dismiss='modal'>"
            + "<span style='font-size:20px;  ' class='glyphicon glyphicon-remove'></span><tton></div>"
            + "<div class='modal-body text-center' id='myConfirmContent' style='font-size:18px; '>"
            + "是否确定要删除？"
            + "</div>"
            + "<div class='modal-footer ' style=''>"
            + "<button class='btn btn-danger ' id='confirmOk' >确定<tton>"
            + "<button class='btn btn-info ' data-dismiss='modal'>取消<tton>"
            + "</div>" + "</div></div></div>";
    $("body").append(html);
    $("#myConfirm").modal("show");
    $("#confirmOk").on("click", function() {
    	var url=basepath+"/BlogData/delBlog";
    	var args={"id":id};
    	$.post(url,args,function(data){
    		$("#"+id).parent().parent().remove();//页面消除已删除的数据
    	},'json');        	
    	  $("#myConfirm").modal("hide");
    	  $.scojs_message('删除成功！', $.scojs_message.TYPE_OK);
    });
}