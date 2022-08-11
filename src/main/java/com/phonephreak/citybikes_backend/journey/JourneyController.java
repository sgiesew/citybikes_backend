package com.phonephreak.citybikes_backend.journey;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin    //!!!
@RestController
@RequestMapping(path = "api/journey")
public class JourneyController {

    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
        System.out.println("-- journey controller initialized --");
    }

    @GetMapping(path = "{journeyId}")
    public Optional<Journey> getJourney(@PathVariable("journeyId") Integer journeyId){
        return journeyService.getJourney(journeyId);
    }
    @GetMapping
    public List<Journey> getJourneys(){
        return journeyService.getJourneys();
    }
    @GetMapping(path = "page/{pageNr}/len/{pageLen}")
    public Page<Journey> getJourneysPage(@PathVariable("pageNr") Integer pageNr, @PathVariable("pageLen") Integer pageLen){
        return journeyService.getJourneysPage(pageNr, pageLen);
    }
    @GetMapping(path = "from_station/{stationId}")
    public long getNumJourneysFromStation(@PathVariable("stationId") String stationId){
        return journeyService.getNumJourneysFromStation(stationId);
    }
    @GetMapping(path = "to_station/{stationId}")
    public long getNumJourneysToStation(@PathVariable("stationId") String stationId){
        return journeyService.getNumJourneysToStation(stationId);
    }

}
