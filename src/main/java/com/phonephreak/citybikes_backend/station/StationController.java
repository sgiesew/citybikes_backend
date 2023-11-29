package com.phonephreak.citybikes_backend.station;

import com.phonephreak.citybikes_backend.journey.DailyCountsResult;
import com.phonephreak.citybikes_backend.journey.JourneyService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping(path = "api/station")
@Validated
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
    public ResponseEntity<StationDetail> getStation(@PathVariable("stationId") Integer stationId){
        Optional<Station> station = stationService.getStation(stationId);
        StationDetail stationDetails = new StationDetail();
        if (station.isPresent()){
            long numDepartures = journeyService.getNumJourneysFromStation(stationId);
            long averageDepartureDistance = numDepartures > 0 ? (long)Math.floor(journeyService.getAverageJourneyDistanceFromStation(stationId)) : 0;
            ArrayList<String> returnedToFromStationRanked = numDepartures > 0 ? (ArrayList<String>) journeyService.returnedToFromStationRanked(stationId) : new ArrayList<String>();
            long numReturns = journeyService.getNumJourneysToStation(stationId);
            long averageReturnDistance = numReturns > 0 ? (long)Math.floor(journeyService.getAverageJourneyDistanceToStation(stationId)) : 0;
            ArrayList<String> departedFromToStationRanked = numReturns > 0 ? (ArrayList<String>) journeyService.departedFromToStationRanked(stationId) : new ArrayList<String>();
            ArrayList<DailyCountsResult> dailyDepartureCounts = (ArrayList<DailyCountsResult>) journeyService.dailyDeparturesFromStation(stationId);
            ArrayList<DailyCountsResult> dailyReturnCounts = (ArrayList<DailyCountsResult>) journeyService.dailyReturnsToStation(stationId);
            stationDetails = new StationDetail(
                station.get().getName(),
                station.get().getAddress(),
                station.get().getCity(),
                station.get().getXPos(),
                station.get().getYPos(),
                numDepartures,
                numReturns,
                averageDepartureDistance,
                averageReturnDistance,
                returnedToFromStationRanked,
                departedFromToStationRanked,
                dailyDepartureCounts,
                dailyReturnCounts
            );
        }
        return ResponseEntity.ok().body(stationDetails);
    }
    @ExceptionHandler
    public ResponseEntity<HttpStatus> handleException(IllegalStateException e){
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/page")
    public ResponseEntity<Page<Station>> getStationsPage(@RequestBody Map<String, Object> payload) throws ParseException{
        Integer pageNr = (Integer) payload.get("pageIndex");
        Integer pageLen = (Integer) payload.get("pageSize");
        String searchTerm = (String) payload.get("globalFilter");
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

    @PostMapping
    public ResponseEntity<Station> addNewStation(@Valid @RequestBody Station station){
        return ResponseEntity.created(null).body(stationService.addNewStation(station));
    }
    
    @DeleteMapping(path = "{stationId}")
    public ResponseEntity<HttpStatus> deleteStation(@PathVariable("stationId") Integer stationId){
        stationService.deleteStation(stationId);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
