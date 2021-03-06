package dev.demo.landmarks.service;

import dev.demo.landmarks.entity.City;
import dev.demo.landmarks.entity.Importance;
import dev.demo.landmarks.entity.Landmark;
import dev.demo.landmarks.entity.Vote;
import dev.demo.landmarks.repository.CityRepository;
import dev.demo.landmarks.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LandmarkService {

    private final FileService fileService;

    private final LandmarkRepository landmarkRepository;

    private final CityRepository cityRepository;

    private final Specifications specifications;

    public Landmark createLandmark(UUID cityId, Landmark landmark, List<MultipartFile> files) {
        City city = cityRepository.findById(cityId).orElseThrow(EntityNotFoundException::new);

        UUID landmarkId = UUID.randomUUID();

        Set<String> fileNames = fileService.saveFiles(landmarkId.toString(), files);

        landmark.setId(landmarkId);
        landmark.setCity(city);
        landmark.setFileNames(fileNames);
        return landmarkRepository.save(landmark);
    }

    public List<Landmark> getLandmarks(String name, Importance importance, Boolean active) {
        Specification<Landmark> specification = specifications.getLandmarkSpecification(name, importance, active);
        return landmarkRepository.findAll(specification);
    }

    public Landmark createVote(UUID id, Vote vote) {
        Landmark landmark = landmarkRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        landmark.addVote(vote);
        return landmarkRepository.save(landmark);
    }

    public Landmark getLandmark(UUID id) {
        return landmarkRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteLandmark(UUID id) throws IOException {
        landmarkRepository.deleteById(id);
        fileService.deleteDirectory(id.toString());
    }

    public Landmark updateLandmark(UUID landmarkId, UUID cityId, Landmark updateLandmark, List<MultipartFile> additionalFiles) {
        Landmark landmark = getLandmark(landmarkId);
        City city = cityRepository.findById(cityId).orElseThrow(EntityNotFoundException::new);

        landmark.setCity(city);
        landmark.setName(updateLandmark.getName());
        landmark.setDescription(updateLandmark.getDescription());
        landmark.setImportance(updateLandmark.getImportance());
        landmark.setActive(updateLandmark.getActive());

        if (additionalFiles != null) {
            Set<String> fileNames = fileService.saveFiles(landmarkId.toString(), additionalFiles);
            landmark.getFileNames().addAll(fileNames);
        }
        return landmarkRepository.save(landmark);
    }
}
