package com.phonephreak.citybikes_backend.journey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class JourneyService {

    private final JourneyRepository journeyRepository;

    @Autowired
    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public Page<Journey> getJourneysPage(Integer pageNr, Integer pageLen, String sortField, String sortOrder, String filterDeparture, String filterReturn) {
        Pageable pageRequest;
        if (sortField == null || sortOrder == null)
            pageRequest = PageRequest.of(pageNr, pageLen);
        else if (sortOrder.equals("ascend"))
            pageRequest = PageRequest.of(pageNr, pageLen, Sort.by(sortField));
        else
            pageRequest = PageRequest.of(pageNr, pageLen, Sort.by(sortField).descending());

        if (filterDeparture == null && filterReturn == null){
            return journeyRepository.findAll(pageRequest);
        }
        else {
            final ExampleMatcher MATCH_ALL = ExampleMatcher
                .matching()
                .withMatcher("departureStationName", ExampleMatcher.GenericPropertyMatchers.endsWith())
                .withMatcher("returnStationName", ExampleMatcher.GenericPropertyMatchers.endsWith())
                .withIgnorePaths("departureDate", "returnDate", "departureStationCode", "returnStationCode", "distance", "duration");
            Journey journey = new Journey();
            if (filterDeparture != null){
                switch (filterDeparture){
                case "Metro station":
                    journey.setDepartureStationName("(M)");
                    break;
                case "Train station":
                    journey.setDepartureStationName("asema");
                    break;
                case "Museum":
                    journey.setDepartureStationName("museo");
                    break;
                case "Stadium":
                    journey.setDepartureStationName("stadion");
                    break;
                case "School":
                    journey.setDepartureStationName("koulu");
                    break;
                }
            }
            if (filterReturn != null){
                switch (filterReturn){
                case "Metro station":
                    journey.setReturnStationName("(M)");
                    break;
                case "Train station":
                    journey.setReturnStationName("asema");
                    break;
                case "Museum":
                    journey.setReturnStationName("museo");
                    break;
                case "Stadium":
                    journey.setReturnStationName("stadion");
                    break;
                case "School":
                    journey.setReturnStationName("koulu");
                    break;
                }
            }
            Example<Journey> example = Example.of(journey, MATCH_ALL);
            return journeyRepository.findAll(example, pageRequest);
        }
    }

    public long getNumJourneysFromStation(String stationId){
        return journeyRepository.countByDepartureStationCode(stationId);
    }
    
    public long getNumJourneysToStation(String stationId){
        return journeyRepository.countByReturnStationCode(stationId);
    }

    public float getAverageJourneyDistanceFromStation(String stationId){
        return journeyRepository.getAverageJourneyDistanceFromStation(stationId);
    }

    public float getAverageJourneyDistanceToStation(String stationId){
        return journeyRepository.getAverageJourneyDistanceToStation(stationId);
    }

    public List<String> returnedToFromStationRanked(String stationId){
        return journeyRepository.returnedToFromStationRanked(stationId);
    }

    public List<String> departedFromToStationRanked(String stationId){
        return journeyRepository.departedFromToStationRanked(stationId);
    }

    public List<Object[]> dailyDeparturesFromStation(String stationId){
        return journeyRepository.dailyDeparturesFromStation(stationId);
    }

    public List<Object[]> dailyReturnsToStation(String stationId){
        return journeyRepository.dailyReturnsToStation(stationId);
    }
}
