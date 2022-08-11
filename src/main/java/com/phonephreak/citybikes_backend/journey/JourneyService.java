package com.phonephreak.citybikes_backend.journey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JourneyService {

    private final JourneyRepository journeyRepository;

    @Autowired
    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public Optional<Journey> getJourney(Integer journey_id) {
        boolean doesExist = journeyRepository.existsById(journey_id);
        if (!doesExist){
            throw new IllegalStateException("The journey with the given ID doesn't exist");
        }
        return journeyRepository.findById(journey_id);
    }
    
    public List<Journey> getJourneys() {
        return journeyRepository.findAll();
    }
    
    public Page<Journey> getJourneysPage(Integer pageNr, Integer pageLen) {
        Pageable pageRequest = PageRequest.of(pageNr, pageLen);
        return journeyRepository.findAll(pageRequest);
    }

    public long getNumJourneysFromStation(String stationId){
        Journey journey = new Journey();
        journey.setDeparture_station_code(stationId);
        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
            .withMatcher("departure_station_code", ExampleMatcher.GenericPropertyMatchers.exact())
            .withIgnorePaths("departure_station_name",
             "return_station_code",
             "return_station_name",
             "distance",
             "duration");
        Example<Journey> example = Example.of(journey, ignoringExampleMatcher);
        return journeyRepository.count(example);
    }
    
    public long getNumJourneysToStation(String stationId){
        Journey journey = new Journey();
        journey.setReturn_station_code(stationId);
        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
            .withMatcher("return_station_code", ExampleMatcher.GenericPropertyMatchers.exact())
            .withIgnorePaths("departure_station_name",
             "departure_station_code",
             "return_station_name",
             "distance",
             "duration");
        Example<Journey> example = Example.of(journey, ignoringExampleMatcher);
        return journeyRepository.count(example);
    }

}
