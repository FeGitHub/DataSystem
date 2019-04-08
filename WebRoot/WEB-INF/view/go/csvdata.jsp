<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		 <script src="${BASE_PATH}/js/jquery-3.3.1.min.js"></script> 
		 <link rel="stylesheet" type="text/css" href="${BASE_PATH}/css/pagecss/go/csvdata.css">
		 
	</head>
	<body>
	    <div align="center"><button id="analyseBtn">分析数据</button></div>
		<div class="container">
			<table class="table" id="tableList">		
				<!-- <thead>
					<tr>
						<td>姓名</td>
						<td>姓别</td>
						<td>年龄</td>
						<td>操作</td>
					</tr>	
				</thead>				
				
				<tr>
					<td class="name">张三</td>
					<td class="sex">男</td>
					<td class="age">20</td>
					<td>
						<button class="delbtn ">删除</button>
						<button class="movebtn ">修改</button>
						<button class="viewbtn ">查看</button>
					</td>
				</tr>
				<tr>
					<td class="name">李四</td>
					<td class="sex">女</td>
					<td class="age">21</td>
					<td>
						<button class="delbtn ">删除</button>
						<button class="movebtn ">修改</button>
						<button class="viewbtn ">查看</button>
					</td>
				</tr>
				<tr>
					<td class="name">赵钱</td>
					<td class="sex">不男</td>
					<td class="age">22</td>
					<td>
						<button class="delbtn ">删除</button>
						<button class="movebtn ">修改</button>
						<button class="viewbtn ">查看</button>
					</td>
				</tr>
				<tr>
					<td class="name">孙李</td>
					<td class="sex">不女</td>
					<td class="age">23</td>
					<td>
						<button class="delbtn ">删除</button>
						<button class="movebtn ">修改</button>
						<button class="viewbtn ">查看</button>
					</td>
				</tr>
				<tfoot>
					<tr>
						<td colspan="4">
							<button class="addbtn ">增加</button>
						</td>
					</tr>
				</tfoot> -->				
			</table>
			<input type="text" id="csv" style="display:none">
			<!--增加的模态框-->
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
		 <script src="${BASE_PATH}/js/pagejs/go/csvdata.js"></script> 
	</body>
</html>