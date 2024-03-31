package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesapi.model.entity.Movie;
import com.example.moviesapi.model.entity.Streaming;
import com.example.moviesapi.model.repository.StreamingRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class StreamingService {

  @Autowired
  StreamingRepository streamingRepository;

  @Autowired
  MovieService movieService;

  public List<Streaming> getAllStreamings() {
    return streamingRepository.findAll();
  }

  public Streaming getStreamingById(@NotNull @Valid Long id) {
    if (id == null) {
      return null;
    }

    Optional<Streaming> optionalStreaming = streamingRepository.findById(id);

    if (optionalStreaming.isEmpty()) {
      return null;
    }

    return optionalStreaming.get();
  }

  public Streaming registerStreaming(Streaming streaming) {
    if (streaming.getId != null) {
      return null;
    }
    return streamingRepository.save(streaming);
  }

  public Streaming updateStreaming(Streaming streaming) {
    if (streaming.getId() == null) {
      return null;
    }

    @SuppressWarnings("null")
    Streaming streamingToUpdate = streamingRepository.findById(streaming.getId()).get();
    streamingToUpdate.setName(streaming.getName());
    streamingToUpdate.setUrl(streaming.getUrl());
    Streaming result = streamingRepository.save(streamingToUpdate);
    return result;
  }

  @SuppressWarnings("null")
  public String deleteStreaming(@NotNull @Valid Long id) {
    Streaming streamingToBeDeleted = getStreamingById(id);
    if (streamingToBeDeleted == null) {
      return "Não foi possível excluir pois nada foi encontrado com o ID " + id;
    }
    String streamingName = streamingToBeDeleted.getName();
    streamingRepository.deleteById(id);
    return "O streaming '" + streamingName + "' de ID " + id + " foi excluído com sucesso!";
  }

  public String associateStreamingToMovie(
      @NotNull @Valid Long streaming_id,
      @NotNull @Valid Long movie_id) {
    Movie movie = movieService.getMovieById(movie_id)
        .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));
    Streaming streaming = getStreamingById(streaming_id);

    List<Movie> movieList = streaming.getMovies();
    movieList.add(movie);
    streaming.setMovies(movieList);
    streamingRepository.save(streaming);

    String result = String.format(
        "O filme '%s' foi adicionado ao streaming '%s'",
        movie.getTitle(), streaming.getName());

    return result;
  }

  public String removeMovieFromStreaming(
      @NotNull @Valid Long streaming_id,
      @NotNull @Valid Long movie_id) {
    Movie movie = movieService.getMovieById(movie_id)
        .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));
    Streaming streaming = getStreamingById(streaming_id);

    List<Movie> movieList = streaming.getMovies();
    movieList.remove(movie);
    streaming.setMovies(movieList);
    streamingRepository.save(streaming);

    String result = String.format(
        "O filme '%s' foi desassociado do streaming '%s'",
        movie.getTitle(), streaming.getName());

    return result;
  }

}
