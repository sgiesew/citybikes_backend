package com.phonephreak.citybikes_backend.station;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.opencsv.CSVReader;

@Configuration
public class StationConfig {

    @Bean
    CommandLineRunner commandLineRunner(StationRepository repository){
        return args -> {
            // Initialize stations table from CSV file
            if (false){ //repository.count() == 0){ // table is empty: initialize
                System.out.println("Initializing stations table...");
                Station station;

                CSVReader reader = new CSVReader(new FileReader("C:/Users/Stefan/Documents/fullstack22/Solita/stations.csv", StandardCharsets.UTF_8));
                String [] nextLine;
                nextLine = reader.readNext(); // skip header
                String city;
                
                List<Station> stationList = new ArrayList<>();
                while ((nextLine = reader.readNext()) != null){
                    System.out.println(nextLine[4]);

                    station = new Station();
                    station.setStation_code(nextLine[1]);
                    station.setName(nextLine[4]);
                    station.setAddress(nextLine[5]);
                    city = nextLine[7];
                    if (city.length() < 3)
                        city = "Helsinki";
                    station.setCity(city);
                    station.setPos_x(Float.parseFloat(nextLine[11]));
                    station.setPos_y(Float.parseFloat(nextLine[12]));

                    stationList.add(station);
                }
                repository.saveAll(stationList);
                System.out.println("Stations table initialized");
            }
            else {
                System.out.println("Stations table ready");
            }
        };
    }
}

