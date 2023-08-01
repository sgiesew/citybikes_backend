package com.phonephreak.citybikes_backend.journey;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Configuration
public class JourneyConfig {

    private void readCSVData(JourneyRepository repository, String filename) throws CsvValidationException, IOException{
        CSVReader reader = new CSVReader(new FileReader(filename, StandardCharsets.UTF_8));
        Journey journey;
        String [] nextLine;
        nextLine = reader.readNext(); // skip header
        
        final int batchSize = 1000;
        int counter = 0;
        List<Journey> journeyList = new ArrayList<>();
        while ((nextLine = reader.readNext()) != null){
            Float distance = 0f;
            distance = Float.parseFloat(nextLine[6]);
            Integer duration = 0;
            try{
                duration = Integer.parseInt(nextLine[7]);
            }
            catch(Exception e){
                float d = Float.parseFloat(nextLine[7]);
                duration = (int)(d);
            }
            if (distance >= 10 && duration >= 10){
                journey = new Journey();
                journey.setDepartureStationId(Integer.parseInt(nextLine[2]));
                journey.setDepartureStationName(nextLine[3]);
                journey.setReturnStationId(Integer.parseInt(nextLine[4]));
                journey.setReturnStationName(nextLine[5]);
                journey.setDistance(distance);
                journey.setDuration(duration);

                journeyList.add(journey);
                counter++;
                if (counter == batchSize){
                    repository.saveAll(journeyList);
                    journeyList.clear();
                    counter = 0;
                }
            }
        }
        repository.saveAll(journeyList);
    }

    @Bean
    CommandLineRunner commandLineRunner2(JourneyRepository repository){
        return args -> {
            // Initialize journeys table from CSV file
            if (repository.count() == 0){ // table is empty: initialize
                System.out.println("Initializing journeys table...");
                readCSVData(repository, "C:/Users/Stefan/Documents/fullstack22/Solita/2021-05.csv");
                readCSVData(repository, "C:/Users/Stefan/Documents/fullstack22/Solita/2021-06.csv");
                readCSVData(repository, "C:/Users/Stefan/Documents/fullstack22/Solita/2021-07.csv");
                System.out.println("Journeys table initialized");
            }
            else {
                System.out.println("Journeys table ready");
            }
        };
    }
}

