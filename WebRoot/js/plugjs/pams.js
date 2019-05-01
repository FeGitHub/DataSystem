//https://www.cnblogs.com/cboyce/p/6029782.html
(function(){
    //定义一些默认参数
    var _options={
        default_word:"default hello"                
    }

    //定义一些api
    var _plugin_api = {
        firstFunc:function(_options){
        	 // alert(_options.default_word);
              return this;//返回当前方法
        },
        secondFunc:function(){
            alert("secondFunc");
            return this;//返回当前方法
        }
         
    }
    //这里确定了插件的名称
   // this.CJPlugin = _plugin_api;
    this.pams = _plugin_api;
})();

