package com.phonephreak.citybikes_backend.station;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/station")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
        System.out.println("-- controller initialized --");
    }

    @GetMapping(path = "{stationId}")
    public Optional<Station> getStation(@PathVariable("stationId") Integer stationId){
        return stationService.getStation(stationId);
    }
    @GetMapping
    public List<Station> getStations(){
        return stationService.getStations();
    }
    @PostMapping
    public void addNewStation(@RequestBody Station station){
        stationService.addNewStation(station);
    }
    @DeleteMapping(path= "{stationId}")
    public void deleteStation(@PathVariable("stationId") Integer stationId){
        stationService.deleteStation(stationId);
    }


}
