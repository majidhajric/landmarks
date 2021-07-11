package dev.demo.landmarks.web.controller;

import dev.demo.landmarks.service.CityService;
import dev.demo.landmarks.service.CountryService;
import dev.demo.landmarks.web.converter.CityConverter;
import dev.demo.landmarks.web.converter.CountryConverter;
import dev.demo.landmarks.web.dto.CityResponse;
import dev.demo.landmarks.web.dto.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PlaceController {

    private final CountryService countryService;

    private final CityService cityService;

    @GetMapping(path = "/countries")
    public List<CountryResponse> getCountries() {
        return countryService.getAllCountries()
                .stream()
                .map(CountryConverter::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/countries/{countryId}/cities")
    public List<CityResponse> getCities(@PathVariable UUID countryId) {
        return cityService.findAllCitiesByCountryId(countryId)
                .stream()
                .map(CityConverter::toResponse)
                .collect(Collectors.toList());
    }
}
