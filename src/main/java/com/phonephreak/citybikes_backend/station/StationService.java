package com.phonephreak.citybikes_backend.station;

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
public class StationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public Optional<Station> getStation(Integer station_id) {
        boolean doesExist = stationRepository.existsById(station_id);
        if (!doesExist){
            throw new IllegalStateException("A station with this station_id does not exist");
        }
        return stationRepository.findById(station_id);
    }

    public List<Station> getStations(){
        return stationRepository.findAll();
    }
    
    public Page<Station> getStationsPage(Integer pageNr, Integer pageLen, String searchTerm, String filterCity) {
        Pageable pageRequest = PageRequest.of(pageNr, pageLen);
        if (searchTerm == null && filterCity == null)
            return stationRepository.findAll(pageRequest);
        else {
            final ExampleMatcher MATCH_ALL = ExampleMatcher
                .matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase().contains())
                .withMatcher("city", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("stationCode", "xPos", "yPos", "address");
            Station station = new Station();
            station.setName(searchTerm);
            station.setCity(filterCity);
            Example<Station> example = Example.of(station, MATCH_ALL);
            return stationRepository.findAll(example, pageRequest);
        }
    }

    public void addNewStation(Station station) {
        stationRepository.save(station);
    }

    public void deleteStation(Integer station_id) {
       boolean doesExist = stationRepository.existsById(station_id);
       if (!doesExist){
           throw new IllegalStateException("The station with the given ID doesn't exist");
       }
       else {
           stationRepository.deleteById(station_id);
       }
    }


}
