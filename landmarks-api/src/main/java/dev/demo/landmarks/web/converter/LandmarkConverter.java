package dev.demo.landmarks.web.converter;

import dev.demo.landmarks.web.dto.LandmarkRequest;
import dev.demo.landmarks.web.dto.LandmarkResponse;
import dev.demo.landmarks.entity.Landmark;
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

        LandmarkResponse response = new LandmarkResponse();
        response.setId(landmark.getId());
        response.setCountry(landmark.getCity().getCountry().getName());
        response.setCity(landmark.getCity().getName());
        response.setName(landmark.getName());
        response.setDescription(landmark.getDescription());
        response.setActive(landmark.getActive());
        response.setImportance(landmark.getImportance());
        response.setScore(landmark.getAverageScore());
        response.setImages(landmark.getFileNames().stream().map(path -> baseURL + "/" + path).collect(Collectors.toSet()));
        return response;
    }
}
