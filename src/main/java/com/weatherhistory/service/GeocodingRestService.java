package com.weatherhistory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherhistory.ApiRequestFailedException;
import com.weatherhistory.dto.openmap.CityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GeocodingRestService {
    @Value("${city.service.currentweather.url}")
    private String weatherUrl;
    @Value("${city.service.geo.url}")
    private String geocodingUrl;
    @Value("${city.service.key}")
    private String cityServiceApiKey;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(GeocodingRestService.class);

    public List<CityDTO> fetchCities(String cityName) throws ApiRequestFailedException {
        try {
            HttpRequest cityRequest = HttpRequest.newBuilder(new URI(geocodingUrl +
                            "/direct?q=" + cityName + "&appid=" + cityServiceApiKey))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(cityRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                CityDTO[] cities = objectMapper.readValue(response.body(), CityDTO[].class);
                return Arrays.asList(cities);
            } else {
                logger.error("Error while calling OpenMap API. Status returned {}; error message: {}",
                        response.statusCode(), response.body());
                throw new ApiRequestFailedException();
            }
        } catch (URISyntaxException | IOException e) {
            logger.error("Request to OpenMap API failed", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return Collections.emptyList();
    }
}
