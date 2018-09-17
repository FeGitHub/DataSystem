/***
 * jquery动态创建表格
 */

	/***
	 * 生成表格的按钮点击事件
	 */
	$("#createTableBtn").click(function(){
		var tableInfo=prompt("请输入表格行数和列数（如: 3,4）","");
		var tableArray=new Array();
		tableArray=tableInfo.split(",");
		if(tableArray.length!=2||(!isRight(tableArray[0]))||(!isRight(tableArray[1]))){
			alert("输入数据格式有误！");
			return;
		}
		addTable(tableArray[0],tableArray[1]);
	
	});
	
	/***
	 * 动态生成表格的主体
	 * @param row 行
	 * @param column 列
	 */
	function addTable(row,column){
		 row++;//增多一行用于放置表头
		 var tableId="tableId1";//表格的唯一id
		 var tableDiv="<table border='8' id="+tableId+"></table>";
		 $("#createTableArea").prepend(tableDiv);//构建table
		 var tempTD="";
		  for(var i = 0; i <row; i++){//构建表格tr			
				  $("#"+tableId).append("<tr></tr>"); 						 
		  }
		  for(var i = 0; i <row; i++){//构建td           	
            	tempTD="";
            	for(var j = 0; j <column; j++){
            		if(i==0){
            			tempTD+="<th>标题</th>" ;
            		}else{            		          		
            			tempTD+=(j==0)?"<td><input type='checkbox' name='checkItem'></td>":"<td>"+i+"</td>";
            		}     		          	
               	}
            	$('#'+tableId+' tr:eq('+i+')').prepend(tempTD);
           }
    
         //增加相应按钮
         var btns=" <button value="+tableId+" class='addbtn'>新增</button>"
        	 	 +" <button value="+tableId+" class='delbtn'>删除</button>"
        	 	 +" <button value="+tableId+" class='upbtn'>上移</button>"
        	 	 +" <button value="+tableId+" class='testBtn'>测试</button>"
        	 	 +" <button value="+tableId+"  class='downbtn'>下移</button><br><br>";
   	  $("#"+tableId).after(btns);//增加表格的操作按钮
   	  registerTableDragEvent();
    	  	
	}
	

	/***
	 * 判断是生成表格的参数是否正确	
	 */
	function isRight(val) {
		if(val === "" || val ==null){
	        return false;
	    }
	    if(!isNaN(val)){
	        return true;
	    }else{
	        return false;
	    }
	}
	
	
	/****
	 * 判断是否是null或Undefined
	 * @param obj
	 * @returns {Boolean}
	 */
	 function isNullOrUndefined(obj) {
	        if (typeof(obj) == "undefined" || obj == null) {
	            return true;
	        }
	        return false;
	    }

	 	/***
	 	 * 可通过鼠标拖动拉宽、缩小表格列宽
	 	 */
	    function registerTableDragEvent() {
	        var dragTH; //记录拖拽的列
	        //第一步按下
	        $('#tableId1 th,#tableId1 td').mousedown(function(e) {
	            e = e || window.event;
	            if (e.offsetX > $(this).innerWidth() - 10) {
	                dragTH = $(this);
	                dragTH.mouseDown = true;
	                dragTH.oldX = e.pageX || e.clientX;
	                dragTH.oldWidth = $(this).innerWidth();
	            }
	        })
	        //第二步 拖动
	        $('#tableId1 th,#tableId1 td').mousemove(function(e) {
	            //改鼠标样式
	            if (e.offsetX > $(this).innerWidth() - 10) {
	                $(this).css({
	                    cursor: "e-resize"
	                });
	            } else {
	                $(this).css({
	                    cursor: "default"
	                });
	            }
	            if (isNullOrUndefined(dragTH)) {
	                dragTH = $(this);
	            }
	            if (!isNullOrUndefined(dragTH.mouseDown) && dragTH.mouseDown == true) {
	                var difference = (e.pageX - dragTH.oldX) || (e.clientX - dragTH.oldX);
	                var newWidth = dragTH.oldWidth + difference; //新的宽度
	                dragTH.width(newWidth)
	            }
	        })
	        // 第三步，释放
	        $("table#tableId1 th,#tableId1 td").mouseup(function(e) {	          
	            dragTH.mouseDown = false;	          
	        });
	    }
	    
	    /***
	     * 删除动作
	     */
	    $("#createTableArea").on('click','.delbtn',function(){
	    	if( $("input:checkbox:checked").length==0){
	    		alert("您未选中任何数据！！");
	    		return;    		
	    	}
	    	 $.each($('input:checkbox'),function(){
	                if(this.checked){
	                	$(this).parent().parent().remove();
	                }
	            });
	    });
	    
	    /**
	     * 增加行
	     */
	    $("#createTableArea").on('click','.addbtn',function(){
	    	var tempTD="";
	    	var column=$("table tr th").length;
        	for(var j = 0; j <column; j++){        		            		          		
        			tempTD+=(j==0)?"<td><input type='checkbox' name='checkItem'></td>":"<td></td>";        	  		          	
           	}
        	tempTD="<tr>"+tempTD+"</tr>";
        	$("#tableId1").append(tempTD);
	    });
	    
	    /***
	     * 按钮上移
	     */
	    $("#createTableArea").on('click','.upbtn',function(){
	    	if($("input:checkbox:checked").length>1){
				alert("移动操作请只选择一项数据");
				return;
			}
	    	var current=$("input:checkbox:checked").parent().parent();
	    	  if(current.index()!=1){//向上移动键    	
			    	 var prev = current.prev();
			    	 if(prev){
			    		 current.insertBefore(prev);
			    	 }    	    	    	
			    }
	    });
	    
	   /***
	    * 按钮下移
	    */
	    $("#createTableArea").on('click','.downbtn',function(){
	    	if($("input:checkbox:checked").length>1){
				alert("移动操作请只选择一项数据");
				return;
			}
	    	var current=$("input:checkbox:checked").parent().parent();
	    	   var endRow=$("#tableId1 tr").length;
	    	   if(current.index()!=endRow){
	    		   var next = current.next();  
			    	 if(next){
			    		 current.insertAfter(next);
			    	 }   
	    	   }	            	
	    });	    
	     
	    /**
		 * 键盘上下键移动
		 */
		//键盘操作
		$(document).keydown(function (event) {
			 confirm();
			if($("input:checkbox:checked").length>1){
				alert("移动操作请只选择一项数据");
				return;
			}
			var current=$("input:checkbox:checked").parent().parent();
		    if(event.keyCode==38&&current.index()!=1){//向上移动键    	
		    	 var prev = current.prev();
		    	 if(prev){
		    		 current.insertBefore(prev);
		    	 }    	    	    	
		    	}
		    var endRow=$("#tableId1 tr").length-1;
		    if(event.keyCode==40&&current.index()!=endRow){//向下移动键	    	
		    	 var next = current.next();  
		    	 if(next){
		    		 current.insertAfter(next);
		    	 }    	
		    }
		});
		
		//=================================
		
		/***
		 * 编辑数据表格
		 */
		 $("#createTableArea").on('click','.testBtn',function(){
			 confirm();	
		    });	    
		
			
		/****
		 * 弹出框
		 */
		function confirm() {
		    if ($("#myConfirm").length > 0) {
		        $("#myConfirm").remove();
		    } 
		  
		    var mainContent="eeee";
		    var html = "<div class='modal fade' id='myConfirm' >"
		            + "<div class='modal-backdrop in' style='opacity:0; '></div>"
		            + "<div class='modal-dialog' style='z-index:2901; margin-top:60px; width:600px; '>"
		            + "<div class='modal-content'>"
		            + "<div class='modal-header'  style='font-size:16px; '>"
		            + "<span class='glyphicon glyphicon-envelope'>&nbsp;</span>信息！<button type='button' class='close' data-dismiss='modal'>"
		            + "<span style='font-size:20px;  ' class='glyphicon glyphicon-remove'></span><tton></div>"
		            + "<div class='modal-body text-center' id='myConfirmContent' style='font-size:18px; '>"
		            + mainContent
		            + "</div>"
		            + "<div class='modal-footer ' style=''>"
		            + "<button class='btn btn-danger ' id='confirmOk' >确定<tton>"
		            + "<button class='btn btn-info ' data-dismiss='modal'>取消<tton>"
		            + "</div>" + "</div></div></div>";
		    $("body").append(html);
		    $("#myConfirm").modal("show");
		}