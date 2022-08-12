package com.phonephreak.citybikes_backend.journey;

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

    @GetMapping(path = "page/{pageNr}/len/{pageLen}")
    public Page<Journey> getJourneysPage(@PathVariable("pageNr") Integer pageNr, @PathVariable("pageLen") Integer pageLen){
        return journeyService.getJourneysPage(pageNr, pageLen);
    }

}
