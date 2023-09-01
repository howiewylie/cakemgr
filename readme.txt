Waracle cakemanager microservice implementation
------------------------------------------
developer: Arvid Haubold

This project has been built as a Spring Boot service running with Gradle, Java source compatibility 17.

To run microservice
-------------------

To run microservice in an IDE such as Intellij open a terminal window
to build the project: ./gradlew build
to run the tests: ./gradlew test

to run the service: ./gradlew bootRun --args='--spring.profiles.active=dev'
you can then access the service via a REST client such as postman on localhost:8080/cakes

Authorisation
--------------

The service in its current form for development implements Spring Security with basic Auth.
In postman select the Authorisation tab and select Basic Auth. Then enter the username and password
as provided in the application.yml file within the project.
Please note that you will need to run the 'dev' profile. Other profiles are to be added for
other environments.

API usage
=========

The service has 4 REST endpoints serving JSON to the client

Listing all cakes
GET /cakes

Adding a cake
POST /cakes
send JSON payload of new cake in the request body
Request body example:
{
        "title": "Some cake",
        "description": "brand new cake",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
}

Editing existing cake
PUT /cakes/1
send JSON payload of cake to edit in the request body
Request body example:
{
        "title": "Lemon cheesecake update",
        "description": "A cheesecake made of lemon and things",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
}

Removing cakes
DELETE /cakes/1

A postman collection JSON file can be found in test/resources. PLease import and run it
with the spring dev profile active.
