package com.ranoshisdas.poostaar.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CategoryDto {
    private Integer CategoryId;
    private String course;

    @Pattern(regexp = "^[a-zA-Z ]{3,}$",
            message = "Name must have 3 Character with no special characters")
    private String CategoryTitle;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "^(19|20)\\d{2}-(19|20)\\d{2}$",
            message = "Enter admission year(ex:2021-2022)")
    private String categoryDescription;
}
