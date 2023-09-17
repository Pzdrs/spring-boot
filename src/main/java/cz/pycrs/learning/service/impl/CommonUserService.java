package cz.pycrs.learning.service.impl;

import cz.pycrs.learning.exception.UserRegistrationException;
import cz.pycrs.learning.entity.user.User;
import cz.pycrs.learning.entity.user.UserNotFoundException;
import cz.pycrs.learning.entity.user.dto.UserDTO;
import cz.pycrs.learning.payload.request.UserRegistrationRequest;
import cz.pycrs.learning.repository.UserRepository;
import cz.pycrs.learning.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommonUserService implements UserService {
    private final UserRepository repository;


    public CommonUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDTO> getUsers() {
        return repository
                .findAll()
                .stream().map(UserDTO::new)
                .toList();
    }

    @Override
    public Optional<UserDTO> getUser(UUID id) {
        return repository
                .findById(id)
                .map(UserDTO::new);
    }

    @Override
    public User registerUser(UserRegistrationRequest registrationRequest) {
        if (repository.existsUserByEmail(registrationRequest.email()))
            throw new UserRegistrationException("Email already exists");
        return repository.save(new User(registrationRequest));
    }

    @Override
    public void deleteUser(UUID id) {
        if (repository.existsById(id)) repository.deleteById(id);
        else throw new UserNotFoundException(id);
    }
}
