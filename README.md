# RHD-JAPAN - Full Stack Java Developer Assignment By Vinod Jagwani, Sept 2021



## Overview of all microservice

There are 4 backend services 

#### 1. rhd-order-service

The order service will take care of order when check out process done. so all orders will manage by this service.


#### 2. rhd-product-service

The Product service will responsible for creating items, search and filter items along with creating a media of item

#### 3. rhd-shopping-cart-service

The shopping cart service will take care of cart and checkout process, it will add items into cart and create a checkout 
and then order will be post to order service via rabbit mq.

#### 4. user-auth-service

The user service is responsible for creating user and and generating token and the token will be used for other service to 
access the apis.

#### 5. rhd-shopping-fe

This is FE application on angular 8 which is has few pages for product display and showing in items in cart


Note: all service have swagger api documentation available.


## Architecture

1. All apis are created in springboot mvc and spring webflux, they are designed in microservice architecture way
so that each service can be deployed independently

2. user-service is developed in spring mvc based approach and other services are developed in webflux reactive 
module, this is intentionally designed like this to demonstrate the variety of springboot features.

3. MongoDb is used for storing item images and users.

3. RabbitMq is used for posting orders when checkout happen.

4. Spring repository and queryDSL is used for saving and retrieving data 

5. R2DBC reactive driver in webflux is used for quering mysql.

6. Spring security is being used for validating token in each microservice.

7. All Models and DB columns are based on assumption 

8. JSR bean validation is there for validating json fields in the request.

9. Mockito is used for unit test and spring slice web layer annotation is used for testing the classes.

10. Zalando logbook is used for logging request and response.

11. Every service has application.yml file for configurations.

12. Docker plugin jib and spotify is used for deploying service locally or docker registry

13. Angular js 8 is used for FE application to show the product list and showing product in the cart.


### Improvements:

All same code must be put into separate jar as dependency


















