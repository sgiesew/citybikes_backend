package com.phonephreak.citybikes_backend.journey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository
        extends JpaRepository<Journey, Integer> {

}
