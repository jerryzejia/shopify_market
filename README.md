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

## Interaction
| HTTP Verb | API Name           | URL Path                        | Parameter                                                                                                             | Description                                                     |
|-----------|--------------------|---------------------------------|-----------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------|
| GET       | getProduct         | /product/{id}                   | (long) id: product id                                                                                                        | Get Product with this specific id                               |
| GET       | getAllProduct      | /product?hasInventory           | boolean hasInventory: <br> true = return list of non-zero inventory product <br> false = return all product                               | Get a list of product depending on the parameter passed         |
| GET       | getShoppingCart    | /ShoppingCart/{id}              | (long) id: Shopping Cart id                                                                                                  | Get Shopping Cart with this id                                  |
| POST      | addProduct         | /product                        | @RequestBody (JSON) Product product：the new product to be added inside                                | Add a new product with the specified id, name, price, inventory |
| POST      | addShoppingCart    | /ShoppingCart                   | @RequestBody (JSON) ShoppingCart shoppingCart : the new shopping cart                                  | Add a shopping cart with the specified ID.                      |
| POST      | purchaseProduct    | /purchase/{shoppingCartId}/{id} | (long) shoppingCartId : id of the shopping cart that is going to contain the product  <br>  (long)id : id of Product to be purchased | Add a product inside a shopping cart                            |
| Delete    | deleteProduct      | /product/{id}                   | (long) id: product id                                                                                                        | Delete product with this id                                     |
| Delete    | deleteShoppingCart | /ShoppingCart/{id}              | (long) id: Shopping Cart id                                                                                                  | Delete shoping cart with this id                                |


This API connects to http://localhost:8080. 
Initializing a product would send a Get request with a body of:
~~~~
{
	"id": 1,
	"name":"java",
	"inventory":1000,
	"price" : 80
}
~~~~
Initializing a shopping cart would send a Get request with a body of:
~~~~
{
	"id": 1,
}
~~~~



