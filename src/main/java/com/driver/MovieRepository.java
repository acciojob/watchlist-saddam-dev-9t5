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
        this.movieList.put(name, movie);
        return "Movie added successfully";
    }

    public String addDirector(Director director) {
        String name = director.getName();
        director.setNumberOfMovies(0);
        this.directorList.put(name, director);
        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movie, String director) {
        if(!(this.movieList.containsKey(movie) && this.directorList.containsKey(director))) {
            return "movie OR director is not available in DB";
        }
        List<String> movies = new ArrayList<>();
        boolean isNewMovieAdded = true;
        if(this.movieDirectorPairList.containsKey(director)) {
            movies = this.movieDirectorPairList.get(director);
            if(movies.remove(movie)) isNewMovieAdded = false;
        }
        movies.add(movie);
        this.movieDirectorPairList.put(director, movies);

        // Increment director's number of movies
        if(isNewMovieAdded) {
            Director directorInfo = this.directorList.get(director);
            int numberOfMovie = directorInfo.getNumberOfMovies();
            directorInfo.setNumberOfMovies(numberOfMovie + 1);
            this.directorList.put(director, directorInfo);
        }
        return "Movie director pair added successfully";
    }

    public Movie getMovieByName(String name) {
        Movie movie = new Movie();
        if(this.movieList.containsKey(name)) {
            movie = this.movieList.get(name);
        }
        return movie;
    }

    public Director getDirectorByName(String name) {
        Director director = new Director();
        if(this.directorList.containsKey(name)) {
            director = this.directorList.get(name);
        }
        return director;
    }

    public List<String> getMoviesByDirectorName(String director) {
        List<String> movies = new ArrayList<>();
        if(this.movieDirectorPairList.containsKey(director)) {
            movies = this.movieDirectorPairList.get(director);
        }
        return movies;
    }

    public List<String> getAllMovies() {
        List<String> allMovies = new ArrayList<>();
        if(!this.movieList.isEmpty()) {
            allMovies.addAll(this.movieList.keySet());
        }
        return allMovies;
    }

    public String deleteDirectorByName(String director) {
        this.directorList.remove(director);
        if(this.movieDirectorPairList.containsKey(director)) {
            List<String> directorMovies = this.movieDirectorPairList.get(director);
            for (String directorMovie: directorMovies) {
                this.movieList.remove(directorMovie);
            }
            this.movieDirectorPairList.remove(director);
        }
        return "Director and their movies has been removed from DB";
    }

    public String deleteAllDirectors() {
        this.directorList.clear();
        this.movieList.clear();
        this.movieDirectorPairList.clear();
        return "All directors and movies has been deleted";
    }

}
