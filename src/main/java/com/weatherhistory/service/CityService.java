package com.weatherhistory.service;

import com.weatherhistory.ApiRequestFailedException;
import com.weatherhistory.CityNotFoundException;
import com.weatherhistory.dto.openmap.CityDTO;
import com.weatherhistory.entity.City;
import com.weatherhistory.entity.Weather;
import com.weatherhistory.repository.CityRepository;
import com.weatherhistory.repository.WeatherRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final WeatherRepository weatherRepository;
    private final GeocodingRestService geocodingRestService;
    private final Logger logger = LoggerFactory.getLogger(CityService.class);


    @Autowired
    public CityService(CityRepository cityRepository, WeatherRepository weatherRepository, GeocodingRestService geocodingRestService) {
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
        this.geocodingRestService = geocodingRestService;
    }

    @Transactional
    public List<CityDTO> getCities(String name) throws ApiRequestFailedException {
        List<CityDTO> knownCities = cityRepository.findAllByName(name).stream()
                .map(CityDTO::fromCity)
                .toList();
        logger.info("Found {} cities with name {} in cache DB", knownCities.size(), name);

        if (knownCities.isEmpty()) {
            List<City> entities = geocodingRestService.fetchCities(name).stream().map(City::fromDTO).toList();
            logger.info("City with the name {} is not found in cache DB. Fetched {} resuls from external API", name, entities.size());
            cityRepository.saveAll(entities);
            knownCities = entities.stream().map(CityDTO::fromCity).toList();
        }

        return knownCities;
    }

    @Transactional
    public void addCity(String name, CityDTO cityDTO) {
        if (!cityRepository.findAllByName(name).isEmpty()) {
            return;
        }

        City city = City.fromDTO(cityDTO);
        city.setName(name);
        cityRepository.save(city);
    }

    @Transactional
    public List<Weather> getWeather(long cityId, LocalDate date) throws CityNotFoundException {
        City city = cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException(cityId));

        return weatherRepository.findAllByCityAndDate(city, date);
    }
}
