<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>试题模块</title>
</head>
<body>
<div class="tile">												    				       	                			                                                      		
 <button type="button" id="test" class="btn btn-info mt5 mr3 keepPlace" data-step="3" >随机试题</button>		
 <button type="button" id="newQuestion" class="btn btn-info mt5 mr3 keepPlace" data-step="3" >新建试题</button>				    	    								  	
</div>


<template id="template">
    <div class="panel mbn">
       <div class="panel-body" >
       <form id="templateForm" style="margin-top:20px;">
		<table class="table table-bordered table-hover">
          <tbody>       
            <tr>
				<td class="va-m" width="30%"><span>试题</span><input id="recordId" type="text" value="" style="display:none;" name="id" ></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-12">	                                     
                           <textarea rows="5" cols="60" id="qusetion"></textarea>
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