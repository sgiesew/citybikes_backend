package com.phonephreak.citybikes_backend.journey;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/journey")
public class JourneyController {

    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
        System.out.println("-- controller initialized --");
    }

    @GetMapping(path = "{journeyId}")
    public Optional<Journey> getJourney(@PathVariable("journeyId") Integer journeyId){
        return journeyService.getJourney(journeyId);
    }
    @GetMapping
    public List<Journey> getJourneys(){
        return journeyService.getJourneys();
    }
    @PostMapping
    public void addNewJourney(@RequestBody Journey journey){
        journeyService.addNewJourney(journey);
    }
    @DeleteMapping(path= "{journeyId}")
    public void deleteJourney(@PathVariable("journeyId") Integer journeyId){
        journeyService.deleteJourney(journeyId);
    }


}
