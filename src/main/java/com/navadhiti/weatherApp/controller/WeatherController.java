package com.navadhiti.weatherApp.controller;

import com.navadhiti.weatherApp.response.WeatherResponse;
import com.navadhiti.weatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam String city) {
        WeatherResponse weather = weatherService.getWeather(city);
        return ResponseEntity.ok(weather);
    }

    @GetMapping("/compare")
    public ResponseEntity<Map<String, Object>>compareWeather(@RequestParam String city1,
                                                 @RequestParam String city2) {


            WeatherResponse weatherCity1 = weatherService.getWeather(city1);
            WeatherResponse weatherCity2 = weatherService.getWeather(city2);

            Map<String, Object> response = new HashMap<>();
            response.put("city1", weatherCity1);
            response.put("city2", weatherCity2);

            return ResponseEntity.ok(response);
    }
}
