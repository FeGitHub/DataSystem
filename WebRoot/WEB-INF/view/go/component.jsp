<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>组件测试</title>
</head>
<body>	
 	<div align="center" class="panel-body">
 	<h1 align="center">组件测试</h1>		
		<a class='seeMethod'>自定义弹出框测试</a>
			<a class='testMethod'>自定义弹出框测试2</a>
			<!--弹出框基本框架开始-->
				<div class="modal" id="seeMethodModal">
						<div class="modal-dialog modal-lg ptn" style="width:700px;"><!--修改这个width可以改变整个模态框的宽度-->
						    <div class="modal-content" >
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title">预警规则</h4>
						      </div>
						      <div class="modal-body" style="height:200px;overflow:hidden;"><!--改变这个高度可以改变整个模态框的高度，但是内部元素可能撑破-->
						      </div>	     
					    </div>
					  </div>
				</div>
			<!--弹出框主体框架结束-->
			
	<!--弹出框模板-->  	
	<template id="checkMethodDiv">
    <div class="panel mbn" style="height:450px;overflow-y:auto;">
       <div class="panel-body" >
       <form id="resonFrom">
		<table class="table table-bordered table-hover">
          <tbody>
            <tr>
				<td class="va-m" width="30%"><span>预警编号</span></td>
              	<td><div class="row">
                  	<div class="col-sm-6">
                    	<div class="form-group mbn lh20 border-none"></div>
                  	</div></div>
              	</td>
            </tr>
			<tr>
              	<td class="va-m" width="30%"><span>预警名称</span></td>
              	<td><div class="row">
                  <div class="col-sm-6">
                    	<div class="form-group mbn lh20 border-none"></div>
                  </div></div>
           		</td>
            </tr>
			<tr>
              	<td class="va-m" width="30%"><span>预警类型</span></td>
              	<td><div class="row">
                  <div class="col-sm-6">
                    	<div class="form-group mbn lh20 border-none"></div>
                  </div></div>
           		</td>
           	</tr>
			<tr>
              	<td class="va-m" width="30%"><span>影响物料类型</span></td>
              	<td><div class="row">
                  <div class="col-sm-6">
                    	<div class="form-group mbn lh20 border-none"></div>
                  </div></div>
           		</td>
            </tr>
			<tr>
              	<td class="va-m" width="30%">预警处理人</span></td>
              	<td><div class="row">
                  <div class="col-sm-6">
                    	<div class="form-group mbn lh20 border-none"></div>
                  </div></div>
           		</td>
            </tr>			
			<tr>
              	<td class="va-m" width="30%">最小合理值</span></td>
              	<td><div class="row">
                  <div class="col-sm-6">
                    	<div class="form-group mbn lh20 border-none"></div>
                  </div></div>
           		</td>
            </tr>
			<tr>
              	<td class="va-m" width="30%"><span>最大合理值</span></td>
              	<td><div class="row">
                  	<div class="col-sm-6">
                    	<div class="form-group mbn lh20 border-none"></div>
                  </div></div>
                 </td>
            </tr>		
          </tbody>
        </table>
        </form>
      </div>
    </div>
</template>


<template id="testTemp">
    <div class="panel mbn" style="height:450px;overflow-y:auto;">
       <div class="panel-body" >
       <form id="testFrom">
		<table class="table table-bordered table-hover">
          <tbody>
            <tr>
				<td class="va-m" width="20%"><span>预警编号</span></td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-3">
	                    	<div class="form-group mbn lh20 border-none"></div>
	                  	</div>
	                  	
	                 </div>	                
              	</td>
              	<td>
	              	<div class="row">
	                  	<div class="col-sm-3">
	                    	<div class="form-group mbn lh20 border-none"></div>
	                  	</div>
	                  	
	                 </div>	                
              	</td>
            </tr>
			 <tr>
				<td class="va-m" width="30%"><span>预警编号</span></td>
              	<td>
	              	<div class="row">	                  
	                  	<div class="col-sm-3">
	                    	<div class="form-group mbn lh20 border-none"></div>
	                    	
	                  	</div>
	                 </div>
              	</td>
              	<td>
	              	<div class="row">	                  
	                  	<div class="col-sm-3">
	                    	<div class="form-group mbn lh20 border-none"></div>	                    	
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
<!--弹出框模板-->
</div>	
<script type="text/javascript">
	 $('body').on('click','.seeMethod',function(){	
		 var htm = $($('#checkMethodDiv').html());
			htm.find('tr:eq(0)').find('.border-none').text("fdf");
			htm.find('tr:eq(1)').find('.border-none').text("fdf");
		    htm.find('tr:eq(2)').find('.border-none').text("fdf");
		    htm.find('tr:eq(3)').find('.border-none').text("fdf");		
			htm.find('tr:eq(13)').hide();								
			htm.find('tr:eq(4)').find('.border-none').text("fdf");								
			htm.find('tr:eq(5)').find('.border-none').text("fdf");
			htm.find('tr:eq(6)').find('.border-none').text("fdf");	
			var _html='<div>'+htm[0].outerHTML+'</div>';
			$("#seeMethodModal").modal("show");
			$("#seeMethodModal .modal-body").html(_html);			
	 })	
	 
	 
	 $('body').on('click','.testMethod',function(){	
		 var htm = $($('#testTemp').html());
			htm.find('tr:eq(0)').find('.border-none').text("fdf");
			htm.find('tr:eq(1)').find('.border-none').text("fdf");		   
			var _html='<div>'+htm[0].outerHTML+'</div>';
			$("#seeMethodModal").modal("show");
			$("#seeMethodModal .modal-body").html(_html);			
	 })	
</script>
</body>

</html>