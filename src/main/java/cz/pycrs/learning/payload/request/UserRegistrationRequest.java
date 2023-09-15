package cz.pycrs.learning.payload.request;

import cz.pycrs.learning.entity.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UserRegistrationRequest(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Email is required")
        @Email(message = "Email is not valid")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        @NotNull(message = "Date of birth is required")
        @Past(message = "Date of birth is not valid")
        LocalDate dateOfBirth,
        User.Gender gender,
        String firstName, String middleName, String lastName
) {
}
