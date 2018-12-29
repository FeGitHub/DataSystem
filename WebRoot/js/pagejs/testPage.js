
$(function(){
	var source = $("#complainListData").html();
	var template = Handlebars.compile(source);
	 var context = {
             "student": [
                   {
                       "title": "gsgf",
                       "body": "fff"
                  },
                   {
                	  "title": "gsgf",
                      "body": "fff"
                  },
                   {
                	  "title": "gsgf",
                      "body": "fff"
                   }
               ]
          };
	var _html = template(context);
	$("#testId").html(_html);
});



