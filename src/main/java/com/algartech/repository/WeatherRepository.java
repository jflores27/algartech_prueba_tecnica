package com.algartech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import com.algartech.entity.Weather;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findTopByCityNameOrderByCreatedAtDesc(String cityName);
    @Query("SELECT w.cityName, MAX(w.createdAt) FROM Weather w GROUP BY w.cityName ORDER BY MAX(w.createdAt) DESC")
    List<Weather> findLatestWeatherRecordsFromUniqueCities(Pageable pageable);
}

