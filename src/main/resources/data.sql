insert into person_model (ID,FIRST_NAME,LAST_NAME,DESCRIPTION) values (10001,'Raouf','Lhaj','Client from Algiers');
insert into person_model (ID,FIRST_NAME,LAST_NAME,DESCRIPTION) values (10002,'Anouar','Khlifa','Client from Setif');
insert into person_model (ID,FIRST_NAME,LAST_NAME,DESCRIPTION) values (10003,'Mahdi','Issaoui','Client from El-Eulma');
insert into person_model (ID,FIRST_NAME,LAST_NAME,DESCRIPTION) values (10004,'Ayoub','Kroudi','Barber from Marrakech');
insert into person_model (ID,FIRST_NAME,LAST_NAME,DESCRIPTION) values (10005,'Ibrahim','Taha','Barber from AUE');
insert into person_model (ID,FIRST_NAME,LAST_NAME,DESCRIPTION) values (10006,'Mustapha','Belabed','Manager from Algeria');

insert into app_user     (ID,PERSON_ID,PASSWORD,ROLE,USERNAME) values (20001,10001,'1234','Client','raouf_alg');
insert into app_user     (ID,PERSON_ID,PASSWORD,ROLE,USERNAME) values (20002,10002,'1234','Client','anouar_19');
insert into app_user     (ID,PERSON_ID,PASSWORD,ROLE,USERNAME) values (20003,10003,'1234','Client','mahdi_eulma');
insert into app_user     (ID,PERSON_ID,PASSWORD,ROLE,USERNAME) values (20004,10004,'1234','Barber','Ayoub_mrk');
insert into app_user     (ID,PERSON_ID,PASSWORD,ROLE,USERNAME) values (20005,10005,'1234','Barber','Ibrahim_aue');
insert into app_user     (ID,PERSON_ID,PASSWORD,ROLE,USERNAME) values (20006,10006,'1234','Manager','Mustapha_tlm');

insert into product_model(ID,NAME,DESCRIPTION,INSTRUCTIONS)    values (30001,'Sea Salt Spray','Sea Salt Spray nurtures the hair with the vitamins and proteins inside the spray bottle.','Spray on wet hair and let for few minutes for the hair to curl up.');
insert into product_model(ID,NAME,DESCRIPTION,INSTRUCTIONS)    values (30002,'Beard Oil','The beard oil nurtures the beard and give it a shine and soft touch.','Put small amount in hands and gently rub the beard for a minutes.');

insert into service_model(ID,NAME,DESCRIPTION,PRICE)           values (40001,'HairCut','The hair cut includes washing the hair and giving suggestions for hairstyles that could properly fit specifically each client.','50 pln');
insert into service_model(ID,NAME,DESCRIPTION,PRICE)           values (40002,'Beard','The beard is faded and trimmed based on the clients request.','30 pln');