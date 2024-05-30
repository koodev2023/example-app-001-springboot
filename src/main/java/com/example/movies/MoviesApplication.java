package com.example.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MoviesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesApplication.class, args);
    }

    @GetMapping("/")
    public String apiRoot() {

        return """
                Hello world, this is my first Spring Boot application.
                Below are apis you can try.
                / (Home)
                /api/v1/movies (Get All Movies)
                /api/v1/movies/{imdbId} (Get Single Movie by imdbId)
                """;

    }

}
