$(function(){ 
 
	
		$.fn.extend({ 
			checks_select: function(options){ 
			jq_checks_select = null; 
					$(this).click(function(e){ 
					jq_check = $(this); 
					//jq_check.attr("class", ""); 
							if (jq_checks_select == null) { 
							   jq_checks_select = $("<div class='checks_div_select'></div>").insertAfter(jq_check); 
								$.each(options, function(i, n){ 
								check_div=$("<div><input type='checkbox' value='" + n + "'>" + n + "</div>").appendTo(jq_checks_select); 
								check_box=check_div.children(); 
								check_box.click(function(e){ 
								//jq_check.attr("value",$(this).attr("value") ); 
								
								temp=""; 
									$("input:checked").each(function(i){ 
										if(i==0){ 
										temp=$(this).attr("value"); 
										}else{ 
										temp+=" "+$(this).attr("value"); 
										} 
										}); 
								jq_check.attr("value",temp); 
								e.stopPropagation(); 
								}); 
								}); 
							}else{ 
					jq_checks_select.toggle(); 
					
					} 
					e.stopPropagation(); 
					}); 
			$(document).click(function () { 
			jq_checks_select.hide(); 
			}); 
			//$(this).blur(function(){ 
			//jq_checks_select.css("visibility","hidden"); 
			//alert(); 
			//}); 
			} 
}) 

}); 