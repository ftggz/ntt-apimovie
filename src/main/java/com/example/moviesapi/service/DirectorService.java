package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.moviesapi.dto.DirectorDTO;
import com.example.moviesapi.model.entity.Director;
import com.example.moviesapi.model.repository.DirectorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class DirectorService {

  @Autowired
  DirectorRepository directorRepository;

  public List<DirectorDTO> getAllDirectors() {
    return directorRepository.findAll().stream()
        .map(director -> new DirectorDTO(
            director.getId(),
            director.getName(),
            director.getMovies().stream()
                .map(movie -> movie.getTitle())
                .toList()))
        .toList();
  }

  public DirectorDTO getDirectorById(@NotNull @Valid Long id) {
    @SuppressWarnings("null")
    Optional<Director> optionalDirector = directorRepository.findById(id);

    DirectorDTO result = new DirectorDTO(
        optionalDirector.get().getId(),
        optionalDirector.get().getName(),
        optionalDirector.get().getMovies()
            .stream()
            .map(movie -> movie.getTitle())
            .toList());

    return result;
  }

  public Director registerDirector(Director director) {
    if (director.getId() != null) {
      throw new RuntimeException("ID não deve ser informado");
    }
    return directorRepository.save(director);
  }

  public Director updateDirector(Director director) {
    @SuppressWarnings("null")
    Director directorToUpdate = directorRepository.findById(director.getId()).get();
    directorToUpdate.setName(director.getName());
    Director result = directorRepository.save(directorToUpdate);
    return result;
  }

  @SuppressWarnings("null")
  public String deleteDirector(@NotNull @Valid Long id) {
    Director directorToBeDeleted = getDirectorById(id).toDirector();
    String directorName = directorToBeDeleted.getName();
    directorRepository.deleteById(id);
    return "O diretor '" + directorName + "' de ID " + id + " foi excluído com sucesso!";
  }
}