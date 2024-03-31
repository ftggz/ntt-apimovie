package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;
import com.example.moviesapi.model.entity.Actor;
import com.example.moviesapi.model.entity.Director;
import com.example.moviesapi.model.entity.Franchise;
import com.example.moviesapi.model.entity.Movie;
import com.example.moviesapi.model.repository.MovieRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  private MovieRepository movieRepository;
  private FranchiseService franchiseService;
  private ActorService actorService;
  private DirectorService directorService;

  // Pelo que li, quando temos um único contructor na camada, o autowired não
  // seria mais necessário a partir da versão 3. alguma coisa do Spring...
  // Mas estou mantendo aqui porque não dá pra confiar em tudo o que
  // a gente lê na internet, né? XD
  @Autowired
  public MovieService(
      MovieRepository movieRepository,
      FranchiseService franchiseService,
      ActorService actorService,
      DirectorService directorService) {
    this.movieRepository = movieRepository;
    this.franchiseService = franchiseService;
    this.actorService = actorService;
    this.directorService = directorService;
  }

  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  public Optional<Movie> getMovieById(@NotNull @Valid Long id) {
    @SuppressWarnings("null")
    Optional<Movie> optionalMovie = movieRepository.findById(id);
    if (optionalMovie.isEmpty()) {
      return optionalMovie;
    }
    return Optional.of(optionalMovie).get();
  }

  public List<Movie> getMovieListByTitle(String title) {
    List<Movie> movieList = movieRepository.findMoviesByTitle(title);
    if (movieList.size() == 0) {
      throw new RuntimeException(
          "Nada foi encontrado com o termo '" + title + "'");
    }
    return movieList;
  }

  public Movie registerMovie(Movie movie) {
    if (movie.getId() != null) {
      throw new RuntimeException("ID não deve ser informado");
    }
    return movieRepository.save(movie);
  }

  public Movie updateMovie(Movie movie) {
    @SuppressWarnings("null")
    Movie movieToUpdate = movieRepository.findById(movie.getId()).get();
    movieToUpdate.setTitle(movie.getTitle());
    Movie result = movieRepository.save(movieToUpdate);
    return result;
  }

  @SuppressWarnings("null")
  public String deleteMovie(@NotNull @Valid Long id) {
    movieRepository.deleteById(id);
    return "Filme excluído do banco de dados";
  }

  public Movie associateMovieToFranchise(
      @NotNull @Valid Long movie_id,
      @NotNull @Valid Long franchise_id) {
    Movie movie = getMovieById(movie_id).get();
    Franchise franchise = franchiseService.getFranchiseById(franchise_id);
    movie.setFranchise(franchise);
    Movie result = movieRepository.save(movie);
    return result;
  }

  public String addActorToMovie(
      @NotNull @Valid Long movie_id,
      @NotNull @Valid Long actor_id) {
    Movie movie = getMovieById(movie_id)
        .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));
    Actor actor = actorService.getActorById(actor_id).toActor();

    List<Actor> actorList = movie.getActors();
    actorList.add(actor);
    movie.setActors(actorList);
    movieRepository.save(movie);

    String result = String.format(
        "O ator '%s' foi incluído no filme '%s'",
        actor.getName(), movie.getTitle());

    return result;
  }

  public String addDirectorToMovie(
      @NotNull @Valid Long movie_id,
      @NotNull @Valid Long director_id) {
    Movie movie = getMovieById(movie_id)
        .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));
    Director director = directorService.getDirectorById(director_id).toDirector();

    List<Director> directorList = movie.getDirectors();
    directorList.add(director);
    movie.setDirectors(directorList);
    movieRepository.save(movie);

    String result = String.format(
        "O diretor '%s' foi incluído no filme '%s'",
        director.getName(), movie.getTitle());

    return result;
  }
}
