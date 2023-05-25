package com.weatherhistory.dto.openmap;

import com.weatherhistory.entity.City;

import java.util.Collections;
import java.util.Map;

public record CityDTO(
        long id,
        String name,
        Map<String, String> local_names,
        double lat,
        double lon,
        String country,
        String state
) {
    public static CityDTO fromCity(City city) {
        return new CityDTO(
                city.getId(),
                city.getName(),
                Collections.emptyMap(),
                city.getLocation().getX(),
                city.getLocation().getY(),
                city.getCountryCode(),
                "");
    }
}
