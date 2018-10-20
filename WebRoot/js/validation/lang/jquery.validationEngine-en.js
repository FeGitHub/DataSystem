(function($){
	// 验证规则
	$.fn.validationEngineLanguage = function(){};
	$.validationEngineLanguage = {
		newLang:function(){
			$.validationEngineLanguage.allRules = {
				"required":{ // Add your regex rules here, you can take telephone as an example
					"regex":"none",
					"alertText":"* Here is blank",
					"alertTextCheckboxMultiple":"* Please select a project",
					"alertTextCheckboxe":"* This option for choice",
					"alertTextDateRange":"* Date range cannot be blank"
				},
				"dateRange":{
					"regex":"none",
					"alertText":"* invalid ",
					"alertText2":" Date range"
				},
				"dateTimeRange":{
					"regex":"none",
					"alertText":"* invalid ",
					"alertText2":" Time range"
				},
				"minSize":{
					"regex":"none",
					"alertText":"* At least ",
					"alertText2":" characters"
				},
				"maxSize":{
					"regex":"none",
					"alertText":"* Up to ",
					"alertText2":" characters "
				},
				"groupRequired":{
					"regex":"none",
					"alertText":"* Fill out at least one of them"
				},
				"min":{
					"regex":"none",
					"alertText":"* The minimum value of"
				},
				"max":{
					"regex":"none",
					"alertText":"* The maximum value for"
				},
				"past":{
					"regex":"none",
					"alertText":"* Date should be before ",
					"alertText2":" "
				},
				"future":{
					"regex":"none",
					"alertText":"* The date should be after ",
					"alertText2":" "
				},	
				"maxCheckbox":{
					"regex":"none",
					"alertText":"* Select ",
					"alertText2":" item at most"
				},
				"minCheckbox":{
					"regex":"none",
					"alertText":"* At least ",
					"alertText2":" project choice"
				},
				"equals":{
					"regex":"none",
					"alertText":"* Two input password is not consistent"
				},
                "creditCard": {
                    "regex": "none",
                    "alertText": "* Invalid credit card number"
                },
				"phone":{
					// credit:jquery.h5validate.js / orefalo
					"regex":/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/,
					"alertText":"* Invalid phone number,the sample 010-6754783"
				},
				"faxNbr":{
					// credit:jquery.h5validate.js / orefalo
					"regex":/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/,
					"alertText":"* Fax number is invalid"
				},
				"movePhone": {
                	// credit: jquery.h5validate.js / orefalo
                	"regex": /^1[34578]\d{9}$/,
                	"alertText": "* Invalid cell phone number"
                },
                "usersName": {
                	// credit: jquery.h5validate.js / orefalo
                	"regex": /^1[34578]\d{9}$/,
                	"alertText": "* Please enter your cell phone number"
                },
				"email":{
					"regex":/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g,
					"alertText":"* Invalid email address"
				},
				"integer":{
					"regex":/^[\-\+]?\d+$/,
					"alertText":"* Invalid integer"
				},
				"number":{
					// Number, including positive, negative, and floating decimal. credit:orefalo
                    "regex": /^[\-\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?([\.]([0-9]+))?$/,
					"alertText":"* Invalid values"
				},
				"money": {
                	"regex": /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/,
                	"alertText": "* Invalid amount, 8-bit integer, decimal tow, example: 247.23"
                },
                "score": {
                	"regex": /^([1-9][\d]{0,2}|0)(\.[\d]{1,2})?$/,
                	"alertText": "* Invalid fraction, integer 3 digits, decimal 2 digits"
                },
                "unitmoney": {
                	"regex": /^([1-9][\d]{0,5}|0)(\.[\d]{1,2})?$/,
                	"alertText": "* Invalid amount, 6-bit integer, decimal tow, example: 247.23"
                },
                "fullmoney": {
                	"regex": /^([1-9][\d]{0,11}|0)(\.[\d]{1,2})?$/,
                	"alertText": "* Invalid amount, 12-bit integer, decimal tow, example: 247.23"
                },
                "bankNum": {
                	"regex": /^\d{16}$|^\d{18,21}$/,
                	"alertText": "* Invalid bank card number, integer 16 or 18-21 bit"
                },
				"date":{
					"regex":/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/,
					"alertText":"* Invalid date format required for YYYY - MM - DD"
				},
				"ipv4":{
					"regex":/^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,
					"alertText":"* Invalid IP address"
				},
				"url":{
					"regex":/((http|ftp|https):\/\/)?[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:\/~\+#]*[\w\-\@?^=%&amp;\/~\+#])?/,
					"alertText":"* Invalid url"
				},
				"onlyNumberSp":{
					"regex":/^[0-9\ ]+$/,
					"alertText":"* Only fill in the Numbers"
				},
				"onlyLetterSp":{
					"regex":/^[a-zA-Z\ \']+$/,
					"alertText":"* Only fill in English letters"
				},
				"onlyLetterNumber":{
					"regex":/^[0-9a-zA-Z_]+$/,
					"alertText":"* Can only fill in numbers, English letters, underline"
				},
				"exceptChinese":{
					"regex":/^[^\u4e00-\u9fa5]{0,}$/,
					"alertText":"* Can not input chinese"
				},
				//tls warning:homegrown not fielded 
				"dateFormat":{
					"regex":/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(?:(?:0?[1-9]|1[0-2])(\/|-)(?:0?[1-9]|1\d|2[0-8]))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(0?2(\/|-)29)(\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\d\d)?(?:0[48]|[2468][048]|[13579][26]))$/,
					"alertText":"* Invalid date format"
				},
				//tls warning:homegrown not fielded 
				"dateTimeFormat":{
					"regex":/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1}$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^((1[012]|0?[1-9]){1}\/(0?[1-9]|[12][0-9]|3[01]){1}\/\d{2,4}\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1})$/,
					"alertText":"* Invalid date or time format",
					"alertText2":"The acceptable format: ",
					"alertText3":"mm/dd/yyyy hh:mm:ss AM|PM or ", 
					"alertText4":"yyyy-mm-dd hh:mm:ss AM|PM"
				},
				
				/**
				 * 正则验证规则补充
				 * Author: ciaoca@gmail.com
				 * Date: 2013-10-12
				 */
				"chinese":{
					"regex":/^[\u4E00-\u9FA5]+$/,
					"alertText":"* Only fill in Chinese characters"
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
					"alertText":"* Invalid id number"
				},
				"chinaIdLoose":{
					"regex":/^(\d{18}|\d{15}|\d{17}[xX])$/,
					"alertText":"* Invalid id number"
				},
				"chinaZip":{
					"regex":/^\d{6}$/,
					"alertText":"* Invalid zip code"
				},
				"qq":{
					"regex":/^[1-9]\d{4,10}$/,
					"alertText":"* Invalid QQ number"
				},
				"password": {
                	"regex": /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,20}$/,
                	"alertText": "*Invalid password"
                },
                "phone-left":{
					// credit:jquery.h5validate.js / orefalo
					"regex":/^[0\+]\d{2,3}$/,
					"alertText":"* Invalid phone number"
				},
				"phone-middle":{
					// credit:jquery.h5validate.js / orefalo
					"regex":/^(\d{7,8})$/,
					"alertText":"* Invalid phone number"
				},
				"phone-right":{
					// credit:jquery.h5validate.js / orefalo
					"regex":/^\d{3,5}$/,
					"alertText":"* Invalid phone number"
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