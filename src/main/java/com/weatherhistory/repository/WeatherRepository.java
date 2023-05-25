package com.weatherhistory.repository;

import com.weatherhistory.entity.City;
import com.weatherhistory.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    List<Weather> findAllByCityAndDate(City city, LocalDate date);
}
