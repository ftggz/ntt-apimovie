package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Genre;
import com.example.moviesapi.service.GenreService;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Hidden
@RequestMapping("genre")
public class GenreController {

  @Autowired
  GenreService genreService;

  @GetMapping("/list")
  public ResponseEntity<List<Genre>> getAllGenres() {
    return ResponseEntity.ok(genreService.getAllGenres());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(genreService.getGenreById(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/save")
  public ResponseEntity<Genre> registerGenre(@RequestBody Genre genre) {
    try {
      return ResponseEntity.ok(genreService.registerGenre(genre));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<Genre> updateGenre(@RequestBody Genre genre) {
    try {
      return ResponseEntity.ok(genreService.updateGenre(genre));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteGenre(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(genreService.deleteGenre(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
