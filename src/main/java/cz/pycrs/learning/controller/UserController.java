package cz.pycrs.learning.controller;

import cz.pycrs.learning.exception.UserRegistrationException;
import cz.pycrs.learning.entity.user.User;
import cz.pycrs.learning.payload.response.ErrorResponse;
import cz.pycrs.learning.payload.response.UserListResponse;
import cz.pycrs.learning.entity.user.dto.UserProfile;
import cz.pycrs.learning.payload.request.UserRegistrationRequest;
import cz.pycrs.learning.payload.response.UserRegistrationResponse;
import cz.pycrs.learning.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<UserListResponse> getUsers() {
        return ResponseEntity.ok(new UserListResponse(service.getUsers()));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> createUser(@RequestBody @Valid UserRegistrationRequest request) {
        try {
            User newUser = service.registerUser(request);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new UserRegistrationResponse(
                            true,
                            new UserProfile(newUser)
                    ));
        } catch (UserRegistrationException e) {
            return ResponseEntity.badRequest().body(new UserRegistrationResponse(
                    false,
                    e.getMessage()
            ));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        Optional<UserProfile> user = service.getUser(id);
        if (user.isEmpty())
            return ErrorResponse.notFound("User with id " + id + " not found");
        return ResponseEntity.ok(user.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
