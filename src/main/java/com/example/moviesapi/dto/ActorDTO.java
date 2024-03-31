package com.example.moviesapi.dto;

import java.util.List;
import com.example.moviesapi.model.entity.Actor;
import com.example.moviesapi.model.entity.Movie;

public record ActorDTO(Long id, String name, List<String> movies) {
  public Actor toActor() {
    Actor actor = new Actor();
    actor.setId(this.id());
    actor.setName(this.name());

    List<Movie> actorMovieList = this.movies().stream()
        .map(title -> {
          Movie movie = new Movie();
          movie.setTitle(title);
          return movie;
        })
        .toList();
    actor.setMovies(actorMovieList);

    return actor;
  }
}
