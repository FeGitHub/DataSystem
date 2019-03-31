
$(function () {  
		
		/****
		 * demo:财务excel信息导入测试
		 */
		$("#financeBtn").click(function(){	
			var url="/excel/importFinanceToDB";
		    var file=$('#financeId')[0].files[0];	
		    if(!checkData($('#financeId').val())){
		    	return;
		    }
			clickAction(file,url);
		})	
		
		
		$("#readCSVBtn").click(function(){	
			var url="/file/readCSV";
		    var file=$('#csv')[0].files[0];	
	    	if(file==null){
					toastrError("请选择文件资源",3000);
					return;
			}
	    	var formData = new FormData();
	    	formData.append('file',file );  
	  		$.ajax({
				url: basepath+url,
				type: "POST",
				data: formData,			
				contentType: false,			
				processData: false,
				success: function (data) {
					if(data.code==200){
						toastrSuccess(data.msg,3000);
						var csv=data.csv;
						initCSV(csv);
						
					} else{
						toastrError(data.msg,3000);
					}  
				},
				error: function () {
					toastrError("请求失败");
				}
			});	
	      
		})	
		
    }); 
 
      /***
       * 请求格式封装
       * @param formData
       * @param url
       */
      function clickAction(file,url){
    	if(file==null){
				toastrError("请选择文件资源",3000);
				return;
		}
    	var formData = new FormData();
    	formData.append('file',file );  
  		$.ajax({
			url: basepath+url,
			type: "POST",
			data: formData,			
			contentType: false,			
			processData: false,
			success: function (data) {
				if(data.code==200){
					toastrSuccess(data.msg,3000);	 
				} else{
					toastrError(data.msg,3000);
				}  
			},
			error: function () {
				toastrError("请求失败");
			}
		});	
      }

        //JS校验文件格式是否为excel    
          function checkData(fileDir){                 
             var suffix = fileDir.substr(fileDir.lastIndexOf("."));    
             if("" == fileDir){    
                 toastrError("选择需要导入的Excel文件！");
                 return false;    
             }    
             if(".xls" != suffix && ".xlsx" != suffix ){    
                 toastrError("选择Excel格式的文件导入！");         
                 return false;    
             }    
             return true;    
          } 
           
      
      function  initCSV(csv){
    	   /* var rows=csv.length;
		    for(var i=0;i<rows;i++){	    	
		      var count=Object.keys(csv[i]).length;	      
		      var text="";
		      for(var j=0;j<count;j++){
		    	  var key=j+"_column";
		    	  text+=csv[i][key]+" ";	    	 
		      }	    
		       console.log(text);		       		       
		    }	*/
		    layer.open({
		    	   type: 2,
		    	   area: ['700px', '450px'],
		    	   fixed: false, //不固定
		    	   maxmin: true,
		    	   content: basepath+'/go/goCsvData',
		    	   success:function(layero,index){
		    		   var body=layer.getChildFrame('body',index);
		    		   var $csv=body.find("#csv");
		    		   var json = JSON.stringify(csv);
		    		   $csv.val(json);
		    	   }
		    	 });		   
      }
      
      
    $("#testBtn").click(function(){
    	
    });