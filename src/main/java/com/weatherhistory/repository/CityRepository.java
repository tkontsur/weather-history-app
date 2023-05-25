package com.weatherhistory.repository;

import com.weatherhistory.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);

    List<City> findAllByName(String name);
}
