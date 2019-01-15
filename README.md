# shopify_internship

## Dependencies
* GSON (included inside the Dependency folder)
~~~~
sudo apt-get update
sudo apt-get install libgoogle-gson-java
~~~~
* MYSQL-connector-java
~~~~
sudo apt-get install mysql-server
sudo apt-get install mysql-client
sudo apt-get install libmysql-java
~~~~

## Create a database
~~~~ 
sudo mysql --password
mysql> create database product_rep; -- Create the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on product_rep.* to 'springuser'@'%'; -- Gives all the privileges to the new user on the newly created database
 ~~~~
SQL script for the database is included inside the DB folder. 

## Thought Process and Design Decision 
### General
- Storage of Data need to be persistent.
- REST API provides better support because of JSON and is faster compared to SOAP. 
### Product 
- Need to include id, name, inventory, and price as specified by the google doc.
- JPA is very useful in this case. 
### Shopping Cart
- Need to include id, current_item, current_price.
- Use a hashmap to manage current_item for simplicity, and convert it to JSON String for storage.
- GSON is very helpful in this regard. 

## Implementation of Product 





## Implementation of Shopping Cart



## Interaction

