package dev.demo.landmarks.web.controller;

import dev.demo.landmarks.entity.Importance;
import dev.demo.landmarks.entity.Landmark;
import dev.demo.landmarks.entity.Vote;
import dev.demo.landmarks.service.LandmarkService;
import dev.demo.landmarks.web.converter.LandmarkConverter;
import dev.demo.landmarks.web.converter.VoteConverter;
import dev.demo.landmarks.web.dto.LandmarkRequest;
import dev.demo.landmarks.web.dto.LandmarkResponse;
import dev.demo.landmarks.web.dto.VoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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


    // TODO: return Page instead of List
    @GetMapping
    public List<LandmarkResponse> getLandmarks(@RequestParam(name = "name", required = false) String name,
                                               @RequestParam(name = "importance", required = false) Importance importance,
                                               @RequestParam(name = "active", defaultValue = "true") Boolean active) {

        return landmarkService.getLandmarks(name, importance, active)
                .stream()
                .map(LandmarkConverter::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public LandmarkResponse getLandmark(@PathVariable("id") UUID landmarkId) {

        Landmark landmark = landmarkService.getLandmark(landmarkId);
        return LandmarkConverter.toResponse(landmark);
    }

    @PutMapping(path = "/{id}")
    public LandmarkResponse updateLandmark(@PathVariable("id") UUID landmarkId,
                                           @RequestPart(value = "landmark") LandmarkRequest landmarkRequest,
                                           @RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles) {

        Landmark landmark = LandmarkConverter.fromRequest(landmarkRequest);
        UUID cityId = landmarkRequest.getCityId();
        landmark = landmarkService.updateLandmark(landmarkId, cityId, landmark, multipartFiles);
        return LandmarkConverter.toResponse(landmark);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLandmark(@PathVariable("id") UUID landmarkId) throws IOException {

        landmarkService.deleteLandmark(landmarkId);
    }

    @PostMapping(path = "/{id}/votes")
    @ResponseStatus(HttpStatus.CREATED)
    public LandmarkResponse createVote(@PathVariable("id") UUID landmarkId, @Valid @RequestBody VoteRequest voteRequest) {

        Vote vote = VoteConverter.fromRequest(voteRequest);
        Landmark landmark = landmarkService.createVote(landmarkId, vote);
        return LandmarkConverter.toResponse(landmark);
    }
}
