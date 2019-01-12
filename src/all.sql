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
 * ztree
 */
#namespace("ztree")
#include("com/DS/ztree/dao/ztree.sql")
#end


/****
 * task
 */
#namespace("task")
#include("com/DS/task/dao/task.sql")
#end

/****
 * demo
 */
#namespace("demo")
#include("com/DS/demo/dao/demo.sql")
#end

/****
 * demo
 */
#namespace("menu")
#include("com/DS/menu/dao/menu.sql")
#end