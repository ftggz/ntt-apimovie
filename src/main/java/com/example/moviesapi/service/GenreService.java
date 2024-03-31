package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesapi.model.entity.Genre;
import com.example.moviesapi.model.repository.GenreRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class GenreService {

  @Autowired
  GenreRepository genreRepository;

  public List<Genre> getAllGenres() {
    return genreRepository.findAll();
  }

  public Genre getGenreById(@NotNull @Valid Long id) {
    if (id == null) {
      return null;
    }

    Optional<Genre> optionalGenre = genreRepository.findById(id);

    if (optionalGenre.isEmpty()) {
      return null;
    }

    return optionalGenre.get();
  }

  public Genre registerGenre(Genre genre) {
    if (genre.getId() != null) {
      return null;
    }
    return genreRepository.save(genre);
  }

  public Genre updateGenre(Genre genre) {
    if (genre.getId() == null) {
      return null;
    }

    @SuppressWarnings("null")
    Genre genreToUpdate = genreRepository.findById(genre.getId()).get();
    genreToUpdate.setGenreName(genre.getGenreName());
    Genre result = genreRepository.save(genreToUpdate);
    return result;
  }

  @SuppressWarnings("null")
  public String deleteGenre(@NotNull @Valid Long id) {
    Genre genreToBeDeleted = getGenreById(id);
    if (genreToBeDeleted == null) {
      return "Não foi possível excluir pois nada foi encontrado com o ID " + id;
    }
    String genreName = genreToBeDeleted.getGenreName();
    genreRepository.deleteById(id);
    return "O gênero '" + genreName + "' de ID " + id + " foi excluído com sucesso!";
  }
}
