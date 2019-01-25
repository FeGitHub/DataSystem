 $(function () {  
	 	/****
	 	 * 上传文件资源到服务器（项目路径）
	 	 */
		$("#submitBtn").click(function(){
			var url="/file/uploadFileToProject";
			var formData = new FormData();
			formData.append('file', $('#fileid')[0].files[0]);  
			clickAction(formData,url);
		})		
		
		/***
		 * 上传文件资源到服务器(任意位置)
		 */
		$("#testBtn").click(function(){
			var url="/file/uploadFile";
			var formData = new FormData();
			formData.append('file', $('#testId')[0].files[0]);  
			clickAction(formData,url);
		})		
		
		
		/****
		 * demo:财务excel信息导入测试
		 */
		$("#financeBtn").click(function(){	
			var url="/excel/importFinanceToDB";
			var formData = new FormData();
			formData.append('file', $('#financeId')[0].files[0]); 			
			clickAction(formData,url);
		})	
    }); 
 
      /***
       * 请求格式封装
       * @param formData
       * @param url
       */
      function clickAction(formData,url){
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
