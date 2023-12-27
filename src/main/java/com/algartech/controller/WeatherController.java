package com.algartech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algartech.entity.Weather;
import com.algartech.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
    private WeatherService weatherService;

    @GetMapping("/{cityName}")
    public ResponseEntity<String> getWeather(@PathVariable String cityName) {
        try {
            String weatherData = weatherService.getWeatherForCity(cityName);
            return ResponseEntity.ok(weatherData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener los datos del clima: " + e.getMessage());
        }
    }
    
    @GetMapping("/history")
    public ResponseEntity<List<Weather>> getWeatherHistory() {
        try {
            List<Weather> weatherHistory = weatherService.getLatestWeatherFromUniqueCities();
            return ResponseEntity.ok(weatherHistory);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

