package com.phonephreak.citybikes_backend.station;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    public Page<Station> getStationsPage(Integer pageNr, Integer pageLen) {
        Pageable pageRequest = PageRequest.of(pageNr, pageLen);
        return stationRepository.findAll(pageRequest);
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
