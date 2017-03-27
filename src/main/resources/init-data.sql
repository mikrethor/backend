SET SCHEMA ${dataBaseSchema};
SET INITIAL SCHEMA ${dataBaseSchema};

delete from CHILD;
delete from PARENT;
delete from EDUCATOR;
delete from ADMIN;
delete from DAY_SUMUP;
delete from SUPER_ADMIN;
delete from USER;
delete from DAYCARE;

insert into DAYCARE("ID", "NAME") values (1, 'Ma garderie');
insert into ADMIN("ID") values (1);
insert into PARENT("ID","DAYCARE_ID","FIRSTNAME", "LASTNAME") values (1,1,'Xavier','B');
insert into PARENT("ID","DAYCARE_ID","FIRSTNAME", "LASTNAME") values (2,1,'Bérengère','B');

insert into EDUCATOR("ID","DAYCARE_ID","FIRSTNAME", "LASTNAME") values (1,1,'Marie-Josée', 'YMCA');
insert into EDUCATOR("ID","DAYCARE_ID","FIRSTNAME", "LASTNAME") values (2,1,'Bérengère', 'CB');
insert into EDUCATOR("ID","DAYCARE_ID","FIRSTNAME", "LASTNAME") values (3,1,'Joe', 'Tribiani');

insert into USER("ID", "LOGIN", "PASSWORD","SALT", "DAYCARE_ID","ADMIN_ID","EDUCATOR_ID","PARENT_ID") values (3, 'test@admin','test','salt',1,1,NULL,NULL);
insert into USER("ID", "LOGIN", "PASSWORD","SALT", "DAYCARE_ID","ADMIN_ID","EDUCATOR_ID","PARENT_ID") values (2, 'test@educator','test','salt',1,NULL,1,NULL);
insert into USER("ID", "LOGIN", "PASSWORD","SALT", "DAYCARE_ID","ADMIN_ID","EDUCATOR_ID","PARENT_ID") values (1, 'test@parent','test','salt',1,NULL,NULL,1);

insert into CHILD("ID", "FIRSTNAME", "LASTNAME", "DAYCARE_ID") values (1, 'Arthur','B',1);
insert into CHILD("ID", "FIRSTNAME", "LASTNAME", "DAYCARE_ID") values (2, 'Louis','B',1);

--Child 1
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (1, 1,'comment 1','BAD','MEDIUM','GOOD',CURRENT_DATE);
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (2, 1,'comment 2','GOOD','MEDIUM','BAD',  TO_TIMESTAMP ( '25/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (3, 1,'comment 3','MEDIUM','MEDIUM','MEDIUM', TO_TIMESTAMP ( '24/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (4, 1,'comment 4','BAD','BAD','BAD',TO_TIMESTAMP ( '23/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (5, 1,'comment 5','GOOD','GOOD','GOOD',TO_TIMESTAMP ( '22/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (6, 1,'comment 6','MEDIUM','BAD','GOOD',TO_TIMESTAMP ( '21/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (7, 1,'comment 7','BAD','MEDIUM','GOOD',TO_TIMESTAMP ( '20/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (8, 1,'comment 7','GOOD','BAD','GOOD',TO_TIMESTAMP ( '19/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (9, 1,'comment 9','BAD','GOOD','GOOD',TO_TIMESTAMP ( '18/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (10, 1,'comment 10','BAD','GOOD','GOOD',TO_TIMESTAMP ( '17/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
--Child 2
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (11, 2,'comment 1','BAD','MEDIUM','GOOD',CURRENT_DATE);
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (12, 2,'comment 2','GOOD','MEDIUM','GOOD',TO_TIMESTAMP ( '25/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (13, 2,'comment 3','GOOD','MEDIUM','GOOD',TO_TIMESTAMP ( '24/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (14, 2,'comment 4','BAD','BAD','GOOD',TO_TIMESTAMP ( '23/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (15, 2,'comment 5','BAD','MEDIUM','BAD',TO_TIMESTAMP ( '22/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (16, 2,'comment 6','BAD','MEDIUM','GOOD',TO_TIMESTAMP ( '21/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (17, 2,'comment 7','BAD','GOOD','BAD',TO_TIMESTAMP ( '20/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (18, 2,'comment 8','BAD','MEDIUM','GOOD',TO_TIMESTAMP ( '19/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (19, 2,'comment 9','GOOD','MEDIUM','GOOD',TO_TIMESTAMP ( '18/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));
insert into DAY_SUMUP("ID", "ID_CHILD", "COMMENT", "MOOD", "APPETITE", "SLEEP", "DAY") values (20, 2,'comment 10','BAD','MEDIUM','GOOD',TO_TIMESTAMP ( '17/03/2017 00:00:00', 'DD/MM/YYYY HH:MI:SS' ));


ALTER SEQUENCE SEQ_ID_USER RESTART WITH 4;
ALTER SEQUENCE SEQ_ID_DAYCARE RESTART WITH 2;
ALTER SEQUENCE SEQ_ID_ADMIN RESTART WITH 2;
ALTER SEQUENCE SEQ_ID_PARENT RESTART WITH 3;
ALTER SEQUENCE SEQ_ID_EDUCATOR RESTART WITH 4;
ALTER SEQUENCE SEQ_ID_CHILD RESTART WITH 3;
ALTER SEQUENCE SEQ_ID_DAY_SUMUP RESTART WITH 21;





 


