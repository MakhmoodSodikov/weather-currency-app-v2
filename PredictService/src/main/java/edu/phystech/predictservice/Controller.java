package edu.phystech.predictservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final PredictService predictService;

    public Controller(PredictService predictService) {
        this.predictService = predictService;
    }

    @GetMapping("/predict")
    public Double predictCurrency() {
        return predictService.predict();
    }
}
