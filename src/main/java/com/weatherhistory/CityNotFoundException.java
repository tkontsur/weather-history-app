package com.weatherhistory;

public class CityNotFoundException extends Exception {
    private long cityId = 0;
    public CityNotFoundException(long cityId) {
        this.cityId = cityId;
    }

    public long getCityId() {
        return cityId;
    }
}
