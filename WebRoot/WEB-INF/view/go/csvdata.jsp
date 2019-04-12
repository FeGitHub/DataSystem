<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>数据展示</title>
		  <script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script> 
		  <script src="${BASE_PATH}/js/bootstrap.min.js"></script>
		  <script src="${BASE_PATH}/js/toastr.min.js"></script>
		  <link href="${BASE_PATH}/css/bootstrap.min.css" type="text/css" rel="stylesheet">		
          <link href="${BASE_PATH}/css/toastr.min.css" type="text/css" rel="stylesheet">		
		  <link rel="stylesheet" type="text/css" href="${BASE_PATH}/css/pagecss/go/csvdata.css">		 
	</head>
	<body>
	    <div align="center">
	         <button id="analyseBtn">分析数据</button>
	         <button id="customAnalyseBtn">自定义分析数据</button>
	     </div>
		<div class="container">
			<table class="table" id="tableList">							
			</table>
			<input type="text" id="csv" style="display:none">
			<div class="modal addfade" id="addmodal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<i>X</i>
							<h4>会员管理</h4>
						</div>
						<div class="modal-body">
							<p>
								姓名：<input type="text" />
							</p>
							<p>
								姓别：<input type="text" />
							</p>
							<p>
								年龄：<input type="text" />
							</p>
						</div>
						<div class="modal-footer">
							<button class="addbtn_ok">确定</button>
							<button class="addbtn_no">取消</button>
						</div>
					</div>
				</div>
			</div>
			
			
			
			<!--修改的模态框-->
			<div class="modal movefade movemodal" id="movemodal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<i>X</i>
							<h4>会员管理</h4>
						</div>
						<div class="modal-body">
							<p>
								姓名：<input type="text" />
							</p>
							<p>
								姓别：<input type="text" />
							</p>
							<p>
								年龄：<input type="text" />
							</p>
						</div>
						<div class="modal-footer">
							<button class="addbtn_ok">确定</button>
							<button class="addbtn_no">取消</button>
						</div>
					</div>
				</div>
			</div>
			
			
			
			<!--查看的模态框-->
			<div class="modal viewfade" id="viewmodal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<i>X</i>
							<h4>会员管理</h4>
						</div>
						<div class="modal-body">
							<p>
								姓名：<span></span>
							</p>
							<p>
								姓别：<span></span>
							</p>
							<p>
								年龄：<span></span>
							</p>
						</div>
						<div class="modal-footer">
							<button class="addbtn_no">取消</button>
						</div>
					</div>
				</div>
			</div>	
		</div>	
		<script type="text/javascript">
   		 var basepath= '${BASE_PATH}';
       </script>		
		 <script src="${BASE_PATH}/js/pagejs/go/csvdata.js"></script> 
	</body>
</html>