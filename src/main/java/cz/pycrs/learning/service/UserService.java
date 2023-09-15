package cz.pycrs.learning.service;

import cz.pycrs.learning.UserRegistrationException;
import cz.pycrs.learning.entity.user.User;
import cz.pycrs.learning.entity.user.dto.UserDTO;
import cz.pycrs.learning.payload.request.UserRegistrationRequest;
import cz.pycrs.learning.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserDTO> getUsers() {
        return repository
                .findAll()
                .stream().map(UserDTO::new)
                .toList();
    }

    public UserDTO getUser(UUID id) {
        return repository
                .findById(id)
                .map(UserDTO::new)
                .orElse(null);
    }

    public User registerUser(UserRegistrationRequest registrationRequest) {
        if (repository.existsUserByEmail(registrationRequest.email())) throw new UserRegistrationException("Email already exists");
        return repository.save(new User(registrationRequest));
    }
}
