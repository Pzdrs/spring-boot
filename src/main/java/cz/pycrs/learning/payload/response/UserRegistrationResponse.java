package cz.pycrs.learning.payload.response;

import cz.pycrs.learning.entity.user.dto.UserDTO;

public record UserRegistrationResponse(
        boolean success,
        String message,
        UserDTO user
) {
    public UserRegistrationResponse(boolean success, UserDTO user) {
        this(success, "User registered successfully", user);
    }

    public UserRegistrationResponse(boolean success, String message) {
        this(success, message, null);
    }
}
