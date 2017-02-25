# RestCloudTest
A project which use Spring Cloud (Netflix Eureka for registartion and Ribbon client-side load balancer) with Spring Data Rest to provide a simple cloud based service

This project has two layers: accessing the Spring Data REST microservice by a load balanced rest template (you'll need to run the registration app -> AccountServer microservice -> AccountMicroService general webserver). Then you can access the general webserver's url to do some things with the SDR microservice.

The second layer is using a Zuul powered gateway (also brings to the table both Ribbon load balancing and Hybrix circuit breaker because they're in the classpath). To use this one, run Registration app -> AccountServer Microservice -> gateway app. Then you can navigate fully through the SDR microservice by pointing to the gateway url + microservice endpoint. e.g: http://localhost:2222/accounts is the endpoint for the microservice, but since we're using Zuul proxy, now it can intercept the endpoint and use its own url to access the api (by forwarding), so http://localhost:4444/accounts (which is gateway's url + microservice endpoint) can access the microservice.
