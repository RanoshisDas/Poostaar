package com.ranoshisdas.poostaar.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {
    private Integer Id;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z ]{4,}$",
            message = "Name must have 4 Character with no special characters")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,}$",
            message = "Password must have 4 Character and at least 1 uppercase," +
                    "lowercase,special character and 1 digit")
    private String password;

    @Email
    private String email;

    @NotEmpty
    @NotNull
    private String college;
}
