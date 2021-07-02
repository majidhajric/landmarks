package dev.demo.landmarks.web.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class VoteRequest {

    @Min(1)
    @Max(5)
    private Integer score;
}
