package com.phonephreak.citybikes_backend.station;

import com.phonephreak.citybikes_backend.journey.DailyCountsResult;
import com.phonephreak.citybikes_backend.journey.JourneyService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


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
    public Map<String, Object> getStation(@PathVariable("stationId") Integer stationId) throws ParseException{
        Optional<Station> station = stationService.getStation(stationId);
        Map<String, Object> stationDetails = new HashMap<String, Object>();
        if (station.isPresent()){
            stationDetails.put("name", station.get().getName());
            stationDetails.put("address", station.get().getAddress());
            stationDetails.put("city", station.get().getCity());
            stationDetails.put("xPos", station.get().getXPos());
            stationDetails.put("yPos", station.get().getYPos());
            long numDepartures = journeyService.getNumJourneysFromStation(station.get().getStationCode());
            stationDetails.put("numDepartures", numDepartures);
            long numReturns = journeyService.getNumJourneysToStation(station.get().getStationCode());
            stationDetails.put("numReturns", numReturns);
            stationDetails.put("averageDepartureDistance", numDepartures > 0 ? Math.floor(journeyService.getAverageJourneyDistanceFromStation(station.get().getStationCode())) : 0);
            stationDetails.put("returnedToFromStationRanked", numDepartures > 0 ? journeyService.returnedToFromStationRanked(station.get().getStationCode()) : 0);
            stationDetails.put("averageReturnDistance", numReturns > 0 ? Math.floor(journeyService.getAverageJourneyDistanceToStation(station.get().getStationCode())) : 0);
            stationDetails.put("departedFromToStationRanked", numReturns > 0 ? journeyService.departedFromToStationRanked(station.get().getStationCode()) : 0);
            ArrayList<DailyCountsResult> dailyCounts = new ArrayList<DailyCountsResult>();
            for (Object[] o : journeyService.dailyDeparturesFromStation(station.get().getStationCode())){
                DailyCountsResult dailyCount = new DailyCountsResult(o[0].toString().substring(0, 10), o[1].toString());
                dailyCounts.add(dailyCount);
            }
            stationDetails.put("dailyDeparturesFromStation", dailyCounts);
            dailyCounts.clear();
            for (Object[] o : journeyService.dailyReturnsToStation(station.get().getStationCode())){
                DailyCountsResult dailyCount = new DailyCountsResult(o[0].toString().substring(0, 10), o[1].toString());
                dailyCounts.add(dailyCount);
            }
            stationDetails.put("dailyReturnsToStation", dailyCounts);
        }
        //System.out.println(journeyService.returnedToFromStationRanked(station.get().getStationCode()));
        return stationDetails;
    }
    
    @PostMapping
    public Page<Station> getStationsPage(@RequestBody Map<String, Object> payload) throws ParseException{
        Integer pageNr = (Integer) payload.get("curPage");
        Integer pageLen = (Integer) payload.get("pageLen");
        String searchTerm = (String) payload.get("searchTerm");
        String filterCity = (String) payload.get("filterCity");
        return stationService.getStationsPage(pageNr, pageLen, searchTerm, filterCity);
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
