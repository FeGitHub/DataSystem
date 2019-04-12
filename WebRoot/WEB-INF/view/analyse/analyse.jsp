<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>数据分析</title>
    <link rel="stylesheet" href="${BASE_PATH}/css/pagecss/analyse/analyse.css">
</head>
<body>   	    	     	    
	     <div class="tile">	   
				<div class="form-group">
				   <div class="col-sm-6">
				       <div class="input-group">
				       <input id='location' class="form-control" onclick="$('#i-file').click();">
				           <label class="input-group-btn">
				               <input type="button" id="i-check" value="选择文件" class="btn btn-primary" onclick="$('#i-file').click();" style="margin-bottom:-9px;">
				           </label>
				       </div>
				   </div>			 
			    <input type="file" name="file" id='i-file'  accept=".csv"  style="display: none"> 			   			
	         </div>	
	    </div>    
	     <div class="tile">	   
	    	  <button type="button" class="btn btn-info" id="readCSVBtn">开始读取</button> 	   
	    	   <a class="btn btn-success mr-2 mb-2" href="${BASE_PATH}/file/downDemoCsv" style="margin-top:8px;margin-left:25px;"><i class="fa fa-download"></i>案例下载</a> 	
	    	   <button class="btn btn-primary mr-2 mb-2" style="margin-top:8px;margin-left:20px;" id="doc"><i class="fa fa-file" ></i>使用说明</button>  	    
	    </div>
	   	  
	   <ul class="layer_notice" style="display: none;">
		    <li><a href="https://baike.baidu.com/item/logistic%E5%9B%9E%E5%BD%92/2981575?fromtitle=%E9%80%BB%E8%BE%91%E5%9B%9E%E5%BD%92&fromid=17202449&fr=aladdin" target="_blank">1. 数据分析采用的是逻辑回归算法，详情点我</a></li>
		    <li><a href="javascript:void(0)" target="_blank">2. 外部数据导入使用csv文件，案例即可下载</a></li>
		    <li><a href="javascript:void(0)" target="_blank">3. 数据之间用逗号分隔，每一行为单独的一组数据</a></li>
		     <li><a href="javascript:void(0)" target="_blank">4. 最后一列是结果数据列，其他列是特征数据列</a></li>
      </ul>
	     <script src="${BASE_PATH}/js/pagejs/analyse/analyse.js"></script>     
     </body>
</html>