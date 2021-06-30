package dev.demo.landmarks.web.controller;

import dev.demo.landmarks.service.CityService;
import dev.demo.landmarks.web.converter.CityConverter;
import dev.demo.landmarks.web.dto.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityResponse> getCountries() {
        return cityService.getAllCities()
                .stream()
                .map(CityConverter::toResponse)
                .collect(Collectors.toList());
    }
}
