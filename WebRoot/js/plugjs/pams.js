//https://www.cnblogs.com/cboyce/p/6029782.html


/****
 * PAMS 系统API
 */
(function(){
    //定义一些默认参数
    var _options={
        default_word:"PAMS"                
    }

    //定义一些api
    var _plugin_api = {
    		
    	/***
    	 *  身份证获取出生日期	
    	 */
        getBirthday:function(){
        	  if(!sSfzh||sSfzh.length==0)return '';
              if(sSfzh.length==18){
                var year = sSfzh.substring(6,10);
                var month = sSfzh.substring(10,12);
                var day = sSfzh.substring(12,14);
                return year + "-" + month + "-" + day;
              } else{
            	  return '';
              }     
        },
        
        /***
         * 身份证获取性别 
         */
        getSex:function(){
        	switch(parseInt(sSfzh.substring(16,17))){
	    	case 1:return "1";
	    	case 3:return "1";
	    	case 5:return "1";
	    	case 7:return "1";
	    	case 9:return "1";
	    	default:return "2";
		    }
      },
      
      /***
       * 判空处理
       * @param data
       * @returns {Boolean}
       */    
      isNull:function(data){
    	var result = false;
  		if(!data || data.length == 0){
  			result = true;
  		}
  		return result;
    },
   
    
    /***
     *  进一步封装ajax
     */
    ajaxDone:function(param){
   	 //检验基本参数是否完整
   	 var url=param.url;
   	 if(PAMS.isNull(url)){
   		 alert("请求资源地址不能为空！");
   		 return;
   	 }
   	 url=basepath+url;
   	 var postData=param.data==null?{}:param.data;//请求数据
   	 var defaultSuccessFn=function(data){
   		 alert(data.msg);
   	 }
   	 postData=param.form==null?postData:$("#"+param.form).serialize();
   	 var successFn=param.successFn==null?defaultSuccessFn:param.successFn;//请求成功方法
   	 $.ajax({
   			url:url,
   			type:"post",
   			data:postData,
   			dataType:"json",
   			error: function (data,type, err) {
   				layer.alert(err, {
   					icon: 5,
   					title: "请求失败"
   					});            
   	        },
   			success:function(data){
   				if(data.code!=400){
   					successFn(data);
   					return;
   				}
   				var  msg=PAMS.getNewline(data.msg);  				
   				layer.alert(msg, {
   					icon: 5,
   					title: "提示"
   					});
   			}
   		});	 
    },     
   /****
    * 字符换行
    * @param val
    * @returns {String}
    */
     getNewline:function (val) {  
	    var str = new String(val);  
	    var bytesCount = 0;  
	    var s="";
	    for (var i = 0 ,n = str.length; i < n; i++) {  
	        var c = str.charCodeAt(i);  
	        //统计字符串的字符长度
	        if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {  
	            bytesCount += 1;  
	        } else {  
	            bytesCount += 2;  
	        }
	        //换行
	        s += str.charAt(i);
	        if(bytesCount>=30){  
	         s = s + '<br>';
	         //重置
	         bytesCount=0;
	        } 
	    }  
	    return s;  
	}, 	  
   //打开模态框
    showModel:function (_html) {      	 		
    		$("#pamsModal .modal-body").html(_html);	
    		$("#pamsModal").modal("show");
    		$("#pamsUpdateBtn").hide();	
	},
	/****
	 * 模态框操作
	 * @param doMethod
	 */
	  doShowModel:function (doMethod) {    		  
		  $("body").on("click","#userUpdateBtn",function(){
			  doMethod();
			  $("#pamsModal").modal("hide");
			})	
	 },
	 /***
	  * 封装警告框
	  * @param err
	  */
	 alert:function (err) {    		  
		 layer.alert(err, {
				icon: 5,
				title: "请求失败"
				});                
	 },
	 /***
	  * 封装确认框
	  * @param err
	  */
		 confirm:function (msg,doMethod) {    		  
			 layer.confirm(msg, {			
				    btn: ['确定', '取消'], //按钮
				    skin: 'btnClass',
				    icon: 2,
				    title: "提示",
		}, function () {
		    layer.closeAll('dialog');       
		    doMethod();
		});	          
	 },	 
  }
  
 
    //这里确定了插件的名称
    this.PAMS = _plugin_api;
})();



