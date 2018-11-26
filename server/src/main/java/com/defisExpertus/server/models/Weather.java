package com.defisExpertus.server.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Weather {

    public @Id @GeneratedValue Long id;
    public String city;
    public String state;
    public int temp;
    public int hum;
    public int wind;
    public int date;

    public Weather(String city, String state, int temp, int hum, int wind) {
        this.city = city;
        this.state = state;
        this.temp = temp;
        this.hum = hum;
        this.wind = wind;
    }

    public Weather(){}
}