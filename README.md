
CITYBIKES BACKEND


Setup
=====

The backend is already deployed and running on the server at https://citybikes-backend.herokuapp.com, so no more setup steps are needed.


Features
========

The backend provides these endpoints:

* GET api/station

  Returns a list of all Station objects

* GET api/station/{stationId}

  Returns a custom object with details and statistics for station {stationId}

* GET api/journey/page/{pageNr}/len/{pageLen}

  Returns a list of {pageLen} Journey objects from all Journey objects, starting at {pageNr}


Technologies
============

* Java Spring Boot
* JpaRepository


TO DO
=====

* endpoint GET api/station returns list of custom objects with just station name and city
* make endpoint GET api/station/page/{pageNr}/len/{pageLen} for server-side pagination for list of stations
