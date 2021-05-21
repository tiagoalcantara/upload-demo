package br.com.tiago.uploaddemo.providers.upload;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class LocalStorageProvider implements IStorageProvider{

    @Override
    public String save(String fileName, byte[] file) {
        String generatedFileName = String.format("%s-%s", UUID.randomUUID(), fileName);
        Path dirPath = Paths.get("tmp", "uploads");
        Path filePath = dirPath.resolve(generatedFileName);

        try {
            Files.createDirectories(dirPath);
            Files.write(filePath, file);
        } catch (IOException e) {
            throw new RuntimeException("Could not upload file.");
        }

        return String.format("http://localhost:8080/files/images/%s", generatedFileName);
    }
}
