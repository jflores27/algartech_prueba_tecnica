package com.algartech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.algartech.entity.Weather;
import com.algartech.repository.WeatherRepository;

@Service
public class WeatherService {
	    
    private final RestTemplate restTemplate;
    
    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherForCity(String cityName) {
        try {
            String uri = UriComponentsBuilder
                .fromHttpUrl("http://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", cityName)
                .queryParam("appid","5b60331e5c0ce9050ea25f2457315c9b")
                .toUriString();

            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String currentWeatherData = response.getBody();

                Optional<Weather> lastWeatherData = weatherRepository.findTopByCityNameOrderByCreatedAtDesc(cityName);

                if (!lastWeatherData.isPresent() || !lastWeatherData.get().getWeatherData().equals(currentWeatherData)) {
                    Weather weather = new Weather();
                    weather.setCityName(cityName);
                    weather.setWeatherData(currentWeatherData);
                    weatherRepository.save(weather);
                }

                return currentWeatherData;
            } else {
                throw new RuntimeException("Failed to fetch weather data from OpenWeather API");
            }
        } catch (Exception e) {
        	return weatherRepository.findTopByCityNameOrderByCreatedAtDesc(cityName)
                    .map(Weather::getWeatherData)
                    .orElse("No hay datos disponibles para esta ciudad");
        }
    }

    public List<Weather> getLatestWeatherFromUniqueCities() {
        return weatherRepository.findLatestWeatherRecordsFromUniqueCities(PageRequest.of(0, 10));
    }
}
