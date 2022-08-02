package com.phonephreak.citybikes_backend.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository
        extends JpaRepository<Station, Integer> {

}
