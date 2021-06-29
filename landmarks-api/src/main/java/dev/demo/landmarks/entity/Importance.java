package dev.demo.landmarks.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Importance {

    @JsonProperty("Important")
    LOW,

    @JsonProperty("Very important")
    MEDIUM,

    @JsonProperty("Must see")
    HIGH
}
