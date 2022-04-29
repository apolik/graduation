# Voting Application

#### Application for deciding where to have lunch. The task was:

> Build a voting system for deciding where to have lunch.
>
> * 2 types of users: admin and regular users
> * Admin can input a restaurant, and it's lunch menu of the day (2-5 items usually, just a dish name and price)
> * Menu changes each day (admins do the updates)
> * Users can vote on which restaurant they want to have lunch at
> * Only one vote counted per user
> * If user votes again the same day:
> * If it is before 11:00 we assume that he changed his mind.
> * If it is after 11:00 then it is too late, vote can't be changed
> * Each restaurant provides a new menu each day.

## Usage
- Clone this repository to your machine 
- Open up terminal and navigate to the root directory 
- Make sure you've maven and java 17 installed
- Run 'mvn spring-boot:run'
## 🐬 Docker
- docker-compose up 
## Swagger UI documentation
> http://localhost:8080/swagger-ui.html
## Credentials
```
User:  ye@gmail.com / kanye
Admin: admin@gmail.com / admin
```
## Curl commands
#### 1. Get all Restaurants
`curl -s http://localhost:8080/api/restaurants --user ye@gmail.com:kanye`
#### 2. Get Restaurant with id 3
`curl -s http://localhost:8080/api/restaurants/3 --user ye@gmail.com:kanye`
#### 3. Get Restaurant not found
`curl -s http://localhost:8080/api/restaurants/1017 --user ye@gmail.com:kanye`
#### 4. Create Restaurant 
`curl -s -X POST -d '{"name": "Anderson"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/admin/restaurants --user admin@gmail.com:admin`
#### 5. Delete Restaurant with id 2
`curl -s -X DELETE http://localhost:8080/api/admin/restaurants/2 --user admin@gmail.com:admin`
#### 6. Get all Dishes for today
`curl -s http://localhost:8080/api/dishes --user ye@gmail.com:kanye`
#### 7. Get all Dishes for today by restaurant 3
`curl -s http://localhost:8080/api/dishes/3 --user ye@gmail.com:kanye`
#### 8. Create Dish for Restaurant with id 3
`curl -s -X POST -d '{"name": "new dish","price": 1017.0,"restaurantId": 3}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/admin/dishes --user admin@gmail.com:admin`
#### 9. Delete Dish with id 6
`curl -s -X DELETE http://localhost:8080/api/admin/dishes/6 --user admin@gmail.com:admin`
#### 10. Create 3 Dishes for restaurant 3
`curl -s -X POST -d '[{"name": "Nuggets","price": 1099.0 }, {"name": "Pizza","price": 759.0 }, {"name": "Coca-cola","price": 509.0 }]' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/admin/dishes/3 --user admin@gmail.com:admin`
#### 11. Get all Votes for today
`curl -s http://localhost:8080/api/voting --user ye@gmail.com:kanye`
#### 12. Get all Votes for today by restaurant 2
`curl -s http://localhost:8080/api/admin/voting/2 --user admin@gmail.com:admin`
#### 13. Vote for Restaurant with id 4
`curl -s -X POST http://localhost:8080/api/voting/4 --user test@gmail.com:testuser`

## Application stack
> Spring Boot, Spring MVC, Spring Security, Spring Data JPA, Hibernate ORM, JUnit 5, AssertJ, Apache Tomcat, Hibernate Validator, 
> SLF4J, Json Jackson, Springdoc OpenAPI UI, Mockito, Java 17, Caffeine cache
