package com.phonephreak.citybikes_backend.journey;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.type.descriptor.jdbc.TimestampWithTimeZoneJdbcType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Integer> {

        long countByReturnStationCode(String stationId);
        long countByDepartureStationCode(String stationId);
        
        long count();

        //long countByDistanceBetween(Float d1, Float d2);

}
