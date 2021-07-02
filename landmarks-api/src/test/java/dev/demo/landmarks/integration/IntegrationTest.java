package dev.demo.landmarks.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.demo.landmarks.entity.City;
import dev.demo.landmarks.entity.Importance;
import dev.demo.landmarks.entity.Landmark;
import dev.demo.landmarks.repository.CityRepository;
import dev.demo.landmarks.repository.LandmarkRepository;
import dev.demo.landmarks.web.dto.LandmarkRequest;
import dev.demo.landmarks.web.dto.LandmarkResponse;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest {

    @Value("${upload.directory}")
    private String uploadDirectory;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private LandmarkRepository landmarkRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    void whenCreateLandmark_thenLandmarkCreated() throws Exception {

        MockMultipartFile imageFile = new MockMultipartFile("file", "foo.jpg", "application/octet-stream", "content".getBytes(StandardCharsets.UTF_8));

        LandmarkRequest landmarkRequest = new LandmarkRequest();
        landmarkRequest.setCityId(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        landmarkRequest.setName("name");
        landmarkRequest.setDescription("description");
        landmarkRequest.setActive(true);
        landmarkRequest.setImportance(Importance.MEDIUM);
        String landmarkJson = mapper.writeValueAsString(landmarkRequest);

        MockMultipartFile jsonFile = new MockMultipartFile("landmark", "",
                "application/json", landmarkJson.getBytes());
        mockMvc.perform(multipart("/landmarks")
                .file(imageFile)
                .file(jsonFile)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());

        File dir = new File(uploadDirectory);
        FileUtils.deleteDirectory(dir);
    }

    @Test
    void whenGetLandmarks_thenLandmarksReturned() throws Exception {
        City city = cityRepository.getById(UUID.fromString("11111111-1111-1111-1111-111111111111"));

        Landmark landmark1 = new Landmark();
        landmark1.setName("one");
        landmark1.setDescription("one");
        landmark1.setImportance(Importance.LOW);
        landmark1.setActive(true);
        landmark1.setCity(city);
        landmark1 = landmarkRepository.save(landmark1);

        Landmark landmark2 = new Landmark();
        landmark2.setName("two");
        landmark2.setDescription("two");
        landmark2.setImportance(Importance.MEDIUM);
        landmark2.setActive(true);
        landmark2.setCity(city);
        landmark2 = landmarkRepository.save(landmark2);

        Landmark landmark3 = new Landmark();
        landmark3.setName("three");
        landmark3.setDescription("three");
        landmark3.setImportance(Importance.HIGH);
        landmark3.setActive(false);
        landmark3.setCity(city);
        landmark3 = landmarkRepository.save(landmark3);

        String jsonResponse = mockMvc.perform(get("/landmarks").param("name", "on"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<LandmarkResponse> responseList = mapper.readValue(jsonResponse, new TypeReference<List<LandmarkResponse>>() {});
        assertEquals(1, responseList.size());
        assertEquals("one",responseList.get(0).getName());

        jsonResponse = mockMvc.perform(get("/landmarks").param("importance", "MEDIUM"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        responseList = mapper.readValue(jsonResponse, new TypeReference<List<LandmarkResponse>>() {});
        assertEquals(1, responseList.size());
        assertEquals("two", responseList.get(0).getName());

        jsonResponse = mockMvc.perform(get("/landmarks").param("active", "false"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        responseList = mapper.readValue(jsonResponse, new TypeReference<List<LandmarkResponse>>() {});
        assertEquals(1, responseList.size());
        assertEquals("three", responseList.get(0).getName());
    }
}
