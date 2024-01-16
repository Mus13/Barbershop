insert into client_model (ID,FIRST_NAME,LAST_NAME,PHONE_NUMBER) values (10001,'Raouf','Lhaj','+48 987 854 256');
insert into client_model (ID,FIRST_NAME,LAST_NAME,PHONE_NUMBER) values (10002,'Anouar','Khlifa','+48 325 652 963');
insert into client_model (ID,FIRST_NAME,LAST_NAME,PHONE_NUMBER) values (10003,'Mahdi','Issaoui','+48 256 321 521');
insert into barber_model (ID,FIRST_NAME,LAST_NAME,DESCRIPTION,FULL_DESCRIPTION) values (10004,'Ayoub','Kroudi','Barber from Marrakech','jkvsnd sdknf eozife fzoie oijge ofijd fpezfjzjf ezfoizen dsfoids fzejoif dsofjerk gofid fzoeijf fosid fzoief fzoefj fpsod pgodf fzeoifn fizojef pofdçjpzoe fposfjez.');
insert into barber_model (ID,FIRST_NAME,LAST_NAME,DESCRIPTION,FULL_DESCRIPTION) values (10005,'Ibrahim','Taha','Barber from AUE','jkvsnd sdknf eozife fzoie oijge ofijd fpezfjzjf ezfoizen dsfoids fzejoif dsofjerk gofid fzoeijf fosid fzoief fzoefj fpsod pgodf fzeoifn fizojef pofdçjpzoe fposfjez.');
insert into barber_model (ID,FIRST_NAME,LAST_NAME,DESCRIPTION,FULL_DESCRIPTION) values (10006,'Mustapha','Belabed','Manager from Algeria','lkvnf fdkv zrfoj grok msdk fdopk poijez fkjsdn fzeijf gteo zefj dsmke fjkzen dklsn.');

insert into product_model(ID,NAME,DESCRIPTION,INSTRUCTIONS)    values (30001,'Sea Salt Spray','Sea Salt Spray nurtures the hair with the vitamins and proteins inside the spray bottle.','Spray on wet hair and let for few minutes for the hair to curl up.');
insert into product_model(ID,NAME,DESCRIPTION,INSTRUCTIONS)    values (30002,'Beard Oil','The beard oil nurtures the beard and give it a shine and soft touch.','Put small amount in hands and gently rub the beard for a minutes.');

insert into service_model(ID,NAME,DESCRIPTION,PRICE)           values (40001,'HairCut','The hair cut includes washing the hair and giving suggestions for hairstyles that could properly fit specifically each client.','50 pln');
insert into service_model(ID,NAME,DESCRIPTION,PRICE)           values (40002,'Beard','The beard is faded and trimmed based on the clients request.','30 pln');

insert into APPOINTMENT_MODEL(ID,DATE_appointment,TIME_START,TIME_END,BARBER_ID,CLIENT_ID,SERVICE_ID) values (50001,'2023-10-31','15:00:00','16:00:00',10004,10001,40001);
insert into APPOINTMENT_MODEL(ID,DATE_appointment,TIME_START,TIME_END,BARBER_ID,CLIENT_ID,SERVICE_ID) values (50002,'2023-11-01','12:00:00','12:30:00',10004,10002,40002);
insert into APPOINTMENT_MODEL(ID,DATE_appointment,TIME_START,TIME_END,BARBER_ID,CLIENT_ID,SERVICE_ID) values (50003,'2023-11-02','14:30:00','15:30:00',10005,10003,40001);

insert into REVIEW_MODEL(ID,RATING,DESCRIPTION,APPOINTMENT_MODEL_ID)  values (60001,5,'Greate hairCut!',50001);
insert into REVIEW_MODEL(ID,RATING,DESCRIPTION,APPOINTMENT_MODEL_ID)  values (60002,4,'I recommend!',50003);