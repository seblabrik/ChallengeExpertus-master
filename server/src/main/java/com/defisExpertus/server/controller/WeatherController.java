package com.defisExpertus.server.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.defisExpertus.server.models.Forecast;
import com.defisExpertus.server.models.ForecastRepository;
import com.defisExpertus.server.models.WeatherRepository;
import com.defisExpertus.server.models.Weather;
import com.defisExpertus.server.services.WeatherServices;
import jdk.nashorn.internal.objects.NativeJSON;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class WeatherController {


    private final WeatherRepository wRepository;
    private final ForecastRepository fRepository;
    private WeatherServices services;

    WeatherController(WeatherRepository wRepository, ForecastRepository fRepository) {
        this.wRepository = wRepository;
        this.fRepository = fRepository;
        this.services = new WeatherServices();
    }

//    @CrossOrigin
//    @GetMapping("/weather")
//    Weather GetWeather(){
//        List<Weather > list = wRepository.findAll();
//        Weather weather = list.get(0);
//        return(weather);
//    }

    @CrossOrigin
    @GetMapping("/weather/paris")
    Weather GetWeatherParis(){
        List<Weather > repo = wRepository.findAll();
        for (Weather weather : repo){
            if (weather.city == "paris"){
                return(weather);
            }
        }
        return null;
    }

    @CrossOrigin
    @GetMapping("/weather/doha")
    Weather GetWeatherDoha(){
        List<Weather > repo = wRepository.findAll();
        for (Weather weather : repo){
            if (weather.city == "doha"){
                return(weather);
            }
        }
        return null;
    }

//    @CrossOrigin
//    @GetMapping("/forecast")
//    Forecast GetForecast(){
//        List<Forecast > list = fRepository.findAll();
//        Forecast forecast = list.get(0);
//        return(forecast);
//    }

    @CrossOrigin
    @GetMapping("/forecast/paris")
    Forecast GetForecastParis(){
        List<Forecast > repo = fRepository.findAll();
        for (Forecast forecast : repo){
            if (forecast.city == "paris"){
                return(forecast);
            }
        }
        return null;
    }

    @CrossOrigin
    @GetMapping("/forecast/doha")
    Forecast GetForecastDoha(){
        List<Forecast > repo = fRepository.findAll();
        for (Forecast forecast : repo){
            if (forecast.city == "doha"){
                return(forecast);
            }
        }
        return null;
    }


    @PostMapping(path ="/add/doha")
    void AddCityDoha(@RequestBody String city){
        services.addWeather(wRepository, "doha");
        services.addForecast(fRepository, "doha");
    }

    @PostMapping(path ="/add/paris")
    void AddCityParis(@RequestBody String city){
        services.addWeather(wRepository, "paris");
        services.addForecast(fRepository, "paris");
    }

    @PostMapping(path ="/delete/paris")
    void DeleteCityParis(@RequestBody String city){
        services.deleteWeather(wRepository, "paris");
        services.deleteForecast(fRepository,"paris");
    }

    @PostMapping(path ="/delete/doha")
    void DeleteCityDoha(@RequestBody String city){
        services.deleteWeather(wRepository, "doha");
        services.deleteForecast(fRepository,"doha");
    }
}
