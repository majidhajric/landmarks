package dev.demo.landmarks.service;

import dev.demo.landmarks.entity.City;
import dev.demo.landmarks.entity.Landmark;
import dev.demo.landmarks.repository.CityRepository;
import dev.demo.landmarks.repository.LandmarkRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class LandmarkServiceTest {

    @Autowired
    LandmarkService landmarkService;

    @MockBean
    FileService fileService;

    @MockBean
    CityRepository cityRepository;

    @MockBean
    LandmarkRepository landmarkRepository;

    @Test
    void whenCreateLandmark_thenLandmarkCreated() {
        UUID cityId = UUID.randomUUID();
        City city = new City();
        city.setId(cityId);

        Mockito.when(cityRepository.findById(any(UUID.class))).thenReturn(Optional.of(city));

        Mockito.when(landmarkRepository.save(any(Landmark.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MockMultipartFile imageFile = new MockMultipartFile("file", "image.jpg", "application/octet-stream", "content".getBytes(StandardCharsets.UTF_8));
        List<MultipartFile> files = Collections.singletonList(imageFile);

        Mockito.when(fileService.saveFiles(anyString(), any())).thenAnswer(invocation -> {
            String directory = invocation.getArgument(0);
            List<MultipartFile> fileList = invocation.getArgument(1);
            return fileList.stream().map(f -> directory + File.separator + f.getOriginalFilename()).collect(Collectors.toSet());
        });

        Landmark request = new Landmark();
        request.setName("name");

        Landmark landmark = landmarkService.createLandmark(cityId, request, files);
        assertNotNull(landmark.getId());
        assertEquals(cityId, landmark.getCity().getId());
        assertEquals(1, landmark.getFileNames().size());
    }
}
