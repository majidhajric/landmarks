package dev.demo.landmarks.web.dto;

import dev.demo.landmarks.entity.Importance;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class LandmarkResponse {

    private UUID id;

    private String country;

    private String city;

    private String name;

    private String description;

    private Boolean active;

    private Importance importance;

    private Double score;

    private Set<String> images;
}
