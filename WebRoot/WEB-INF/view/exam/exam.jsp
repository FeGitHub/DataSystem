<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>试题模块</title>
</head>
<body>
<div class="tile">												    				       	                			                                                      		
 <button type="button" id="exam" class="btn btn-info mt5 mr3 keepPlace" data-step="3" >随机试题</button>		
 <button type="button" id="newQuestion" class="btn btn-info mt5 mr3 keepPlace" data-step="3" >新建试题</button>	
 		    	    								  	
</div>
<div class="tile">	
         <form  id="filterForm">	
                        <div class="animated-radio-button">
						               <label>
						                   <input type="radio" name="filter" value="1" checked><span class="label-text">抽中10分钟过滤</span>
						               </label>
						                <label>
						                   <input type="radio" name="filter" value=""><span class="label-text">不做过滤限制</span>
						                </label>						     
			             </div>	
			             
		</form>
</div>

<div class="tile">	
   <span id="size"></span>
</div>



<template id="template">
    <div class="panel mbn">
       <div class="panel-body" >
       <form id="templateForm" style="margin-top:20px;">
		<table class="table table-bordered table-hover">
          <tbody>       
            <tr>
				<td class="va-m" width="30%"><span>试题</span><input value="" id="key" name="id" style="display:none;"></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                           <textarea rows="5" cols="60" id="question" name="question"></textarea>
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>     
             <tr id="showAnswer">
				<td class="va-m" width="30%"><span>答案</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                           <textarea rows="5" cols="60" id="answer" name="answer"></textarea>
	                  	</div>	                  	
	                 </div>	                
              	</td>             
            </tr>        
             <tr id="operator">
				<td class="va-m" width="30%"><span>显示答案</span></td>
              	<td>
	              	<div class="row">
				          <div class="col-sm-12">	                                     
			                     <div class="animated-radio-button">
						               <label>
						                   <input type="radio" name="show" value="0" checked><span class="label-text">隐藏</span>
						               </label>
						                <label>
						                   <input type="radio" name="show" value="1"><span class="label-text">展示</span>
						                </label>						     
			                       </div>
				           </div>	                  	
	                 </div>	  
              	</td>              	
            </tr>          
          </tbody>
        </table>
        </form>
      </div>
    </div>
</template>


<script src="${BASE_PATH}/js/pagejs/exam/exam.js"></script>
</body>
</html>