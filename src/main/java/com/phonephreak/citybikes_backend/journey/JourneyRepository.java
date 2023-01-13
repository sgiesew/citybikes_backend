package com.phonephreak.citybikes_backend.journey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Integer> {

        long countByReturnStationCode(String stationId);
        long countByDepartureStationCode(String stationId);

        @Query("SELECT AVG(j.distance) FROM Journey j WHERE j.departureStationCode = ?1")
        float getAverageJourneyDistanceFromStation(String stationId);
        @Query("SELECT AVG(j.distance) FROM Journey j WHERE j.returnStationCode = ?1")
        float getAverageJourneyDistanceToStation(String stationId);

        @Query("SELECT j.returnStationName FROM Journey j WHERE j.departureStationCode = ?1 GROUP BY j.returnStationName ORDER BY COUNT(*) DESC LIMIT 5")
        List<String> returnedToFromStationRanked(String stationId);
        @Query("SELECT j.departureStationName FROM Journey j WHERE j.returnStationCode = ?1 GROUP BY j.departureStationName ORDER BY COUNT(*) DESC LIMIT 5")
        List<String> departedFromToStationRanked(String stationId);
        
        @Query("SELECT j.departureDate, COUNT(*) FROM Journey j WHERE j.departureStationCode = ?1 GROUP BY j.departureDate")
        List<Object[]> dailyDeparturesFromStation(String stationId);
        @Query("SELECT j.returnDate, COUNT(*) FROM Journey j WHERE j.returnStationCode = ?1 GROUP BY j.returnDate")
        List<Object[]> dailyReturnsToStation(String stationId);

}
