-- insert 1 address
INSERT INTO address (CITY,COUNTRY,STREET,ZIPCODE)VALUES('BUDAPEST','HUNGARY','FUTO UTCA','1085');

-- insert 1 restaurant to the address
INSERT INTO restaurant (NAME,ADDRESS_ID)VALUES('10 MINUTES',1);

-- insert 1 menu to the restaurant (menu --1) (valid from today until today + 1 month)
INSERT INTO menu (Restaurant_ID,FROMDATE, TODATE) VALUES (1, curdate(), curdate() + interval 1 month  -  interval 1 day );

-- insert 5 foods, add foods to the inserted menu
INSERT INTO food (CALORIES,ISVEGAN,NAME,PRICE) VALUES (100,0,'Fish soup',1000);
INSERT INTO food (CALORIES,ISVEGAN,NAME,PRICE) VALUES (200,1,'Margerita pizza',2000);
INSERT INTO food (CALORIES,ISVEGAN,NAME,PRICE) VALUES (300,0,'Cordon bleu',3000);
INSERT INTO food (CALORIES,ISVEGAN,NAME,PRICE) VALUES (400,0,'Tune steak',4000);
INSERT INTO food (CALORIES,ISVEGAN,NAME,PRICE) VALUES (500,0,'Tender lion',5000);

INSERT INTO sql_excercise.menu_food VALUES (1,1);
INSERT INTO sql_excercise.menu_food VALUES (1,2);
INSERT INTO sql_excercise.menu_food VALUES (1,3);
INSERT INTO sql_excercise.menu_food VALUES (1,4);
INSERT INTO sql_excercise.menu_food VALUES (1,5);

-- insert 1 more menu to the restaurant (menu --2)  (valid from today + 1 month + 1 day until today + 2 month)
INSERT INTO menu (Restaurant_ID, FROMDATE, TODATE) VALUES (1, curdate()  + interval 1 month,curdate()  + interval 2 month  -  interval 1 day  );

-- insert 2 foods, add all to foods in tha database to the inserted menu
INSERT INTO food (CALORIES,ISVEGAN,NAME,PRICE) VALUES (600,0,'Lamb chops',6000);
INSERT INTO food (CALORIES,ISVEGAN,NAME,PRICE) VALUES (700,0,'File mignon',7000);

INSERT INTO sql_excercise.menu_food VALUES (2,1);
INSERT INTO sql_excercise.menu_food VALUES (2,2);
INSERT INTO sql_excercise.menu_food VALUES (2,3);
INSERT INTO sql_excercise.menu_food VALUES (2,4);
INSERT INTO sql_excercise.menu_food VALUES (2,5);
INSERT INTO sql_excercise.menu_food VALUES (2,6);
INSERT INTO sql_excercise.menu_food VALUES (2,7);

