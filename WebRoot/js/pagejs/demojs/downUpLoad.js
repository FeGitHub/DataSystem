 $(function () {  
	 	/****
	 	 * 上传文件资源到服务器（项目路径）
	 	 */
		$("#submitBtn").click(function(){
			var formData = new FormData();
			formData.append('file', $('#fileid')[0].files[0]);  
			$.ajax({
				url: basepath+"/file/uploadFileToProject",
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
		})		
		
		/***
		 * 上传文件资源到服务器(任意位置)
		 */
		$("#testBtn").click(function(){
			var formData = new FormData();
			formData.append('file', $('#testId')[0].files[0]);  
			$.ajax({
				url: basepath+"/file/uploadFile",
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
		})		
		
		/****
		 * 读取excel文件信息
		 */
		$("#exceltBtn").click(function(){				
			var formData = new FormData();
			formData.append('file', $('#execlId')[0].files[0]);  
			$.ajax({
				url: basepath+"/file/getInfoFromExcel",
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
		})		
    }); 
