-- 插入测试接口数据 
delete from interfaceservice where serviceid='serviceid';
insert into interfaceservice (serviceid,jsonstr) values('serviceid','{"param1":"paramstr1","param2":"paramstr2"}');
commit;