package dev.demo.landmarks.web.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CountryResponse {

    private UUID id;

    private String name;
}
