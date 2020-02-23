
delete from weburl where website='audiomack';
insert into weburl (website,url,remark) values ('audiomack','https://audiomack.com','歌曲资源网');
commit;

delete from weburl where website='zzzfun';
insert into weburl (website,url,remark) values ('zzzfun','http://www.zzzfun.com','看番资源网');
commit;


delete from weburl where website='processon';
insert into weburl (website,url,remark) values ('processon','https://www.processon.com/','在线画图');
commit;