drop table if exists menu_food;
drop table if exists menu;
drop table if exists food;
drop table if exists restaurant;
drop table if exists address;


CREATE TABLE address
(
   ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
   CITY varchar(255),
   COUNTRY varchar(255),
   STREET varchar(255),
   ZIPCODE varchar(255)
);


CREATE TABLE restaurant
(
   ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
   NAME varchar(255) UNIQUE,
   ADDRESS_ID int
);
ALTER TABLE restaurant ADD CONSTRAINT FK_RESTAURANT_ADDRESS_ID FOREIGN KEY (ADDRESS_ID) REFERENCES address(ID);

CREATE TABLE menu
(
   ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
   Restaurant_ID int NOT NULL,
   FROMDATE date,
   TODATE date
);

ALTER TABLE menu ADD CONSTRAINT FK_MENU_Restaurant_ID FOREIGN KEY (Restaurant_ID) REFERENCES restaurant(ID);


CREATE TABLE food
(
   ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
   CALORIES int,
   ISVEGAN bit DEFAULT 0 NOT NULL,
   NAME varchar(255) UNIQUE,
   PRICE int
);

CREATE TABLE menu_food
(
   Menu_ID int NOT NULL,
   food_ID int NOT NULL,
   CONSTRAINT PRIMARY KEY (Menu_ID,food_ID)
);

ALTER TABLE menu_food ADD CONSTRAINT FK_MENU_FOOD_food_ID FOREIGN KEY (food_ID) REFERENCES food(ID);
ALTER TABLE menu_food ADD CONSTRAINT FK_MENU_FOOD_Menu_ID FOREIGN KEY (Menu_ID) REFERENCES menu(ID);