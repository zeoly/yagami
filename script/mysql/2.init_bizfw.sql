-- root机构
INSERT INTO `bf_department` (`create_by`,`create_date`,`update_by`,`update_date`,`code`,`name`,`level`) VALUES ('admin',sysdate(),'admin',sysdate(),'root','root',0);
-- admin用户
INSERT INTO `bf_people` (`create_date`,`create_by`,`update_date`,`update_by`,`code`,`name`,`password`,`id_bf_department`,`status`,`error_count`) VALUES (sysdate(),'admin',sysdate(),'admin','admin','超级管理员','21218CCA77804D2BA1922C33E0151105',(SELECT id_bf_department FROM `bf_department` where code='root'),'1',0);
-- 超管角色
INSERT INTO `bf_role` (`create_by`,`create_date`,`update_by`,`update_date`,`name`,`description`) VALUES ('admin',sysdate(),'admin',sysdate(),'超级管理员','超级管理员');
-- 用户角色关系
INSERT INTO `bf_people_role_rel` (`create_by`,`create_date`,`update_by`,`update_date`,`id_bf_people`,`id_bf_role`) VALUES ('admin',sysdate(),'admin',sysdate(),(select t.id_bf_people from `bf_people` t where t.code='admin'),(select t.id_bf_role from bf_role t where t.name='超级管理员'));
