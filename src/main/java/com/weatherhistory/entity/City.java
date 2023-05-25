package com.weatherhistory.entity;

import com.weatherhistory.dto.openmap.CityDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.geo.Point;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(length = 2)
    private String countryCode;
    @Column(nullable = false)
    private Point location;
    @OneToMany(mappedBy = "city")
    List<Weather> weathers;

    public static City fromDTO(CityDTO city) {
        City result = new City();
        result.setName(city.name());
        result.setLocation(new Point(city.lat(), city.lon()));
        result.setCountryCode(city.country());
        if (city.id() != 0) {
            result.setId(city.id());
        }

        return result;
    }
    //@ManyToMany(cascade = CascadeType.ALL)
    ////private List<Weather> weather;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
