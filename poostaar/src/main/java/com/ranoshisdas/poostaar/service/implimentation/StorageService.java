package com.ranoshisdas.poostaar.service.implimentation;

import com.ranoshisdas.poostaar.config.AppConstant;
import com.ranoshisdas.poostaar.entity.ImageData;
import com.ranoshisdas.poostaar.entity.Post;
import com.ranoshisdas.poostaar.exception.ResourceNotFoundException;
import com.ranoshisdas.poostaar.repo.PostRepo;
import com.ranoshisdas.poostaar.repo.StorageRepository;
import com.ranoshisdas.poostaar.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;
    @Autowired
    private PostRepo postRepo;

    public String uploadImage(MultipartFile file, Integer postId) throws IOException {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", AppConstant.ID, postId));
        ImageData imageData=new ImageData();
        imageData.setPost(post);
        imageData.setName(file.getOriginalFilename());
        imageData.setType(file.getContentType());
        imageData.setImageData(ImageUtils.compressImage(file.getBytes()));
        this.repository.save(imageData);
        return "Image set in Post "+postId+"\nFile name:"+imageData.getName();
        //return imageData;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }
}
