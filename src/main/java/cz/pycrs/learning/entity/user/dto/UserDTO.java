package cz.pycrs.learning.entity.user.dto;

import cz.pycrs.learning.entity.user.User;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String username, String firstName, String lastName, String email,
        cz.pycrs.learning.entity.user.User.Gender gender,
        int age
) {
    public UserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getGender(), user.getAge());
    }
}
