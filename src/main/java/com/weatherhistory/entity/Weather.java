package com.weatherhistory.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id")
    private City city;
    private LocalDate date;
    private String description;
    private String textDescription;
    private String icon;
    @Column(name = "temperature", precision = 5, scale = 2, nullable = false)
    private BigDecimal temperature;
    @Column(name = "temperature_max", precision = 5, scale = 2, nullable = false)
    private BigDecimal temperatureMax;
    @Column(name = "temperature_min", precision = 5, scale = 2, nullable = false)
    private BigDecimal temperatureMin;

    public Long getId() {
        return id;
    }

    public Weather setId(Long id) {
        this.id = id;
        return this;
    }

    public City getCity() {
        return city;
    }

    public Weather setCity(City city) {
        this.city = city;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Weather setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Weather setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public Weather setTextDescription(String textDescription) {
        this.textDescription = textDescription;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Weather setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public Weather setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
        return this;
    }

    public BigDecimal getTemperatureMax() {
        return temperatureMax;
    }

    public Weather setTemperatureMax(BigDecimal temperatureMax) {
        this.temperatureMax = temperatureMax;
        return this;
    }

    public BigDecimal getTemperatureMin() {
        return temperatureMin;
    }

    public Weather setTemperatureMin(BigDecimal temperatureMin) {
        this.temperatureMin = temperatureMin;
        return this;
    }
}
