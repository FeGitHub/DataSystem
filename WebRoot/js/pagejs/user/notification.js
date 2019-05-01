$("body").on("click","#nidBtn",function(){	
	var nid=$("#nid").val();
	if(nid==null||nid==""){
		return;
	}	
	$.ajax({
		url:basepath+"/user/updateNotification",
		type:"post",
		dataType:"json",
		data:{"id":nid},
		success:function(data){		
			if(data.code==200){			
				toastrSuccess(data.msg,3000);
			}else{
				toastrError(data.msg,3000);
			}
		}					
	});

}); 