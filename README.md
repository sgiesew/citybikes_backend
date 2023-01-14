
CITYBIKES BACKEND


Setup
=====

The backend is already deployed and running in the cloud at https://citybikes-backend.herokuapp.com, so no more setup steps are needed.


Features
========

The backend provides the endpoints documented in APIdoc.pdf (in this directory)

Tests
=====

The class CitybikesBackendApplicationTests.java contains these tests for the endpoints:
- whenGetExistingStation_thenReturns200
- whenGetNotExistingStation_thenReturns404
- whenStationsPageParametersMissing_thenReturns400
- whenJourneysPageParametersMissing_thenReturns400
- whenStationAdded_thenReturns201
- whenStationAddedWithEmptyParameters_thenReturns400
- whenDeleteNotExistingStation_thenReturns404

To run the tests, click the "Actions" tab. In the left pane, click "CI/CD pipeline". In the main pane, click "Run workflow". 

Technologies
============

* Java Spring Boot - Spring Boot provides a flexible way to configure Java Beans, XML configurations, and Database Transactions. It provides a powerful batch processing and manages REST endpoints.
* Spring Data JPA - JPA handles most of the complexity of JDBC-based database access and object-relational mappings. On top of that, Spring Data JPA reduces the amount of boilerplate code required by JPA. That makes the implementation of your persistence layer easier and faster.
