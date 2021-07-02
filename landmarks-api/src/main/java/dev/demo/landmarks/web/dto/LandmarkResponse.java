package dev.demo.landmarks.web.dto;

import dev.demo.landmarks.entity.Importance;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class LandmarkResponse {

    private UUID id;

    private UUID countryId;

    private String countryName;

    private UUID cityId;

    private String cityName;

    private String name;

    private String description;

    private Boolean active;

    private Importance importance;

    private Double score;

    private Set<String> images;
}
