package cz.pycrs.learning.exception;

import cz.pycrs.learning.exception.ItemNotFoundException;

import java.util.UUID;

public class UserNotFoundException extends ItemNotFoundException {
    public UserNotFoundException(UUID id) {
        super(String.format("User with id %s not found", id));
    }

    public UserNotFoundException(String email) {
        super(String.format("User with email %s not found", email));
    }

    public UserNotFoundException() {
        super("User not found");
    }
}
