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
	    
	    /***
	     * 自动填充表单数据
	     */
	    fillForm:function (obj) {    		  
	    	 if($.type(obj)!="object"){
	    		        alert("页面初始化错误！");
	    		        return false;
	    		      }
	    		      var key,value,tagName,type,arr;
	    		      for(x in obj){
	    		        key = x;
	    		        value = obj[x];
	    		        $("[name='"+key+"'],[name='"+key+"[]']").each(function(index){
	    		          tagName = $(this)[0].tagName;
	    		          type = $(this).attr('type');
	    		          if(tagName=='INPUT'){
	    		            if(type=='radio'){                      //处理radio
	    		              $(this).attr('checked',$(this).val()==value);
	    		            }else if(type=='checkbox'){             //处理checkbox
	    		              for(var i =0;i<value.length;i++){
	    		                if($(this).val()==value[i]){
	    		                  $(this).attr('checked',true);
	    		                  break;
	    		                }
	    		              }
	    		            }else if(type=='date'){                  //处理日期型表单
	    		              if(parseInt(value)>1000000000000)      //毫秒时间戳
	    		                $(this)[0].valueAsNumber=parseInt(value);
	    		              else if(parseInt(value)>1000000000)    //秒时间戳
	    		                $(this)[0].valueAsNumber=parseInt(value)*1000;
	    		              else                                   //字符串时间
	    		                $(this)[0].valueAsDate=new Date(value);
	    		            }else{
	    		              if($.isArray(value))                    //表单组情形(多个同名表单)
	    		                $(this).val(value[index]);
	    		              else
	    		                $(this).val(value);
	    		            }
	    		          }else if(tagName=='SELECT' || tagName=='TEXTAREA'){    //处理select和textarea
	    		            if($.isArray(value))
	    		              $(this).val(value[index]);
	    		            else
	    		              $(this).val(value);
	    		          }    
	    		        }); 
	    		      }
	    },
	    /***
	     * 清空表单内容
	     * @param form
	     */
	    clear:function (form) {    		  
	    	 $(":input","#"+form)
	    	  .not(":button",":reset",":hidden",":submit")
	    	  .val("")
	    	  .removeAttr("checked")
	    	  .removeAttr("selected");
	    },	
	    	        	    
     }    
    //这里确定了插件的名称
    this.PAMS = _plugin_api;
})();

//验证手机号码     /^[1][3-9][0-9]{9}$/
//验证联系电话（手机号码和固定电话）  /^([1]\d{10}|([(（]?0[0-9]{2,3}[）)]?[-]?)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?)$/
// 验证只能存在字母和下划线                           /^([a-zA-z_]{1})([\w]*)$/
//验证只能存在数字和字母
// 验证固定电话   /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/
// 验证电子邮箱   /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/
// 验证中文字符  /[^\u4E00-\u9FA5]/
// 验证邮政编码  /^[1-9][0-9]{5}$/
// 验证纯数字    /^\d+(\.\d+)?$/
//   验证正数                 /^(0|[1-9][0-9]*)(\.\d+)?$/
//  验证字符串首尾空格   /^[\s ]|[ ]$/
//   验证特殊符号        /[`~!@#$^&*()=|{}':;',\\[\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]/
//   验证日期格式             /\d{4}(\\-|\/|.)\d{1,2}\1\d{1,2}/
//  验证IP地址    /\d+\.\d+\.\d+\.\d+/
//  15位身份证                      /^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$/
//   18s位身份证    /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/

