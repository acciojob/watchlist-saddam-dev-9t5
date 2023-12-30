package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {
    MovieService movieService = new MovieService();
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        String response = movieService.addMovie(movie);
        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movie")
    public ResponseEntity<Map<String, Movie>> getAllMovie() {
        Map<String, Movie> allMovie = null;
        allMovie = movieService.getAllMovie();
        return new ResponseEntity<>(allMovie, HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        String response = movieService.addDirector(director);
        return new ResponseEntity<>("Director added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-all-director")
    public ResponseEntity<Map<String, Director>> getAllDirector() {
        Map<String, Director> allDirector = null;
        allDirector = movieService.getAllDirector();
        return new ResponseEntity<>(allDirector, HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie, @RequestParam String director) {
        String response = movieService.addMovieDirectorPair(movie, director);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movie-director-pair")
    public ResponseEntity<Map<String, List<String>>> getAllMovieDirectorPair() {
        Map<String, List<String>> allMovieDirectorPair = null;
        allMovieDirectorPair = movieService.getAllMovieDirectorPair();
        return new ResponseEntity<>(allMovieDirectorPair, HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name) {
        Movie movie = new Movie();
        movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name) {
        Director director = new Director();
        director = movieService.getDirectorByName(name);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director) {
        List<String> movies = null;
        movies = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> getAllMovies() {
        List<String> allMovies = null;
        allMovies = movieService.getAllMovies();
        return new ResponseEntity<>(allMovies, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director) {
        String response = movieService.deleteDirectorByName(director);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        String response = movieService.deleteAllDirectors();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
