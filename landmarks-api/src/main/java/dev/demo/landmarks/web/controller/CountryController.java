package dev.demo.landmarks.web.controller;

import dev.demo.landmarks.service.CountryService;
import dev.demo.landmarks.web.converter.CountryConverter;
import dev.demo.landmarks.web.dto.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<CountryResponse> getCountries() {
        return countryService.getAllCountries()
                .stream()
                .map(CountryConverter::toResponse)
                .collect(Collectors.toList());
    }
}
