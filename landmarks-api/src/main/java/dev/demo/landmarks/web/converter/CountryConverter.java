package dev.demo.landmarks.web.converter;

import dev.demo.landmarks.entity.Country;
import dev.demo.landmarks.web.dto.CountryResponse;

public class CountryConverter {

    public static CountryResponse toResponse(Country country) {
        CountryResponse response = new CountryResponse();
        response.setId(country.getId());
        response.setName(country.getName());
        return response;
    }
}
