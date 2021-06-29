package dev.demo.landmarks.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class FileService {


    @Value("${upload.directory}")
    private String uploadDirectory;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDirectory));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder!");
        }
    }

    public Set<String> saveFiles(String directoryName, List<MultipartFile> files) {
        Set<String> savedFiles = new HashSet<>();
        try {
            Path uploadPath = Paths.get(uploadDirectory, directoryName);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                for (MultipartFile file : files) {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    savedFiles.add(filePath.toString());
                }
            }
        } catch (IOException e) {
            log.error("Could not save file", e);
        }
        return savedFiles;
    }
}
