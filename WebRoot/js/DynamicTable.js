/***
 * jquery动态创建表格
 */
var TableNum=0;//全局变量，用于记录该页面生成的表格的数量（该页面允许生成多个表格）	
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
		 TableNum++;
		 var tableId="tableId"+TableNum;//表格的唯一id
		 var tableDiv="<table border='8' id="+tableId+"></table>";
		 $("#createTableArea").prepend(tableDiv);//构建table
		 var tempTD="";
		  for(var i = 0; i <row; i++){//构建表格tr
			  $("#"+tableId).prepend("<tr></tr>"); 
		  }
		  for(var i = 0; i <row; i++){//构建td           	
            	tempTD="";
            	for(var j = 0; j <column; j++){
            		if(i==0){
            			tempTD+="<th>标题</th>" ;
            		}else{            		          		
            			tempTD+=(j==0)?"<td><input type='checkbox'></td>":"<td></td>";
            		}     		          	
               	}
            	$('#'+tableId+' tr:eq('+i+')').prepend(tempTD);
           }
         changeTableCss();//修改表格样式     
         //增加相应按钮
         var btns=" <button value="+tableId+" class='addbtn'>新增</button>"
        	 	 +" <button value="+tableId+" class='delbtn'>删除</button>"
        	 	 +" <button value="+tableId+">上移</button>"
        	 	 +" <button value="+tableId+">下移</button><br><br>";
   	  $("#"+tableId).after(btns);//增加表格的操作按钮
   	  registerTableDragEvent();
    	  	
	}
	
	/***
	 * 动态修改表格样式
	 */
	function changeTableCss(){
		 $("td").css("text-align","center");
		 $("td").css("width",140);
         $("td").css("height",40);
         $('tr td:first-child').css("width",50);
       
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
	
	/**
	 * 键盘上下键移动
	 */
	//键盘操作
	$(document).keydown(function (event) {
	    if(event.keyCode==38){//向上移动键
	    	alert("上");
	    }
	    if(event.keyCode==40){//向下移动键
	    	alert("下");
	    }
	});
	
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
	            // 还原鼠标样式
	            // if (isNullOrUndefined(dragTH)) {
	            //     dragTH = $(this);
	            // }
	            dragTH.mouseDown = false;
	            // $(dragTH).css({
	            //     cursor : "default"
	            // });
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
        			tempTD+=(j==0)?"<td><input type='checkbox'></td>":"<td></td>";        	  		          	
           	}
        	tempTD="<tr>"+tempTD+"</tr>";
        	$("#tableId1").append(tempTD);
	    });