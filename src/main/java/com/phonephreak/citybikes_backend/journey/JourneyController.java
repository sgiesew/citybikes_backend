package com.phonephreak.citybikes_backend.journey;

import java.util.Map;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/journey")
public class JourneyController {

    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
        System.out.println("-- journey controller initialized --");
    }

    @PostMapping(path = "/page")
    public ResponseEntity<Page<Journey>> getJourneysPage(@RequestBody Map<String, Object> payload) throws ParseException{
        Integer pageNr = (Integer) payload.get("curPage");      
        Integer pageLen = (Integer) payload.get("pageLen");      
        String sortField = (String) payload.get("sortField");      
        String sortOrder = (String) payload.get("sortOrder");      
        String filterDeparture = (String) payload.get("filterDeparture");      
        String filterReturn = (String) payload.get("filterReturn");      
        return ResponseEntity.ok().body(journeyService.getJourneysPage(pageNr, pageLen, sortField, sortOrder, filterDeparture, filterReturn));
    }
    @ExceptionHandler
    public ResponseEntity<HttpStatus> handleException(ParseException e){
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<HttpStatus> handleException(ClassCastException e){
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

}
