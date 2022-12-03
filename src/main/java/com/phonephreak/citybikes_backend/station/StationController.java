package com.phonephreak.citybikes_backend.station;

import com.phonephreak.citybikes_backend.journey.JourneyService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin
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

    @GetMapping(path = "{stationId}")
    public Map<String, Object> getStation(@PathVariable("stationId") Integer stationId){
        Optional<Station> station = stationService.getStation(stationId);
        Map<String, Object> stationLong = new HashMap<String, Object>();
        if (station.isPresent()){
            stationLong.put("name", station.get().getName());
            stationLong.put("address", station.get().getAddress());
            stationLong.put("city", station.get().getCity());
            stationLong.put("pos_x", station.get().getPos_x());
            stationLong.put("pos_y", station.get().getPos_y());
            stationLong.put("num_departures", journeyService.getNumJourneysFromStation(station.get().getStation_code()));
            stationLong.put("num_returns", journeyService.getNumJourneysToStation(station.get().getStation_code()));
        }
        return stationLong;
    }
    
    @PostMapping
    public Page<Station> getStationsPage(@RequestBody Map<String, Object> payload) throws ParseException{
        Integer pageNr = (Integer) payload.get("pageNr");      
        Integer pageLen = (Integer) payload.get("pageLen");      
        return stationService.getStationsPage(pageNr, pageLen);
    }

    /*
    @PostMapping
    public void addNewStation(@RequestBody Station station){
        stationService.addNewStation(station);
    }
    
    @DeleteMapping(path= "{stationId}")
    public void deleteStation(@PathVariable("stationId") Integer stationId){
        stationService.deleteStation(stationId);
    }
    */
}
