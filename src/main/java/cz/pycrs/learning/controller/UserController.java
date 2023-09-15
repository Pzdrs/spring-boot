package cz.pycrs.learning.controller;

import cz.pycrs.learning.UserRegistrationException;
import cz.pycrs.learning.entity.user.User;
import cz.pycrs.learning.entity.user.UserListResponse;
import cz.pycrs.learning.entity.user.dto.UserDTO;
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

    @PostMapping
    public ResponseEntity<UserRegistrationResponse> createUser(@RequestBody @Valid UserRegistrationRequest request) {
        try {
            User newUser = service.registerUser(request);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new UserRegistrationResponse(
                            true,
                            new UserDTO(newUser)
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
        UserDTO user = service.getUser(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(user);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
