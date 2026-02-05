package com.attendanceApp.dtos;

import com.attendanceApp.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    @NotBlank
    @Email(message = "Invalid Email Format")
    private String username;

    @NotBlank
    @Size(min = 8, message = "must be length 8 or above")
    private String password;

    @NotBlank
    @Size(min = 3, max = 20, message = "must between 3-20")
    private String fullName;

}