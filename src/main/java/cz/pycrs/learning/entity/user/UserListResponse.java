package cz.pycrs.learning.entity.user;

import cz.pycrs.learning.entity.user.dto.UserDTO;

import java.util.List;

public record UserListResponse(
        int count,
        List<UserDTO> users
) {
    public UserListResponse(List<UserDTO> users) {
        this(users.size(), users);
    }
}
