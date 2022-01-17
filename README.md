# InventoryManagement
Spring Boot Applicaton for building REST API with functionalities to Create/Read/Update/Delete Products from Inventory. Users can also download the list of products and their details in a CSV format.

Requirements:
Java 8

How to run the project:

Clone the repository<br>
Import the project into IDE of your choice(IntelliJ/Eclipse..)<br>
Run the command: gradle clean build<br>

This will download all the dependencies needed for the project.<br>

Run InventoryManagementApplication.java as application<br>
This will start the tomcat server on http://localhost:8080<br>
For this project, I am using h2 database which will persist the on a file under data/ folder.<br>

API Endpoints:

1) GET /products : Retrieves all the products from the database
2) POST /product : Add a new product to the database
3) PUT /product/{productId} : Modify details of an existing product using productId
4) DELETE /product/{productId} : Delete a product from database using productId
5) GET /download : Returns a CSV file that contains all the product information.

You may try the above endpoints using Postman. <br>
You could also hit the /download API on browser to download the csv file.<br>
