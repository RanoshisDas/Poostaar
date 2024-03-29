package com.ranoshisdas.poostaar.payload;

import com.ranoshisdas.poostaar.entity.ImageData;
import com.ranoshisdas.poostaar.util.ImageUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostDto {


    public PostDto(Integer postId, String addedDate, String title, String content, List<ImageData> imageData, UserDto user, CategoryDto category) {
        this.postId = postId;
        this.addedDate = addedDate;
        this.title = title;
        this.content = content;
        this.imageData = new ArrayList<>();
        for (ImageData image : imageData) {
            this.imageData.add(image.getName());
        }
        this.user = user;
        this.category = category;
    }

    private Integer postId;
    private String addedDate;

    @NotEmpty
    @NotNull
    @Size(min = 4, message = "Title must have minimum 4 Character")
    private String title;

    @NotNull
    private String content;



    private List<String> imageData;

    private UserDto user;

    private CategoryDto category;
}
