package cz.pycrs.learning.payload.response;

import cz.pycrs.learning.entity.user.dto.UserProfile;

import java.util.List;

public record UserListResponse(
        int count,
        List<UserProfile> users
) {
    public UserListResponse(List<UserProfile> users) {
        this(users.size(), users);
    }
}
