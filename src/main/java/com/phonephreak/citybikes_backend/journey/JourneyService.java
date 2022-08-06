package com.phonephreak.citybikes_backend.journey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JourneyService {

    private final JourneyRepository journeyRepository;

    @Autowired
    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
        System.out.println("-- service initialized --");
    }

    public Optional<Journey> getJourney(Integer journey_id) {
        boolean doesExist = journeyRepository.existsById(journey_id);
        if (!doesExist){
            throw new IllegalStateException("The journey with the given ID doesn't exist");
        }
        return journeyRepository.findById(journey_id);
    }
    public List<Journey> getJourneys() {
        return journeyRepository.findAll();
    }

    public void addNewJourney(Journey journey) {
        journeyRepository.save(journey);
    }

    public void deleteJourney(Integer journey_id) {
       boolean doesExist = journeyRepository.existsById(journey_id);
       if (!doesExist){
           throw new IllegalStateException("The journey with the given ID doesn't exist");
       }
       else {
           journeyRepository.deleteById(journey_id);
       }
    }


}
