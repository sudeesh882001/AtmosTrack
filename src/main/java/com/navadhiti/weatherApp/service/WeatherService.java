package com.navadhiti.weatherApp.service;

import com.navadhiti.weatherApp.response.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class WeatherService {
    private static final String API_KEY = "9fb89b00dbda7c10975b2ec87d8bf3fb";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherResponse getWeather(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(WEATHER_URL)
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric") // for Celsius
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        // Deserialize the response JSON into a WeatherResponse object
        return restTemplate.getForObject(url, WeatherResponse.class);
    }

    public String compareWeather(String city1, String city2) {
        WeatherResponse weatherCity1 = getWeather(city1);
        WeatherResponse weatherCity2 = getWeather(city2);
        return "{ \"city1\": " + weatherCity1 + ", \"city2\": " + weatherCity2 + " }";
    }
}
