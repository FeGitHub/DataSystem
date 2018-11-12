/****
 * 备忘提醒页面的js
 */
$(function(){
	var table=$('#remindTable').DataTable({
			language: {
		     "url": basepath+"/json/datatables_language.json"//国际化文件的文件资源
		 },
		 bFilter: false, //去掉默认搜索框
		 bLengthChange: false, //去掉显示总页数
		 ajax: {
		     url: basepath+'/remind/getRemindDetails',
		 },
		 columns: [
		           { data: 'remindName' },//事项简称	     
		           { data: null,//详情
		         	  "render": function ( data, type, full, meta ) {//此方法可以对返回的json数据进行加工            
		           		 var str = setPopverStyle(full.remindName,full.remindText,"详情");  	           		
		           		 return str;
		           	}
		           },
		           { data: 'remindTime'},//提醒时间
		           { data: 'mail'},//提醒邮箱
		           { data: null,//操作部分
		         	  "render": function ( data, type, full, meta ) {//此方法可以对返回的json数据进行加工            
		           		 var str = "";  
		           		 str+="<button class='btn table_btn btn-danger btn-sm edtiBtn'  target='_blank' id="+full.id+">修改</button>";
		           		 return str;
		           	}
		           }
		       ]
		});
		
	   /***
	    * 表格绘画成功后
	    */
	    table.on( 'draw', function () {
	    	 $("[data-toggle='popover']").popover();
		} );
	    
	    /***
	     * 编辑表格行数据
	     */
		$("body").on("click",".edtiBtn",function(){
		    var htm = $($('#testTemp').html());
			var _html='<div>'+htm[0].outerHTML+'</div>';
			$("#seeMethodModal").modal("show");
			$("#seeMethodModal .modal-body").html(_html);			  
			
	}); 
	   
	    /****
	     *  function 设置气泡弹出框
	     *  content 文本内容
	     *  title 文本内容主题
	     *  textStr 点击文本语
	     */
	    function setPopverStyle(title,content,textStr){
	    	var str="<a tabindex='0'  data-toggle='popover' data-trigger='focus' title="+title+" data-content="+content+">"+textStr+"</a>";
	    	return str;	    	
	    }
     
} );


