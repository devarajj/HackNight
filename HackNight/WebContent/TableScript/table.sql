drop table comments;
drop table orders;
drop table products;
drop table users;

/** user table created **/
CREATE TABLE IF NOT EXISTS users(
       username VARCHAR(40) UNIQUE,
       password VARCHAR(255),
       email VARCHAR(255),
       mobno VARCHAR(10),
       status VARCHAR(10),
       PRIMARY KEY (email)
 
    );


/** user table values inserted **/
insert into users(username,password,email,mobno,status) values('dev','dev123','devarajc@guidanz.com','8056807346','A'); 
insert into users(username,password,email,mobno,status) values('test','test123','test@gmail.com','8056807344','A'); 
insert into users(username,password,email,mobno,status) values('devaraj','dev123','devaraj@guidanz.com','8056807343','I'); 

/** products table created **/
CREATE TABLE IF NOT EXISTS products(
       product_id INT AUTO_INCREMENT PRIMARY KEY,
       product_name VARCHAR(255) ,
       image_url VARCHAR(255) ,
       color VARCHAR(255),
       amount INT(10),
       discount int(3),
       type VARCHAR(255),
       ratings INT(10),
       message LONGTEXT 
    );

ALTER TABLE products AUTO_INCREMENT = 1000;


/** products table created **/
insert into products(product_name,image_url,amount,discount,type,ratings,message,color) values('nokia 9090','nokia.gif','10000','10','mobile','13','abcdefghijklmn','yellow');
insert into products(product_name,image_url,amount,discount,type,ratings,message,color) values('nokia lumia','nokia_lumia.gif','8000','4','smart phone','23','dlfshjldjhfldskflsjd','blue');
insert into products(product_name,image_url,amount,discount,type,ratings,message,color) values('google nexus','nexus.gif','23000','20','smart phone','300','djsflkjldjf;skdjfllksjdjfjslkdjflksdj','red');



/** orders table created **/
CREATE TABLE IF NOT EXISTS orders(
       order_id INT AUTO_INCREMENT PRIMARY KEY,
       name   VARCHAR(255),
       email VARCHAR(255),
       mobno VARCHAR(20),
       order_item  VARCHAR(255),
       amount INT(10),
       address MEDIUMTEXT,
       FOREIGN KEY (email) REFERENCES users(email)
    );


/** orders table created **/
insert into orders(name,email,mobno,order_item,amount,address) values('deva','test@gmail.com','8056808480','nokia','100','chennai rugoieigpoeipigioperigpoip');
insert into orders(name,email,mobno,order_item,amount,address) values('deva','test@gmail.com','8056808480','nokia','100','chennai rugoieigpoeipigioperigpoip');


/** comments table  **/
CREATE TABLE IF NOT EXISTS comments(
       product_id INT, 
       name   VARCHAR(255),
       email VARCHAR(255),
       message MEDIUMTEXT,
       FOREIGN KEY (email) REFERENCES users(email),
       FOREIGN KEY (product_id) REFERENCES products(product_id)
    );

insert into comments(product_id,name,email,message) values('1000','nice product','test@gmail.com','worth to buy it');
insert into comments(product_id,name,email,message) values('1000','good','test@gmail.com','cheap and best');
insert into comments(product_id,name,email,message) values('1001','color','test@gmail.com','color is nice');
insert into comments(product_id,name,email,message) values('1002','color','test@gmail.com','color is nice');


select * from users;
select * from products;
select * from orders;
select * from comments;





