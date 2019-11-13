//https://www.cnblogs.com/cboyce/p/6029782.html
(function(){
    //定义一些默认参数
    var _options={
        default_word:"default hello"                
    }

    //定义一些api
    var _plugin_api = {
        firstFunc:function(_options){
        	  alert(_options.default_word);
             // return this;//返回当前方法
        },
        secondFunc:function(){
            alert("secondFunc");
           // return this;//返回当前方法
        },
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
           // return this;
        },
        getSex:function(){
        	switch(parseInt(sSfzh.substring(16,17))){
	    	case 1:return "1";
	    	case 3:return "1";
	    	case 5:return "1";
	    	case 7:return "1";
	    	case 9:return "1";
	    	default:return "2";
		    }
        //  return this;
      },
      isNull:function(){
    	var result = false;
  		if(!data || data.length == 0){
  			result = true;
  		}
  		return result;
       // return this;
    },
         
    }
    //这里确定了插件的名称
   // this.CJPlugin = _plugin_api;
    this.PAMS = _plugin_api;
})();

