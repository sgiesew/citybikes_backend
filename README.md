
CITYBIKES BACKEND


Setup
=====

The backend is already deployed and running in the cloud at https://citybikes-backend.herokuapp.com, so no more setup steps are needed.


Features
========

The backend provides the endpoints documented in APIdoc.pdf (in this directory)

Tests
=====

The class CitybikesBackendApplicationTests.java contains these tests:
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

* Java Spring Boot
* Jpa Repository
