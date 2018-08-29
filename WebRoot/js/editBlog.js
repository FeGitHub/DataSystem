//编辑博客数据页面的js
$(function(){
	  $("#submitBtn").click(function(){		
		  var id=$("input[name='id']").val();
		  var title=$("input[name='title']").val();
		  var content=$("input[name='content']").val();
		  var url="/BlogData/saveBlog";
		  $.post(url,{id:id,title:title,content:content},function(data){
			  if(data.info=="ok"){
				  $.scojs_message('修改成功！', $.scojs_message.TYPE_OK);
			  }else{
				  $.scojs_message('修改失败！', $.scojs_message.TYPE_ERROR);
			  }
		  },"json");
	  });
	  
	 //======================
	  /**
	   * 重写确认框 fun:函数对象 params:参数列表， 可以是数组
	   */
	  function confirm() {
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
	          $("#myConfirm").modal("hide");
	        
	      });
	  }
});