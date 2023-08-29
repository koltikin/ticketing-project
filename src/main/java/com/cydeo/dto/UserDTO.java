package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank
    @Size(max = 15,min = 2)
    private String firstName,lastName;

    @NotBlank(message = "this filed is required")
    @Email(message = "enter a valid email address")
    private String userName;

    @NotBlank(message = "password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters long and contain both letters and digits")
    private String password;
    private boolean enabled;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{11}$", message = "Invalid phone number format")
    private String phone;
    @NotNull(message = "role is required")
    private RoleDTO role;
    @NotNull(message = "choose a gender")
    private Gender gender;
}
