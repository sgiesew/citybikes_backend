package com.phonephreak.citybikes_backend.journey;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Configuration
public class JourneyConfig {

    private void readCSVData(JourneyRepository repository, String filename) throws CsvValidationException, IOException{
        CSVReader reader = new CSVReader(new FileReader(filename));
        Journey journey;
        String [] nextLine;
        nextLine = reader.readNext(); // skip header
        
        int counter = 0;
        while ((nextLine = reader.readNext()) != null && counter < 100){
            Integer distance = Integer.parseInt(nextLine[6]);
            Integer duration = Integer.parseInt(nextLine[7]);
            if (distance >= 10 && duration >= 10){
                //System.out.println(counter + ": " + nextLine[3] + " -> " + nextLine[5]);
                journey = new Journey();
                journey.setDeparture_station_code(nextLine[2]);
                journey.setDeparture_station_name(nextLine[3]);
                journey.setReturn_station_code(nextLine[4]);
                journey.setReturn_station_name(nextLine[5]);
                journey.setDistance(distance);
                journey.setDuration(duration);

                repository.save(journey);
            }
            counter++;
        }
        System.out.println("Journeys table initialized");
}

    @Bean
    CommandLineRunner commandLineRunner2(JourneyRepository repository){
        return args -> {
            // Initialize journeys table from CSV file
            if (!repository.existsById(1)){ // table is empty: initialize
                System.out.println("Initializing journeys table...");
                readCSVData(repository, "C:/Users/Stefan/Documents/fullstack22/Solita/2021-05.csv");
                readCSVData(repository, "C:/Users/Stefan/Documents/fullstack22/Solita/2021-06.csv");
                readCSVData(repository, "C:/Users/Stefan/Documents/fullstack22/Solita/2021-07.csv");
            }
            else {
                System.out.println("Journeys table ready");
            }
        };
    }
}

