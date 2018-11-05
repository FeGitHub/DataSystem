<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>表单验证框架</title>
</head>
<body>
 	
 	<div align="center" class="panel-body">
 	<h1 align="center">表单验证框架</h1>
		<form> 
				  不可空白:<input type="text" class="validate[required]"> 
				只能数字：<input type="text" class="validate[required,custom[onlyNumberSp]]">  
				 只能中文：<input type="text" class="validate[required,custom[chinese]]"> 
				 <input type="checkbox" name="test" class="validate[minCheckbox[1]] " value="1"></input>  
				 <input type="checkbox" name="test" class="validate[minCheckbox[1]] " value="2"></input> <br><br>
				 <input  maxlength="30"  name="range_min" id="range_min" type="text" class="validate[funcCall[rangeInputIsRight],custom[onlyNumberSp]] form-control input-sm" style="width:200px;">	
				  <input  maxlength="20"  name="range_max" id="range_max" type="text" class="validate[funcCall[rangeInputIsRight],custom[onlyNumberSp]] form-control input-sm" style="width:200px;" > 						            
                <button type="button" id="submitBtn">提交</button>
		</form> 
		</div>	
<hr>
				
<!-- jquery.validationEngine.min.js有改动要和特定版本的jquery搭配使用 -->
<script src="${BASE_PATH}/js/validation/lang/jquery-1.11.1.min.js"></script>
<script src="${BASE_PATH}/js/validation/lang/jquery.validationEngine-zh_CN.js"></script>
<script src="${BASE_PATH}/js/validation/jquery.validationEngine.min.js"></script>		
<script type="text/javascript">
	  $(function(){
		$('form').validationEngine({   
  			promptPosition: 'centerReight',
  			scroll:false
  		});
		 $("#submitBtn").click(function(){
			 if($("form").validationEngine('validate')){		
					alert("通过");
				}
			}); 		
	}); 	
	  //自定义检验方法
      function rangeInputIsRight(field, rules, i, options){              
     	 var range_min =$("#range_min").val(); 
     	 var range_max = $("#range_max").val(); 
     	  if(range_min==""&&range_max==""){
         	 rules.push('required');
         	 return "* 合法范围最少填写一个";
         }   
         if(range_min!=""&&range_max!=""){ 
             range_min = parseInt(range_min);  
             range_max = parseInt(range_max);                                     
         	if(range_min>=range_max){                      	                                               	
         	 rules.push('required');
         	 return "* 合法范围大小设置出错";
         	}
         }                               
     }	
</script>
</body>

</html>