package edu.phystech.predictservice;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class PredictService {

    private final SimpleRegression regression;
    private final static int DATASET_SIZE = 7;
    private final static String weatherUrl = "http://weatherservice:8081/weather?n=" + DATASET_SIZE;
    private final static String currencyUrl = "http://currencyservice:8082/currency?n=" + DATASET_SIZE;
    private final static String forecastUrl = "http://weatherservice:8081/forecast";
    private final RestTemplate restTemplate;

    public PredictService(RestTemplateBuilder restTemplateBuilder)  {
        restTemplate = restTemplateBuilder.build();
        regression = new SimpleRegression();
        fit();
    }

    public double predict() {
        ResponseEntity<Double> forecastResponse = restTemplate.getForEntity(forecastUrl, Double.class);
        return predict(forecastResponse.getBody());
    }

    private void fit() {
        ResponseEntity<double[]> weatherResponse = restTemplate.getForEntity(weatherUrl, double[].class);
        ResponseEntity<double[]> currencyResponse = restTemplate.getForEntity(currencyUrl, double[].class);

        if (weatherResponse.hasBody() && currencyResponse.hasBody()) {
            for (int i = 0; i < DATASET_SIZE; ++i) {
                regression.addData(weatherResponse.getBody()[i], currencyResponse.getBody()[i]);
            }
        } else {
            if (!weatherResponse.hasBody()) {
                System.out.println("Null weather response");
            }
            if (!currencyResponse.hasBody()) {
                System.out.println("Null currency response");
            }
        }
    }

    private Double predict(Double avgTemperature) {
        return regression.predict(avgTemperature);
    }

    public static void main(String[] args) {
        SpringApplication.run(PredictService.class, args);
    }

}
