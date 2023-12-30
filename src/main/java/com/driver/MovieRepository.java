package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Repository
public class MovieRepository {
    Map<String, Movie> movieList = new HashMap<>();
    Map<String, Director> directorList = new HashMap<>();
    Map<String, List<String>> movieDirectorPairList = new HashMap<>();

    public String addMovie(Movie movie) {
        String name = movie.getName();
        movieList.put(name, movie);
        return "Movie added successfully";
    }

    public String addDirector(Director director) {
        String name = director.getName();
        directorList.put(name, director);
        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movie, String director) {
        if(!(movieList.containsKey(movie) && directorList.containsKey(director))) {
            return "movie OR director is not available in DB";
        }
        List<String> movies = new ArrayList<>();
        boolean isNewMovieAdded = true;
        if(movieDirectorPairList.containsKey(director)) {
            movies = movieDirectorPairList.get(director);
            if(movies.remove(movie)) isNewMovieAdded = false;
        }
        movies.add(movie);
        movieDirectorPairList.put(director, movies);

        // Increment director's number of movies
        if(isNewMovieAdded) {
            Director directorInfo = directorList.get(director);
            int numberOfMovie = directorInfo.getNumberOfMovies();
            directorInfo.setNumberOfMovies(numberOfMovie + 1);
            directorList.put(director, directorInfo);
        }
        return "Movie director pair added successfully";
    }

    public Movie getMovieByName(String name) {
        Movie movie = new Movie();
        if(movieList.containsKey(name)) {
            movie = movieList.get(name);
        }
        return movie;
    }

    public Director getDirectorByName(String name) {
        Director director = new Director();
        if(directorList.containsKey(name)) {
            director = directorList.get(name);
        }
        return director;
    }

    public List<String> getMoviesByDirectorName(String director) {
        List<String> movies = new ArrayList<>();
        if(movieDirectorPairList.containsKey(director)) {
            movies = movieDirectorPairList.get(director);
        }
        return movies;
    }

    public List<String> getAllMovies() {
        List<String> allMovies = new ArrayList<>();
        if(!movieList.isEmpty()) {
            allMovies.addAll(movieList.keySet());
        }
        return allMovies;
    }

    public String deleteDirectorByName(String director) {
        directorList.remove(director);
        if(movieDirectorPairList.containsKey(director)) {
            List<String> directorMovies = movieDirectorPairList.get(director);
            for (String directorMovie: directorMovies) {
                movieList.remove(directorMovie);
            }
            movieDirectorPairList.remove(director);
        }
        return "Director and their movies has been removed from DB";
    }

    public String deleteAllDirectors() {
        directorList.clear();
        movieList.clear();
        movieDirectorPairList.clear();
        return "All directors and movies has been deleted";
    }

}
