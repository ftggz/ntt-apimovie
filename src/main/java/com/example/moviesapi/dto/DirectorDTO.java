package com.example.moviesapi.dto;

import java.util.List;
import com.example.moviesapi.model.entity.Director;
import com.example.moviesapi.model.entity.Movie;

public record DirectorDTO(Long id, String name, List<String> movies) {
  public Director toDirector() {
    Director director = new Director();
    director.setId(this.id());
    director.setName(this.name());

    List<Movie> directorMovieList = this.movies().stream()
        .map(title -> {
          Movie movie = new Movie();
          movie.setTitle(title);
          return movie;
        })
        .toList();
    director.setMovies(directorMovieList);

    return director;
  }
}
