package com.phonephreak.citybikes_backend.station;

import com.phonephreak.citybikes_backend.journey.JourneyService;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@CrossOrigin    //!!!
@RestController
@RequestMapping(path = "api/station")
public class StationController {

    private final StationService stationService;
    private final JourneyService journeyService;

    public StationController(StationService stationService, JourneyService journeyService) {
        this.stationService = stationService;
        this.journeyService = journeyService;
        System.out.println("-- station controller initialized --");
    }

    /*
    @GetMapping(path = "{stationId}")
    public StationLong getStation(@PathVariable("stationId") Integer stationId){
        Optional<Station> station = stationService.getStation(stationId);
        StationLong station_long = null;
        if (station.isPresent()){
            station_long = new StationLong(station.get());
            station_long.num_departures = journeyService.getNumJourneysFromStation(station.get().getStation_code());
            station_long.num_returns = journeyService.getNumJourneysToStation(station.get().getStation_code());
        }
        return station_long;
    }
     */
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
