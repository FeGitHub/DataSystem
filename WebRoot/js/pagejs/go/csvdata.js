$(function(){
	            showCSV();
				//增加
				var arrAdd=[]
				$(document).on("click",".addbtn",function(){
					
					$(".addfade").fadeIn(600)
				})
				//增加确定
				$(document).on("click",".addbtn_ok",function(){
					$(".addfade").fadeOut(600)
					arrAdd=[]
					$('#addmodal').find("input").each(function(){
						arrAdd.push($(this).val())						
					})
					var str='<tr><td class="name">'+arrAdd[0]+'</td><td class="sex">'+arrAdd[1]+'</td><td class="age">'+arrAdd[2]+'</td><td><button class="delbtn ">删除</button> <button class="movebtn ">修改</button> <button class="viewbtn ">查看</button></td></tr>'
					$("tbody").append(str)
					$('#addmodal').find("input").val('')
				})
				
				$(document).on("click",".addbtn_no",function(){
					$(".addfade").fadeOut(600)
				})
				
				$(document).on("click",".modal-header i",function(){
					$(".addfade").fadeOut(600)
				})
				
				
				
				//删除
				var del=[]
				$(document).on("click",".delbtn",function(){
					del=[]
					if(confirm("确定删除吗？")){
						$(this).parents("tr").fadeOut(300)
					}
				})
				
				
				//修改
				var arrMove=[];
				var _this = null ;
				$(document).on("click",".movebtn",function(){
					arrMove=[]
					_this=$(this).parents("tr")
					$(".movefade").fadeIn(600)
					$(this).parent().siblings().each(function(){
						arrMove.push($(this).text())
						
					})
					
					$(this).parents().find('.movemodal input').each(function(i){
						$(this).val(arrMove[i])
						
					})
 
					
					
				})
				
				//修改确定
				$(document).on("click",".addbtn_ok",function(){
					arrMove=[]					
					$(this).parent().siblings().find('input').each(function(){
						arrMove.push($(this).val())
						console.log($(this))
					})
					console.log(arrMove)
					$(this).parents().find('.movemodal input').each(function(i){
						$(this).val(arrMove[i])
						
					})
					
					_this.find("td").each(function(i){
						$(this).text(arrMove[i])
					})
					
					$(".movefade").fadeOut(600)
				})
				//修改取消
				$(document).on("click",".addbtn_no",function(){
					$(".movefade").fadeOut(600)
				})
				
				$(document).on("click",".modal-header i",function(){
					$(".movefade").fadeOut(600)
				})
				
				
				//查看
				var arrView=[]
				$(document).on("click",".viewbtn",function(){
					arrView=[]
					$(".viewfade").fadeIn(600)
					$(this).parent().siblings().each(function(){
						arrView.push($(this).text())
					})
					$(".modal-body").find("p span").each(function(i){
						$(this).text(arrView[i])
						console.log(arrView)
					})
					
					
				})
				
				//查看取消
				$(document).on("click",".addbtn_no",function(){
					$(".viewfade").fadeOut(600)
				})
				
				$(document).on("click",".modal-header i",function(){
					$(".viewfade").fadeOut(600)
				})
			})
			
 
/***
 * 将读取的csv文件信息以表格形式展示出来
 */
 function showCSV(){
	 var csv =$("#csv").val();
	 if(csv==null){
		 $(".table").html("<h1 align='center'>数据为空</h1>");
		 return;
	 }
	 var arr=JSON.parse(csv);
	 var col=Object.keys(arr[0]).length;
     var _html="";
     var rows=arr.length;
    for(var i=0;i<rows;i++){    	
    	if(i==0){
    		_html+="<thead><tr>";
    	} else{
    		_html+="<tr>";
    	}      	
      var count=Object.keys(arr[0]).length;
      for(var j=0;j<count;j++){
    	  var key=j+"_column";	  		
    	 _html+="<td>"+arr[i][key]+"</td>" 	
      }
      if(i==0){
    		_html+="</tr></thead>";
    	}else{
    		_html+="</tr>";
    	}
      if(i==(rows-1)){
    	  //_html+="<tfoot><tr><td colspan='"+count+"'><button id='addBtn'>新增数据</button></td></tr></tfoot>";
    	  _html+="<tfoot><tr><td colspan='"+count+"'></td></tr></tfoot>"
  	 }
     		       
    }	
    $(".table").html(_html); 
}

 $("body").on("click","#analyseBtn",function(){
	 var $tb= $("#tableList").find("tbody");
	 var $thead= $("#tableList").find("thead").find("tr");
	 var ths=$thead.find("td");
	 var thStr="";//标题
	 for(var i=0;i<ths.length;i++){
		 if(i==0){
			 thStr=ths.eq(i).text();
		 }else{
			 thStr+=","+ths.eq(i).text();
		 }		 
	 }
	 console.log(thStr)
	 var trList = $tb.children("tr");
	 var arg=[];
	 for (var i=0;i<trList.length;i++) {
	        var tdArr = trList.eq(i).find("td");
	        var str="";
	        var params = []; 
	         for(var j=0;j<tdArr.length;j++){        	 
	        	 var td=tdArr.eq(j).text();	 
	        	 str+=" "+td;
	        	 params.push(td);
	         }  
	         arg.push(params);
	         console.log(str);
	    }
	   console.log(arg);
	   var json=JSON.stringify(arg).toString();
	   
 });
 
 $("body").on("click","#customAnalyseBtn",function(){
	 var load=layer.msg('数据处理中', {
		  icon: 16
		  ,shade: 0.01,
		  time:false //取消自动关闭
		});
		$.ajax({
			url:basepath+"/analyse/customAnalyse",
			type:"post",		
			dataType:"json",
			success:function(data){
				if(data.code==200){							
					toastrSuccess(data.msg,3000);
					var index = parent.layer.getFrameIndex(window.name); 
					var rows=data.rows;
					var _html="";
					for(var i=0;i<rows.length;i++){
						if(i==0){
							_html=rows[i]+"<br>";
						}else{
							_html+=rows[i]+"<br>";
						}
					}
					parent.$('#result').html(_html);
					parent.$('.card').css("display","block");
					layer.close(load);
					parent.layer.close(index); //再执行关闭
					
				}else{							
					toastrError(data.msg,3000);
				}							
			}
		});			
 })