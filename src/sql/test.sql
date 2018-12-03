#namespace("remind")
	#sql("getRemindDetails")
	    SELECT * FROM  ds_remind where 1=1
	    #if(username)
            and username = #para(username)
        #end
        #if(nickname)
            and nickname like #para(nickname)
        #end
	    order by addTime desc
	#end
#end
