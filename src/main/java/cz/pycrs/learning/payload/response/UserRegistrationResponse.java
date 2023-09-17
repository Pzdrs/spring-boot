package cz.pycrs.learning.payload.response;

import cz.pycrs.learning.entity.user.dto.UserProfile;

public record UserRegistrationResponse(
        boolean success,
        String message,
        UserProfile user
) {
    public UserRegistrationResponse(boolean success, UserProfile user) {
        this(success, "User registered successfully", user);
    }

    public UserRegistrationResponse(boolean success, String message) {
        this(success, message, null);
    }
}
