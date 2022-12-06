package com.phonephreak.citybikes_backend.journey;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.text.ParseException;

import org.springframework.data.domain.Page;
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

    @PostMapping
    public Page<Journey> getJourneysPage(@RequestBody Map<String, Object> payload) throws ParseException{
        Integer pageNr = (Integer) payload.get("pageNr");      
        Integer pageLen = (Integer) payload.get("pageLen");      
        String sortField = (String) payload.get("sortField");      
        String sortOrder = (String) payload.get("sortOrder");      
        String filterDeparture = (String) payload.get("filterDeparture");      
        String filterReturn = (String) payload.get("filterReturn");      
        return journeyService.getJourneysPage(pageNr, pageLen, sortField, sortOrder, filterDeparture, filterReturn);
    }

}
