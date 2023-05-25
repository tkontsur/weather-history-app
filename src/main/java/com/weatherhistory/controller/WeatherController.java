package com.weatherhistory.controller;

import com.weatherhistory.CityNotFoundException;
import com.weatherhistory.entity.Weather;
import com.weatherhistory.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/weather")
public class WeatherController {
    private final CityService cityService;

    public WeatherController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{cityId}/{date}")
    public List<Weather> getWeather(@PathVariable(name = "cityId") long cityId,
                                    @PathVariable(name = "date") LocalDate date) throws CityNotFoundException {
        return cityService.getWeather(cityId, date);
    }
}
