package br.com.tiago.uploaddemo.controllers;

import br.com.tiago.uploaddemo.providers.upload.IStorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FilesController {

    @Autowired
    private IStorageProvider storageProvider;

    @PostMapping("/files/upload")
    public ResponseEntity<String> upload(MultipartFile image){
        try {
            String url = storageProvider.save(image.getOriginalFilename(), image.getBytes());
            return ResponseEntity.status(HttpStatus.CREATED).body(url);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not save image");
        }
    }
}
