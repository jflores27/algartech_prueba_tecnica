package com.algartech.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.algartech.entity.Weather;
import com.algartech.service.WeatherService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class WeatherControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    public void whenGetWeather_thenReturnsWeather() throws Exception {
        when(weatherService.getWeatherForCity("TestCity")).thenReturn("Sunny");

        mockMvc.perform(get("/weather/TestCity")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Sunny"));
    }

    @Test
    public void whenGetWeatherHistory_thenReturnsHistory() throws Exception {
        List<Weather> weatherHistory = new ArrayList<>();
        weatherHistory.add(new Weather("City1", "Sunny"));
        weatherHistory.add(new Weather("City2", "Cloudy"));

        when(weatherService.getLatestWeatherFromUniqueCities()).thenReturn(weatherHistory);

        mockMvc.perform(get("/weather/history")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)));
    }
}


