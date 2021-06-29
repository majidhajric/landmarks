package dev.demo.landmarks.service;

import dev.demo.landmarks.entity.City;
import dev.demo.landmarks.entity.Importance;
import dev.demo.landmarks.entity.Landmark;
import dev.demo.landmarks.repository.CityRepository;
import dev.demo.landmarks.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LandmarkService {

    private final FileService fileService;

    private final LandmarkRepository landmarkRepository;

    private final CityRepository cityRepository;

    public Landmark createLandmark(UUID cityId, Landmark landmark, List<MultipartFile> files) {
        City city = cityRepository.findById(cityId).orElseThrow(EntityNotFoundException::new);

        UUID landmarkId = UUID.randomUUID();

        Set<String> fileNames = fileService.saveFiles(landmarkId.toString(), files);

        landmark.setId(landmarkId);
        landmark.setCity(city);
        landmark.setFileNames(fileNames);
        return landmarkRepository.save(landmark);
    }

    public List<Landmark> findByNameAndImportance(String name, Importance importance) {
        return landmarkRepository.findAllActiveByNameAndImportance(name, importance);
    }
}
