package edu.phystech.weatherservice;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    private final WeatherService weatherService;

    public Controller(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public List<Double> getWeather(@RequestParam @Nullable String days, @RequestParam @Nullable String city) {
        int daysNum = days == null ? 1 : Integer.parseInt(days);
        if (city == null) {
            return weatherService.getWeatherHistory(daysNum);
        }
        return weatherService.getWeatherHistory(daysNum, city);
    }

    @GetMapping("/forecast")
    public double getForecast(@RequestParam @Nullable String city) {
        if (city == null) {
            return weatherService.getWeatherForecast();
        }
        return weatherService.getWeatherForecast(city);
    }
}
