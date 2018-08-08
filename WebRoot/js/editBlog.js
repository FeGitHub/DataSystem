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
});