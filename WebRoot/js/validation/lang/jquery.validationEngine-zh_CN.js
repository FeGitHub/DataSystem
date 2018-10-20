(function($){
	// 验证规则
	$.fn.validationEngineLanguage = function(){};
	$.validationEngineLanguage = {
		newLang:function(){
			$.validationEngineLanguage.allRules = {
				"required":{ // Add your regex rules here, you can take telephone as an example
					"regex":"none",
					"alertText":"* 此处不可空白",
					"alertTextCheckboxMultiple":"* 请选择一个项目",
					"alertTextCheckboxe":"* 该选项为必选",
					"alertTextDateRange":"* 日期范围不可空白"
				},
				"dateRange":{
					"regex":"none",
					"alertText":"* 无效的 ",
					"alertText2":" 日期范围"
				},
				"dateTimeRange":{
					"regex":"none",
					"alertText":"* 无效的 ",
					"alertText2":" 时间范围"
				},
				"minSize":{
					"regex":"none",
					"alertText":"* 最少 ",
					"alertText2":" 个字符"
				},
				"maxSize":{
					"regex":"none",
					"alertText":"* 最多 ",
					"alertText2":" 个字符"
				},
				"groupRequired":{
					"regex":"none",
					"alertText":"* 至少填写其中一项"
				},
				"exclamLimit":{
					"regex":/^((?!([!！][!！][!！])).)*$/,
					"alertText":"* 不能连续输入两个以上的感叹号"
				},
				"min":{
					"regex":"none",
					"alertText":"* 最小值为 "
				},
				"max":{
					"regex":"none",
					"alertText":"* 最大值为 "
				},
				"notinteger": {
                	"regex": /^([1-9][\d]{0,7})?$/,
                	"alertText": "* 无效的数量，整数8位，示例：247"
                },
                "prpcurementnumber": {
                	"regex": /^([1-9][\d]{0,5})?$/,
                	"alertText": "* 无效的数值，请填写6位整数"
                },
                "notnumber": {
                	"regex": /^([1-9][\d]{0,7})?$/,
                	"alertText": "* 无效的数值，整数8位，示例：247"
                },	
                "notnumberone": {
                	"regex": /^([1-9][\d]{0,7}|0)?$/,
                	"alertText": "* 无效的数值，整数8位，示例：247"
                },
                
                "auctionBondRatio": {
                	"regex": /^([1-9][\d]{0,1}|100)?$/,
                	"alertText": "* 无效的竞价保证金比例，范围0-100间整数，示例：20"
                },
                
                "percentage": {
                	"regex": /^(((\d|[1-9]\d)(\.\d{1,2})?)|100|100.0|100.00)$/,
                	"alertText": "* 无效的百分比，示例：20.00"
                },
                
				"past":{
					"regex":"none",
					"alertText":"* 日期需在 ",
					"alertText2":" 之前"
				},
				"future":{
					"regex":"none",
					"alertText":"* 日期需在 ",
					"alertText2":" 之后"
				},	
				"maxCheckbox":{
					"regex":"none",
					"alertText":"* 最多选择 ",
					"alertText2":" 个项目"
				},
				"minCheckbox":{
					"regex":"none",
					"alertText":"* 最少选择 ",
					"alertText2":" 个项目"
				},
				"equals":{
					"regex":"none",
					"alertText":"* 两次输入的密码不一致"
				},
                "creditCard": {
                    "regex": "none",
                    "alertText": "* 无效的信用卡号码"
                },
				"phone":{
					// credit:jquery.h5validate.js / orefalo
					"regex":/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/,
					"alertText":"* 无效的电话号码,示例：010-6754783"
				},
				"faxNbr":{
					// credit:jquery.h5validate.js / orefalo
					"regex":/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/,
					"alertText":"* 无效的传真号码"
				},
				"movePhone":{
					"regex":/^1[34578]\d{9}$/,
					"alertText":"* 无效的手机号码"
				},
				"usersName":{
					"regex":/^1[34578]\d{9}$/,
					"alertText":"* 登录名请输入手机号码"
				},
				"email":{
					"regex":/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g,
					"alertText":"* 无效的邮件地址"
				},
				"integer":{
					"regex":/^[\-\+]?\d+$/,
					"alertText":"* 无效的整数"
				},
				"number":{
					// Number, including positive, negative, and floating decimal. credit:orefalo
                    "regex": /^[\-\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?([\.]([0-9]+))?$/,
					"alertText":"* 无效的数值"
				},
				"score": {
                	"regex": /^([1-9][\d]{0,2}|0)(\.[\d]{1,2})?$/,
                	"alertText": "* 无效分数，整数3位，小数2位"
                },
				"money": {
                	"regex": /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/,
                	"alertText": "* 无效金额，整数8位，小数2位，示例：247.23"
                },
                "unitmoney": {
                	"regex": /^([1-9][\d]{0,5}|0)(\.[\d]{1,2})?$/,
                	"alertText": "* 无效单价，整数6位，小数2位，示例：247.23"
                },
                "fullmoney": {
                	"regex": /^([1-9][\d]{0,11}|0)(\.[\d]{1,2})?$/,
                	"alertText": "* 无效金额，整数12位，小数2位，示例：247.23"
                },
                "bankNum": {
                	"regex": /^\d{9,30}$/,
                	"alertText": "* 无效银行卡号，数字9-30位"
                },
				"date":{
					"regex":/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/,
					"alertText":"* 无效的日期，格式必需为 YYYY-MM-DD"
				},
				"ipv4":{
					"regex":/^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,
					"alertText":"* 无效的 IP 地址"
				},
				"url":{
					"regex":/((http|ftp|https):\/\/)?[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:\/~\+#]*[\w\-\@?^=%&amp;\/~\+#])?/,
					"alertText":"* 无效的网址"
				},
				"onlyNumberSp":{
					"regex":/^[0-9\ ]+$/,
					"alertText":"* 只能填写数字"
				},
				"onlyLetterSp":{
					"regex":/^[a-zA-Z\ \']+$/,
					"alertText":"* 只能填写英文字母"
				},
				"onlyNumberLetterSp":{
					"regex":/^[0-9a-zA-Z]+$/,
					"alertText":"* 只能填写数字英文字母"
				},
				"onlyLetterNumber":{
					"regex":/^[0-9a-zA-Z_]+$/,
					"alertText":"* 只能填写数字、英文字母、下划线"
				},
				"onlyNumberAndUnderline":{
					"regex":/^[0-9\-]+$/,
					"alertText":"* 只能填写数字、短横线"
				},
				"exceptChinese":{
					"regex":/^[^\u4e00-\u9fa5]{0,}$/,
					"alertText":"* 不能输入中文"
				},
				//tls warning:homegrown not fielded 
				"dateFormat":{
					"regex":/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(?:(?:0?[1-9]|1[0-2])(\/|-)(?:0?[1-9]|1\d|2[0-8]))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(0?2(\/|-)29)(\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\d\d)?(?:0[48]|[2468][048]|[13579][26]))$/,
					"alertText":"* 无效的日期格式"
				},
				//tls warning:homegrown not fielded 
				"dateTimeFormat":{
					"regex":/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1}$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^((1[012]|0?[1-9]){1}\/(0?[1-9]|[12][0-9]|3[01]){1}\/\d{2,4}\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1})$/,
					"alertText":"* 无效的日期或时间格式",
					"alertText2":"可接受的格式： ",
					"alertText3":"mm/dd/yyyy hh:mm:ss AM|PM 或 ", 
					"alertText4":"yyyy-mm-dd hh:mm:ss AM|PM"
				},
				
				/**
				 * 正则验证规则补充
				 * Author: ciaoca@gmail.com
				 * Date: 2013-10-12
				 */
				"chinese":{
					"regex":/^[\u4E00-\u9FA5]+$/,
					"alertText":"* 只能填写中文汉字"
				},
				
				/**
				 * 正则验证规则补充
				 * Author: wangqiao@finscm.com
				 * Date: 2017-07-14
				 */
				"onlyLetterUnderline":{
					"regex":/^[a-zA-z_]+$/,
					"alertText":"填字母或下划线"
				},
				"chinaId":{
					/**
					 * 2013年1月1日起第一代身份证已停用，此处仅验证 18 位的身份证号码
					 * 如需兼容 15 位的身份证号码，请使用宽松的 chinaIdLoose 规则
					 * /^[1-9]\d{5}[1-9]\d{3}(
					 * 	(
					 * 		(0[13578]|1[02])
					 * 		(0[1-9]|[12]\d|3[01])
					 * 	)|(
					 * 		(0[469]|11)
					 * 		(0[1-9]|[12]\d|30)
					 * 	)|(
					 * 		02
					 * 		(0[1-9]|[12]\d)
					 * 	)
					 * )(\d{4}|\d{3}[xX])$/i
					 */
					"regex":/^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$|^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/,
					"alertText":"* 无效的身份证号码"
				},
				"chinaIdLoose":{
					"regex":/^(\d{18}|\d{15}|\d{17}[xX])$/,
					"alertText":"* 无效的身份证号码"
				},
				"chinaZip":{
					"regex":/^\d{6}$/,
					"alertText":"* 无效的邮政编码"
				},
				"qq":{
					"regex":/^[1-9]\d{4,10}$/,
					"alertText":"* 无效的 QQ 号码"
				},
				"onlyEnglNumber":{
					"regex":/^[0-9a-zA-Z]+$/,
					"alertText":"* 只能填写数字、英文字母"
				},
				"stock": {
                	"regex": /^([1-9][\d]{0,16}|0)(\.[\d]{1,3})?$/,
                	"alertText": "* 无效的数值，整数最大17位，小数最大3位，示例：247.223"
                },
                "password": {
                	"regex": /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,20}$/,
                	"alertText": "*6-20位数字及字母组成 区分大小写"
                },
                "phone-left":{
					// credit:jquery.h5validate.js / orefalo
					"regex":/^[0\+]\d{2,3}$/,
					"alertText":"* 无效的电话号码"
				},
				"phone-middle":{
					// credit:jquery.h5validate.js / orefalo
					"regex":/^(\d{7,8})$/,
					"alertText":"* 无效的电话号码"
				},
				"phone-right":{
					// credit:jquery.h5validate.js / orefalo
					"regex":/^\d{3,5}$/,
					"alertText":"* 无效的电话号码"
				},
				/**
				 * 自定义公用提示信息：
				 * 外部通过 $.validationEngineLanguage.allRules.validate2fields.alertText 可获取
				 *
				 * 	"validate2fields": {
				 * 		"alertText": "* 请输入 HELLO"
				 *	 },
				 * 	
				 *
				 * 自定义规则示例：
				 * 	"requiredInFunction": { 
				 * 		"func": function(field,rules,i,options){
				 * 			return (field.val()=="test") ? true : false;
				 * 		},
				 * 		"alertText": "* Field must equal test"
				 * 	},
				 *
				 *
				 * Ajax PHP 验证示例
				 * 	"ajaxUserCallPhp": {
				 * 		"url": "phpajax/ajaxValidateFieldUser.php",
				 * 		// you may want to pass extra data on the ajax call
				 * 		"extraData": "name=eric",
				 * 		// if you provide an "alertTextOk", it will show as a green prompt when the field validates
				 * 		"alertTextOk": "* 此帐号名称可以使用",
				 * 		"alertText": "* 此名称已被其他人使用",
				 * 		"alertTextLoad": "* 正在确认帐号名称是否有其他人使用，请稍等。"
				 * 	},
				 * 	"ajaxNameCallPhp": {
				 * 		// remote json service location
				 * 		"url": "phpajax/ajaxValidateFieldName.php",
				 * 		// error
				 * 		"alertText": "* 此名称已被其他人使用",
				 * 		// speaks by itself
				 * 		"alertTextLoad": "* 正在确认名称是否有其他人使用，请稍等。"
				 * 	}
				 */
			};
			
		}
	};
	$.validationEngineLanguage.newLang();
})(jQuery);