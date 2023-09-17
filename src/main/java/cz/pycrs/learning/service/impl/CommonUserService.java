package cz.pycrs.learning.service.impl;

import cz.pycrs.learning.exception.UserRegistrationException;
import cz.pycrs.learning.entity.user.User;
import cz.pycrs.learning.exception.UserNotFoundException;
import cz.pycrs.learning.entity.user.dto.UserProfile;
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
    public List<UserProfile> getUsers() {
        return repository
                .findAll()
                .stream().map(UserProfile::new)
                .toList();
    }

    @Override
    public Optional<UserProfile> getUser(UUID id) {
        return repository
                .findById(id)
                .map(UserProfile::new);
    }

    @Override
    public User registerUser(UserRegistrationRequest registrationRequest) {
        if (repository.existsUserByEmail(registrationRequest.email()))
            throw new UserRegistrationException("Email address is already taken");
        return repository.save(new User(registrationRequest));
    }

    @Override
    public void deleteUser(UUID id) {
        if (repository.existsById(id)) repository.deleteById(id);
        else throw new UserNotFoundException(id);
    }
}
