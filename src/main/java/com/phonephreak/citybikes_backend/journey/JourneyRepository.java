package com.phonephreak.citybikes_backend.journey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Integer> {

        long countByReturnStationId(Integer stationId);
        long countByDepartureStationId(Integer stationId);

        @Query("SELECT AVG(j.distance) FROM Journey j WHERE j.departureStationId = ?1")
        float getAverageJourneyDistanceFromStation(Integer stationId);
        @Query("SELECT AVG(j.distance) FROM Journey j WHERE j.returnStationId = ?1")
        float getAverageJourneyDistanceToStation(Integer stationId);

        @Query("SELECT j.returnStationName FROM Journey j WHERE j.departureStationId = ?1 GROUP BY j.returnStationName ORDER BY COUNT(*) DESC LIMIT 5")
        List<String> returnedToFromStationRanked(Integer stationId);
        @Query("SELECT j.departureStationName FROM Journey j WHERE j.returnStationId = ?1 GROUP BY j.departureStationName ORDER BY COUNT(*) DESC LIMIT 5")
        List<String> departedFromToStationRanked(Integer stationId);
        
        @Query("SELECT new com.phonephreak.citybikes_backend.journey.DailyCountsResult(j.departureDate, COUNT(*)) FROM Journey j WHERE j.departureStationId = ?1 GROUP BY j.departureDate ORDER BY j.departureDate")
        List<DailyCountsResult> dailyDeparturesFromStation(Integer stationId);
        @Query("SELECT new com.phonephreak.citybikes_backend.journey.DailyCountsResult(j.returnDate, COUNT(*)) FROM Journey j WHERE j.returnStationId = ?1 GROUP BY j.returnDate ORDER BY j.returnDate")
        List<DailyCountsResult> dailyReturnsToStation(Integer stationId);

}
