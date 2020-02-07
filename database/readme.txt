1.直接在navicat运行sql文件或在navicat的命令行上执行不成功
(1)navicat的命令行 不支持source等命令

2.如果要在控制台上执行的话，先切换到database下，再执行batch.sql
如：
(1)d:               -- 切换到d盘
(2)cd  D:\work\workspace\PAMS\database   -- 切换到database下,batch.sql的路径会以当前命令行的路径来查找文件
(3) mysql -u你的账号  -p你的密码 
(4)source  D:\work\workspace\PAMS\database\batch.sql 

注意：-- 后面要先留一个空格