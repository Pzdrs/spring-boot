package cz.pycrs.learning.entity.user;

import cz.pycrs.learning.entity.user.dto.UserDTO;
import cz.pycrs.learning.payload.request.UserRegistrationRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Function;


@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
        }
)
@Data
public class User {
    public enum Gender {
        MALE, FEMALE
    }

    @Id
    @GeneratedValue
    private UUID id;
    @Column(
            nullable = false
    )
    private String username, email;
    private String firstName, middleName, lastName;
    @Column(
            nullable = false
    )
    private LocalDate dateOfBirth;
    @Column(
            nullable = false
    )
    private String password;
    private Gender gender;

    protected User() {
    }

    public User(String username, String firstName, String middleName, String lastName, String email, LocalDate dateOfBirth, String password, Gender gender) {
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.gender = gender;
    }

    public User(UserRegistrationRequest req) {
        this(
                req.username(),
                req.firstName(),
                req.middleName(),
                req.lastName(),
                req.email(),
                req.dateOfBirth(),
                req.password(),
                req.gender()
        );
    }

    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
}
