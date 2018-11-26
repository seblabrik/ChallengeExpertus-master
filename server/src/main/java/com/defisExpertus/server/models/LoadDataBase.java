package com.defisExpertus.server.models;

import com.defisExpertus.server.services.WeatherServices;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDataBase {

    WeatherServices services;

    LoadDataBase(){this.services = new WeatherServices();}

    @Bean
    CommandLineRunner initWeatherDatabase(WeatherRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save((Weather) services.generateWeather("paris")));
        };
    }

    @Bean
    CommandLineRunner initForecastDatabase(ForecastRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save((Forecast) services.generateForecast("paris")));
        };
    }
}
