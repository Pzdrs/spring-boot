package cz.pycrs.learning.service;

import cz.pycrs.learning.entity.user.User;
import cz.pycrs.learning.entity.user.dto.UserProfile;
import cz.pycrs.learning.payload.request.UserRegistrationRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserProfile> getUsers();

    Optional<UserProfile> getUser(UUID id);

    User registerUser(UserRegistrationRequest registrationRequest);

    void deleteUser(UUID id);
}
