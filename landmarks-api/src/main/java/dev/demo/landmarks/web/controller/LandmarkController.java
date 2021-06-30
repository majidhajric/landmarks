package dev.demo.landmarks.web.controller;

import dev.demo.landmarks.web.converter.LandmarkConverter;
import dev.demo.landmarks.web.dto.LandmarkRequest;
import dev.demo.landmarks.web.dto.LandmarkResponse;
import dev.demo.landmarks.entity.Landmark;
import dev.demo.landmarks.service.LandmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/landmarks")
public class LandmarkController {

    private final LandmarkService landmarkService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public LandmarkResponse createLandmark(@RequestPart(value = "landmark") LandmarkRequest landmarkRequest,
                                           @RequestPart(value = "file") List<MultipartFile> multipartFiles) {
        Landmark landmark = LandmarkConverter.fromRequest(landmarkRequest);
        landmark = landmarkService.createLandmark(landmarkRequest.getCityId(), landmark, multipartFiles);
        return LandmarkConverter.toResponse(landmark);
    }
}
