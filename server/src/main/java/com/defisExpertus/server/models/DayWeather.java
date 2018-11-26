package com.defisExpertus.server.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class DayWeather {

    public @Id @GeneratedValue Long id;
    public String city;
    public String state;
    public int temp;
    public int hum;
    public int wind;
    public int date;

    public DayWeather(String city, String state, int temp, int hum, int wind, int date) {
        this.city = city;
        this.state = state;
        this.temp = temp;
        this.hum = hum;
        this.wind = wind;
        this.date = date;
    }

    public DayWeather(Weather weather, int date) {
        this.city = weather.city;
        this.state = weather.state;
        this.temp = weather.temp;
        this.hum = weather.hum;
        this.wind = weather.wind;
        this.date = date;
    }

    public DayWeather(){}
}
