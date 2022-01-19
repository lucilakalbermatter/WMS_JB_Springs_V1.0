# Spring Framework

## Topics covered

Inversion of Control, Dependency Injection, Beans, Annotation configuration, Spring Starter, REST API.

## Goal achieved

You'll start with the first step towards converting your command line query tool to a web-based warehouse management system using the Spring framework. By the end of this exercise you will have a basic spring boot project with some REST endpoints exposed for warehouse and user data that we'll later use to develop our web-based warehouse management system.
Specifically you'll have the following goals achieved :

- Spring boot application with standard project structure.
- well segregated controller, service and repository layers.
- Endpoints exposed for various warehouse and user related data.
- Testing these endpoints with postman.

## Preparation

Use [Spring Initializer](https://start.spring.io/) to initialize a new Spring boot application.

- Select Maven Project, Language- Java, latest stable Spring Boot version.
- Give Group, Artifact, Name , Description, package name to the project.
- Select Jar as packaging and LTE version of Java.
- Add Spring web dependency from here. Later we'll add dependencies as per requirement in the pom.xml file.
- Generate the zip file, extract it and import the project to your IDE.

## Data

The data required by the spring boot application are provided in json files inside the sample/data directory. Copy the `data` directory as it is into the root directory of your spring boot project(i.e where the src folder is present). You'll specifically be needing `stock.json` and `personnel.json` inside the data directory.


## Description

#### Import classes from our previous command line query tool project to our spring boot application
<br>
Create a new package `model` and copy our models inside this package: Admin, Employee, Guest, Item, Order, OrderItem, User.
<br><br>
Create a new package `repository` and copy our Repository classes inside this package : WarehouseRepository, UserRepository
<br><br>

Also you need to add the json-simple dependency to your `pom.xml` to resolve dependencies of the Repository classes.

```
		<dependency>
			<groupId>com.googlecode.json-simple</groupId>
			<artifactId>json-simple</artifactId>
			<version>1.1.1</version>
		</dependency>
```
Perform the needed refactoring to make the codes error-free.


#### Add the service layer and controller layer for Warehouse related functionalities

###### Service Layer
Create a new package `service` and create a new Service class WarehouseService inside this package. Annotate this class with @Service annotation as later we'll need to use this service class in our Controller.
<br><br>
You'll be delegating the functionalities from your WarehouseRespository class to this class as to have a separate service layer and later we follow the practice of writing business logics in the service layer only.
<br><br>
As the methods we'll be needing from the Repository class are already static, we'll not be needing dependency injection for the WarehouseRepository class into the service class. Otherwise if we want to use non-static methods from the Repository class, we would annotate the Repository class with @Repository annotation and then we would be able to inject the class as the dependency.
<br><br>
Create five new methods to `get warehouse`, `get all the items`, `get items by warehouse`, `get categories` and `get items by category` in this service class and simply call the appropriate static methods from the WarehouseRepository class.

###### Controller Layer

Create a new package `controller` and create a new Controller class WarehouseController inside this package. Annotate this class with @RestController as we'll be exposing rest endpoints through this class.
<br><br>
You'll need to add dependency injection for the WarehouseService class as we'll be using methods from this class to serve requests to our `REST` endpoints.
<br><br>
Specifically, create the following endpoints related to warehouse :

			- GET -> /warehouse/getWarehouses  -> returns Set<Integer>
			- GET -> /warehouse/getAllItems    -> returns List<Item>
			- GET -> /warehouse/getAllItems/{warehouseId} -> returns List<Item>
			- GET -> /warehouse/getCategories  -> returns Set<String>
			- GET -> /warehouse/getItemsByCategory/{category}  -> returns List<Item>


#### Add the service layer and controller layer for User related functionalities

###### Service Layer
Inside the  `service` package, create a new Service class UserService. Annotate this class with @Service annotation as later we'll need to use this service class in our Controller.
<br><br>
You'll be delegating the functionalities from your UserRespository class to this class.
<br><br>
As the methods we'll be needing from the Repository class are already static, we'll not be needing dependency injection for the UserRepository class into the service class.
<br><br>
Create four new methods to `get all the employees`, `get all the admins`, `if employee credential is correct` and `if admin credential is correct` in this service class and simply call the appropriate static methods from the UserRepository class.

###### Controller Layer

Create a new Controller class WarehouseController inside the `controller` package. Annotate this class with @RestController as we'll be exposing rest endpoints through this class.
<br><br>
You'll need to add dependency injection for the UserService class as we'll be using methods from this class to serve requests to our `REST` endpoints.
<br><br>
Specifically, create the following endpoints related to users :

			- GET -> /users/getAllEmployees  -> returns List<Employee>
			- GET -> /users/getAllAdmins    -> returns List<Admin>

Copy the LoginDTO model provided in the `sample/` directory inside the `model` package and use it as Request body for the following two endpoints :

			- GET -> /users/employee/login -> takes LoginDTO in RequestBody -> returns boolean
			- GET -> /users/admin/login  -> takes LoginDTO in RequestBody  -> returns boolean
			(return true if credentials match, otherwise false)



#### Test all the endpoints using Postman.

Start the spring boot application and Test all the endpoints created using postman. For your convenience you can import this [collection](https://www.getpostman.com/collections/a15466413f56f26b6628) into postman and test the endpoints.
