package cz.pycrs.learning;

import cz.pycrs.learning.entity.user.User;
import cz.pycrs.learning.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class LearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            userRepository.saveAllAndFlush(List.of(
                    new User(
                            "pycrs", "Petr", null, "Bohac", "pycrs@gmail.com", LocalDate.of(2002, Month.APRIL, 23), "mypass2", User.Gender.MALE
                    ),
                    new User(
                            "pycrs", "Petr", null, "Bohac", "pycrs2@gmail.com", LocalDate.of(2005, Month.APRIL, 23), "mypass2", User.Gender.MALE
                    ))
            );
        };
    }
}
