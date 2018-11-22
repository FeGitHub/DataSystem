/**
 * Handlebars自定义helper

 */
(function(Handlebars) {
	//全局对象，命名空间
	var object = new Object();
	
	Handlebars.registerHelper({
		/** 如果等于 */
		ifEqual: function(value,num,options) {
			if(value == num) {
				return options.fn(this);
			} else {
				return options.inverse(this);
			}
		},
		/** 如果不等于 */
		ifNotEqual: function(value,num,options) {
			if(value != num) {
				return options.fn(this);
			} else {
				return options.inverse(this);
			}
		},
		
		/** 如果存在*/
		ifExist: function(value,num1,num2,num3,options) {
			if(value == num1 || value == num2 || value == num3) {
				return options.fn(this);
			} else {
				return options.inverse(this);
			}
		},
		
		/**
		 * 如果小于
		 */
		ifLess: function(index,num,options) {
			if(index < num) {
				return options.fn(this);
			} else {
				return options.inverse(this);
			}
		},
		/**
		 * 加
		 */
		plus: function(index,num) {
			return index + num;
		},
		/**
		 * 减
		 */
		minus: function(index,num) {
			return index - num;
		},
		/**
		 * 乘
		 */
		multip:function(index,num) {
			return _.accMul(index,num);
		},
		/**
		 * 除
		 */
		division: function(index,num) {
			return index / num;
		},
		
		/**
		 * 保留小数位
		 */
		toFixed: function(index,num) {
			if(index) {
				return index.toFixed(num);
			}else {
				return 0.00;
			}
		},
		
		/**
		 * 如果整除
		 */
		ifDivisible: function(index,num,options) {
			if(index % num == 0) {
				return options.fn(this);
			} else {
				return options.inverse(this);
			}
		},
		/**
		 * 日期格式化
		 */
		dateFormat: function(time,fmt) {
			if (!time) {
				return '';
			} else if (time == 'now') {
				return moment().format(fmt);
			}
			return moment(time).format(fmt);
		},
		/**
		 * 比较一个字符串的长度
		 */
		checkStrLength: function(str,n,options){
				if(_.getBytesLength(str) > n) {
					return options.fn(this);
				} 
				else{
					return options.inverse(this);
				}
		},
		/**
		 * 字符替换(全部替换)
		 */
		replaceAllStr: function(str,repl,berepl){
			var reg = new RegExp(berepl,"g");
	           if(_.getBytesLength(str)>0){
	        	    str.replace(reg,repl);
	           }
	           return str;
		},
		/**
		 * 截取字符串
		 */
		cutStr: function(str, n){
			return _.cutStr(str, n);
		},
		/**
		 * 截取字符串
		 */
		substring: function(str, start, end) {
			if (!str) {
				return '';
			}
			return str.substring(start,end);
		},
		/**
		 * 全角字符转成半角字符
		 */
		unescapeXSS:function(str) {
			return _.unescapeXSS(str);
		},
		/**
		 * 填充字符
		 */
		fillLeft: function(source,fillChar,length) {
			return _.fillLeft(source,fillChar,length);
		},
		/**
		 * 全局唯一的id
		 */
		uniqueId: function(perfix) {
			if (perfix) {
				object.uniqueId = _.uniqueId(perfix);
			} else {
				object.uniqueId = _.uniqueId();
			}
			return object.uniqueId;
		},
		lastUniqueId: function() {
			return object.uniqueId;
		},
		/**
		 * 0,1转换成是或否
		 */
		transFormat: function(value) {
			if(value==0){
				return "是";
			}else if(value==1){
				return "否";
			}
		},
		/**
		 * 审核状态
		 */
		auditStatus:function(value) {
			if(value==0){
				return "待审核";
			}else if(value==1){
				return "审核通过";
			}else if(value==2){
				return "审核不通过";
			}
		}
	});
	
})(Handlebars)