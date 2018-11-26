package com.defisExpertus.server.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Forecast {

    public @Id @GeneratedValue Long id;
    public String city;
    @OneToOne(cascade = {CascadeType.ALL})
    DayWeather day1;
    @OneToOne(cascade = {CascadeType.ALL})
    DayWeather day2;
    @OneToOne(cascade = {CascadeType.ALL})
    DayWeather day3;
    @OneToOne(cascade = {CascadeType.ALL})
    DayWeather day4;
    @OneToOne(cascade = {CascadeType.ALL})
    DayWeather day5;
    @OneToOne(cascade = {CascadeType.ALL})
    DayWeather day6;
    @OneToOne(cascade = {CascadeType.ALL})
    DayWeather day7;

    public Forecast(String city, List<DayWeather> list){
        this.city = city;
        this.day1 = list.get(0);
        this.day2 = list.get(1);
        this.day3 = list.get(2);
        this.day4 = list.get(3);
        this.day5 = list.get(4);
        this.day6 = list.get(5);
        this.day7 = list.get(6);
    }

    public Forecast(){}
}
