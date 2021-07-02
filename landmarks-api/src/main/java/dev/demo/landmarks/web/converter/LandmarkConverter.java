package dev.demo.landmarks.web.converter;

import dev.demo.landmarks.entity.City;
import dev.demo.landmarks.entity.Country;
import dev.demo.landmarks.entity.Landmark;
import dev.demo.landmarks.web.dto.LandmarkRequest;
import dev.demo.landmarks.web.dto.LandmarkResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.stream.Collectors;

public class LandmarkConverter {

    public static Landmark fromRequest(LandmarkRequest request) {
        Landmark landmark = new Landmark();
        landmark.setName(request.getName());
        landmark.setDescription(request.getDescription());
        landmark.setActive(request.getActive());
        landmark.setImportance(request.getImportance());
        return landmark;
    }

    public static LandmarkResponse toResponse(Landmark landmark) {
        String baseURL = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(null)
                .build()
                .toUriString();

        City city = landmark.getCity();
        Country country = city.getCountry();

        LandmarkResponse response = new LandmarkResponse();
        response.setId(landmark.getId());
        response.setCountryId(country.getId());
        response.setCountryName(country.getName());
        response.setCityId(city.getId());
        response.setCityName(city.getName());
        response.setName(landmark.getName());
        response.setDescription(landmark.getDescription());
        response.setActive(landmark.getActive());
        response.setImportance(landmark.getImportance());
        response.setScore(landmark.getAverageScore());
        response.setImages(landmark.getFileNames().stream().map(path -> baseURL + "/" + path).collect(Collectors.toSet()));
        return response;
    }
}
