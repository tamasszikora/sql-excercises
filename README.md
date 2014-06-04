- fork this repository
- create SQL statements
	- create_database.sql: solution of chapter 2
	- populate_database.sql: solution of chapter 3.1
	- ohter_sqls.sql: solution of chapter 3.2-3.7
- commit and push the sql statements into the forked repository
- send the link of the forked repository

Tasks:
1. Create database name 'sql_excercise'
=====

2. Create tables in  database sql_training with the following data
=====

![alt tag](https://raw.githubusercontent.com/ipuppyp/sql-excercises/master/images/database.png)


Use
- primary keys with auto increment
- default values where it is justifiable
- foreign keys where it is needed
- use unique keys where it is justifiable

3. Write the following SQL-s
====
3.1 Populate database
----

- insert 1 address
- insert 1 restaurant to the address
- insert 1 menu to the restaurant (menu #1) (valid from today until today + 1 month - 1 day)
-> todate = curdate() + interval 1 month  -  interval 1 day

- insert 5 foods, add foods to the inserted menu
- insert 1 more menu to the restaurant (menu #2)  (valid from today + 1 month until today + 2 months - 1 day)
-> fromdate = curdate()  + interval 1 month
-> todate = curdate()  + interval 2 month  -  interval 1 day

-- insert 2 foods, add all to foods in the database to the inserted menu

3.2 Select
----
- select all foods (SELECT ...)
- select all vegan foods (SELECT ... WHERE ...)
- select all foods order by price (SELECT ... ORDER BY ...)
- select highest price food (SELECT MAX(...))
- select number of foods by menu (SELECT COUNT(1), MENU_ID FROM ... GROUP BY ...)
- select all restaurants WITH address (SELECT ... FROM ... JOIN ...)
- select foods available today (SELECT ... FROM ... JOIN ... WHERE ... curdate() BETWEEN FROMDATE AND TODATE)


3.3 Update
----
- update address data of restaurant
- update price of any food



3.4 Create indexes
----
- index foods by name to find them very quickly
 


3.5 Create view
----
- CREATE view the select all the foods of all the restaurants with address availabe today.

3.6 Delete
----
- remove menu #2
- remove all the data from the tables

3.7 Drop
----
- drop all the tables
