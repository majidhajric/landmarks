package dev.demo.landmarks.service;

import dev.demo.landmarks.entity.City;
import dev.demo.landmarks.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CityService {

    private final CityRepository cityRepository;

    public List<City> findAllCitiesByCountryId(UUID countryId) {
        return cityRepository.findAllByCountryId(countryId);
    }
}
