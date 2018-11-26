package com.defisExpertus.server.services;
import com.defisExpertus.server.models.*;
import lombok.var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WeatherServices {

    int wind_min;
    int wind_max;
    int temp_min;
    int temp_max;
    int hum_min;
    int hum_max;
    List<String> state_possibilities;

    public WeatherServices(){
        this.wind_min = 0;
        this.wind_max = 54;
        this.hum_min = 0;
        this.hum_max = 30;
        this.temp_min = 0;
        this.temp_max = 25;
        this.state_possibilities = Arrays.asList("Clouds", "Rain", "Drizzle", "Storm", "Sunny", "Clear");
    }

    public Weather generateWeather(String city){
        var wind = this.wind_min + (int)(Math.random() * ((this.wind_max - this.wind_min) + 1));
        var hum = this.hum_min + (int)(Math.random() * ((this.hum_max - this.hum_min) + 1));
        var temp = this.temp_min + (int)(Math.random() * ((this.temp_max - this.temp_min) + 1));
        var state = this.state_possibilities.get((int)(Math.random() * (this.state_possibilities.size())));
        return new Weather(city, state, temp, hum, wind);
    }

    public Forecast generateForecast(String city){
        List<DayWeather> list = new ArrayList<>();
        for (int i=0; i<7; i++){
            var weather = generateWeather(city);
            list.add(new DayWeather(weather, i));
        }
        return new Forecast(city, list);
    }

    public void addWeather(WeatherRepository repo, String city){
        Weather weather = generateWeather(city);
        repo.save(weather);
    }

    public void addForecast(ForecastRepository repo, String city){
        Forecast forecast = generateForecast(city);
        repo.save(forecast);
    }

    public void deleteWeather(WeatherRepository repo, String city){
        List<Weather> list = repo.findAll();
        for(Weather weather: list){
            if(weather.city==city){
                repo.delete(weather);
            }
        }
    }

    public void deleteForecast(ForecastRepository repo, String city){
        List<Forecast> list = repo.findAll();
        for(Forecast forecast: list){
            if(forecast.city==city){
                repo.delete(forecast);
            }
        }
    }
}
