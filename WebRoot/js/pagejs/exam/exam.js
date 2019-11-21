

$("#test").click(function(){	
	//简化请求
	var successFn=function(data){
		//alert(JSON.stringify(data));
		alert(data.msg);		
	}
	PAMS.AjaxDone({url:basepath+"/exam/getExam",successFn:successFn});
});



$("#newQuestion").click(function(){	
   var htm = $($('#template').html());
   var _html='<div>'+htm[0].outerHTML+'</div>';	
   PAMS.showModel(_html);
});





