
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
    	var id=layer.msg('数据处理中', {
    		  icon: 16
    		  ,shade: 0.01,
    		  time:false //取消自动关闭
    		});
    	$.ajax({
			url: basepath+"/analyse/regression",
			type: "POST",	
			dataType:"json",
			success: function (data) {
				layer.close(id);
				showResult(data);
			
			},
			error: function () {
				layer.close(id);
				toastrError("请求失败");
			}
		});	
    });
    
    /***
     * 展示分析结果
     */
    function showResult(data){
       var _html="<center>";  	  
   	   if(data!=null){
   		  _html+="<h2>数据处理完成</h2>";  
   		 for(var i=0;i<data.length;i++){
   	   	   _html+="<p>"+data[i]+"</p>";
   	   	 } 		
   	   }else{
   		 _html+="<h2>无任何分析数据</h2>";  
   	   } 	
   	    _html+="</center>";
	   	layer.open({
	   	type: 1,
	   	title: '数据分析结果',
	   	shadeClose: true,
	   	shade: 0.8,
	   	skin: 'layer_bg',  
	   	area: ['500px', '300px'],
	   	content: _html,  
	   });
       
    }
     