package com.ranoshisdas.poostaar.controller;

import com.ranoshisdas.poostaar.entity.ImageData;
import com.ranoshisdas.poostaar.payload.PostDto;
import com.ranoshisdas.poostaar.service.implimentation.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("poostaar/")
public class ImageController {
    @Autowired
    private StorageService service;

//Add a new image in post
    @PostMapping("post/{postId}/image")
    public ResponseEntity<?> uploadImage(@PathVariable Integer postId,
            @RequestParam("image") MultipartFile file) throws IOException {

      String uploadImage = service.uploadImage(file,postId);
        return ResponseEntity.ok(uploadImage);
    }
//get one image by its name
    @GetMapping("post/image/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){

        byte[] imageData=service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
//get all image by post id

//delete image by its name

}
