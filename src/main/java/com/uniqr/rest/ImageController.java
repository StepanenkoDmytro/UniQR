package com.uniqr.rest;

import com.uniqr.model.Image;
import com.uniqr.repository.ImageRepository;
import com.uniqr.service.exception.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/images")
@CrossOrigin
public class ImageController {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageId(@PathVariable String id) throws IOException {

        Image image = imageRepository.findById(id).orElseThrow(
                () -> new ImageNotFoundException(String.format("Image with id = %d not found", id))
        );
        return ResponseEntity.ok()
                .header("fileName", image.getOriginFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

}
