package dev.demo.landmarks.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.demo.landmarks.entity.Importance;
import dev.demo.landmarks.web.dto.LandmarkRequest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebTest {

    @Value("${upload.directory}")
    private String uploadDirectory;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

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
    }

    @AfterEach
    void cleanUp() throws IOException {
        File dir = new File(uploadDirectory);
        FileUtils.deleteDirectory(dir);
    }
}
