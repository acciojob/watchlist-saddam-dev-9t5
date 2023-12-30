package com.driver;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Service
public class MovieService {
    MovieRepository movieRepository = new MovieRepository();
    public String addMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }

    public Map<String, Movie> getAllMovie() {
        return movieRepository.getAllMovie();
    }

    public String addDirector(Director director) {
        return movieRepository.addDirector(director);
    }

    public Map<String, Director> getAllDirector() {
        return movieRepository.getAllDirector();
    }
    public String addMovieDirectorPair(String movie, String director) {
        return movieRepository.addMovieDirectorPair(movie, director);
    }

    public Map<String, List<String>> getAllMovieDirectorPair() {
        return movieRepository.getAllMovieDirectorPair();
    }

    public Movie getMovieByName(String name) {
        return movieRepository.getMovieByName(name);
    }

    public Director getDirectorByName(String name) {
        return movieRepository.getDirectorByName(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        return movieRepository.getMoviesByDirectorName(director);
    }

    public List<String> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public String deleteDirectorByName(String director) {
        return movieRepository.deleteDirectorByName(director);
    }

    public String deleteAllDirectors() {
        return movieRepository.deleteAllDirectors();
    }
}
