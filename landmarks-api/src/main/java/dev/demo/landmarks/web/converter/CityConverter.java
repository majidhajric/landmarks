package dev.demo.landmarks.web.converter;

import dev.demo.landmarks.entity.City;
import dev.demo.landmarks.web.dto.CityResponse;

public class CityConverter {

    public static CityResponse toResponse(City city) {
        CityResponse response = new CityResponse();
        response.setId(city.getId());
        response.setName(city.getName());
        return response;
    }
}
