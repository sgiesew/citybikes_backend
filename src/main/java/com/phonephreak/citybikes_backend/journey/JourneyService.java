package com.phonephreak.citybikes_backend.journey;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.hibernate.type.descriptor.jdbc.TimestampWithTimeZoneJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
        if (sortField.equals("") || sortOrder.equals(""))
            pageRequest = PageRequest.of(pageNr, pageLen);
        else if (sortOrder.equals("ascend"))
            pageRequest = PageRequest.of(pageNr, pageLen, Sort.by(sortField));
        else
            pageRequest = PageRequest.of(pageNr, pageLen, Sort.by(sortField).descending());

        if (filterDeparture != null || filterReturn != null){
            final ExampleMatcher MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("departureStationName", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("returnStationName", ExampleMatcher.GenericPropertyMatchers.contains())
            .withIgnorePaths("departureDate", "returnDate", "departureStationCode", "returnStationCode", "distance", "duration");
            Journey journey = new Journey();
            if (filterDeparture != null){
                switch (filterDeparture){
                case "metro":
                    journey.setDepartureStationName("(M)");
                    break;
                case "train":
                    journey.setDepartureStationName(" asema");
                    break;
                }
            }
            if (filterReturn != null){
                switch (filterReturn){
                case "metro":
                    journey.setReturnStationName("(M)");
                    break;
                case "train":
                    journey.setReturnStationName(" asema");
                    break;
                }
            }
            Example<Journey> example = Example.of(journey, MATCH_ALL);
            return journeyRepository.findAll(example, pageRequest);
        }
        return journeyRepository.findAll(pageRequest);
    }

    public long getNumJourneysFromStation(String stationId){
        return journeyRepository.countByDepartureStationCode(stationId);
    }
    
    public long getNumJourneysToStation(String stationId){
        return journeyRepository.countByReturnStationCode(stationId);
    }

}
