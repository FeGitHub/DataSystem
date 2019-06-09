$(function(){
	init();
	
})
			
 

function init(){
	var projectId=$("#obj").val();
	$.ajax({
		url:basepath+"/task/getProjectAnalyse",
		type:"post",
		data:{"projectId":projectId},
		dataType:"json",
		success:function(data){	
			if(data.code==200){			
				//toastrSuccess(data.msg,2000);		
				console.log(data);
			}else{
				toastrError(data.msg,2000);
			}		
		} 
	}); 
}
















/***
 * 将读取的csv文件信息以表格形式展示出来
 */
 function showCSV(){
	 var csv =$("#csv").val();
	 if(csv==null){
		 $(".table").html("<h1 align='center'>数据为空</h1>");
		 return;
	 }
	 var arr=JSON.parse(csv);
	 var col=Object.keys(arr[0]).length;
     var _html="";
     var rows=arr.length;
    for(var i=0;i<rows;i++){    	
    	if(i==0){
    		_html+="<thead><tr>";
    	} else{
    		_html+="<tr>";
    	}      	
      var count=Object.keys(arr[0]).length;
      for(var j=0;j<count;j++){
    	  var key=j+"_column";	  		
    	 _html+="<td>"+arr[i][key]+"</td>" 	
      }
      if(i==0){
    		_html+="</tr></thead>";
    	}else{
    		_html+="</tr>";
    	}
      if(i==(rows-1)){
    	  //_html+="<tfoot><tr><td colspan='"+count+"'><button id='addBtn'>新增数据</button></td></tr></tfoot>";
    	  _html+="<tfoot><tr><td colspan='"+count+"'></td></tr></tfoot>"
  	 }
     		       
    }	
    $(".table").html(_html); 
}

 $("body").on("click","#analyseBtn",function(){});
 
 $("body").on("click","#customAnalyseBtn",function(){})