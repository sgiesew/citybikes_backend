package com.phonephreak.citybikes_backend.station;

import java.io.FileReader;

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
            if (!repository.existsById(1)){ // table is empty: initialize
                System.out.println("Initializing stations table...");
                Station station;

                CSVReader reader = new CSVReader(new FileReader("C:/Users/Stefan/Documents/fullstack22/Solita/stations.csv"));
                String [] nextLine;
                nextLine = reader.readNext(); // skip header
                String city;
                
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

                    repository.save(station);
                }
                System.out.println("Stations table initialized");
            }
            else {
                System.out.println("Stations table ready");
            }
        };
    }
}

