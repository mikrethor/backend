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

ALTER SEQUENCE SEQ_ID_USER RESTART WITH 4;
ALTER SEQUENCE SEQ_ID_DAYCARE RESTART WITH 2;
ALTER SEQUENCE SEQ_ID_ADMIN RESTART WITH 2;
ALTER SEQUENCE SEQ_ID_PARENT RESTART WITH 3;
ALTER SEQUENCE SEQ_ID_EDUCATOR RESTART WITH 4;
ALTER SEQUENCE SEQ_ID_CHILD RESTART WITH 3;





 


