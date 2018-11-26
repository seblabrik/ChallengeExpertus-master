package com.defisExpertus.server.models;

import org.springframework.data.jpa.repository.JpaRepository;


public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
