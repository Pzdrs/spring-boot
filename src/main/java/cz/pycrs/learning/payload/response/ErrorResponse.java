package cz.pycrs.learning.payload.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String message
) {
    public ErrorResponse(HttpStatusCode status, String message) {
        this(LocalDateTime.now(), status.value(), message);
    }

    public static ResponseEntity<ErrorResponse> create(HttpStatusCode status, String message) {
        return ResponseEntity.status(status).body(new ErrorResponse(status, message));
    }

    public static ResponseEntity<ErrorResponse> notFound(String message) {
        return create(HttpStatus.NOT_FOUND, message);
    }
}
