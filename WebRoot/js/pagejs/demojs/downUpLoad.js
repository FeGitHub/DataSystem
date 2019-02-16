 /****
  * demo：处理文件上传和下载的js
  */
$(function () {  
	 	/****
	 	 * 上传文件资源到服务器（项目路径）
	 	 */
		$("#submitBtn").click(function(){
			var url="/file/uploadFileToProject";			
			var file=$('#fileid')[0].files[0];		 			
			clickAction(file,url);
		})		
		
		/***
		 * 上传文件资源到服务器(任意位置)
		 */
		$("#testBtn").click(function(){
			var url="/file/uploadFile";
			var file=$('#testId')[0].files[0]		 		
			clickAction(file,url);
		})		
		
		
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