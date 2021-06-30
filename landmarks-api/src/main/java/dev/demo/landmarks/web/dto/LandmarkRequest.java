package dev.demo.landmarks.web.dto;

import dev.demo.landmarks.entity.Importance;
import lombok.Data;

import java.util.UUID;

@Data
public class LandmarkRequest {

    private UUID cityId;

    private String name;

    private String description;

    private Boolean active;

    private Importance importance;
}
