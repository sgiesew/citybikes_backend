package com.phonephreak.citybikes_backend.station;

import com.phonephreak.citybikes_backend.journey.DailyCountsResult;
import com.phonephreak.citybikes_backend.journey.JourneyService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Station>> getStations(){
        return ResponseEntity.ok().body(stationService.getStations());
    }

    @GetMapping(path = "{stationId}")
    public ResponseEntity<Map<String, Object>> getStation(@PathVariable("stationId") Integer stationId){
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
            ArrayList<DailyCountsResult> dailyDepartureCounts = new ArrayList<DailyCountsResult>();
            for (Object[] o : journeyService.dailyDeparturesFromStation(station.get().getStationCode())){
                DailyCountsResult dailyCount = new DailyCountsResult(o[0].toString().substring(0, 10), (long) o[1]);
                dailyDepartureCounts.add(dailyCount);
            }
            stationDetails.put("dailyDeparturesFromStation", dailyDepartureCounts);
            ArrayList<DailyCountsResult> dailyReturnCounts = new ArrayList<DailyCountsResult>();
            for (Object[] o : journeyService.dailyReturnsToStation(station.get().getStationCode())){
                DailyCountsResult dailyCount = new DailyCountsResult(o[0].toString().substring(0, 10), (long) o[1]);
                dailyReturnCounts.add(dailyCount);
            }
            stationDetails.put("dailyReturnsToStation", dailyReturnCounts);
        }
        //System.out.println(journeyService.returnedToFromStationRanked(station.get().getStationCode()));
        return ResponseEntity.ok().body(stationDetails);
    }
    @ExceptionHandler
    public ResponseEntity<HttpStatus> handleException(IllegalStateException e){
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping(path = "/page")
    public ResponseEntity<Page<Station>> getStationsPage(@RequestBody Map<String, Object> payload) throws ParseException{
        Integer pageNr = (Integer) payload.get("curPage");
        Integer pageLen = (Integer) payload.get("pageLen");
        String searchTerm = (String) payload.get("searchTerm");
        String filterCity = (String) payload.get("filterCity");
        return ResponseEntity.ok().body(stationService.getStationsPage(pageNr, pageLen, searchTerm, filterCity));
    }
    @ExceptionHandler
    public ResponseEntity<HttpStatus> handleException(ParseException e){
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<HttpStatus> handleException(ClassCastException e){
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
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
