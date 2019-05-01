/***
 * 备忘提醒功能的sql
 */
#namespace("remind")
#include("com/DS/remind/dao/remind.sql")
#end

/****
 * 调度器的sql
 */
#namespace("qrtz")
#include("com/DS/quartz/dao/qrtz.sql")
#end


/****
 * 用户信息的sql
 */
#namespace("user")
#include("com/DS/user/dao/user.sql")
#end




/****
 * task
 */
#namespace("task")
#include("com/DS/task/dao/task.sql")
#end

/****
 * projectTree
 */
#namespace("projectTree")
#include("com/DS/task/dao/projectTree.sql")
#end



/****
 * 菜单信息的sql
 */
#namespace("menu")
#include("com/DS/menu/dao/menu.sql")
#end

/****
 *
 */
#namespace("notification")
#include("com/DS/notification/dao/notification.sql")
#end


/****
 * excel
 */
#namespace("excel")
#include("com/DS/excel/dao/excel.sql")
#end