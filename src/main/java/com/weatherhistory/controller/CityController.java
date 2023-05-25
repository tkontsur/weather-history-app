package com.weatherhistory.controller;

import com.weatherhistory.ApiRequestFailedException;
import com.weatherhistory.dto.openmap.CityDTO;
import com.weatherhistory.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/{cityName}")
    public void saveCity(@PathVariable(name = "cityName") String name, @RequestBody CityDTO cityDTO) {
        cityService.addCity(name, cityDTO);
    }

    @GetMapping
    public List<CityDTO> getWeather(@RequestParam(name = "name") String cityName) throws ApiRequestFailedException {
        return cityService.getCities(cityName);
    }
}
