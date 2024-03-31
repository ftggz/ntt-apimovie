package com.example.moviesapi.controller;

import com.example.moviesapi.dto.DirectorDTO;
import com.example.moviesapi.model.entity.Director;
import com.example.moviesapi.service.DirectorService;

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
@RequestMapping("director")
public class DirectorController {

  @Autowired
  DirectorService directorService;

  @GetMapping("/list")
  public ResponseEntity<List<DirectorDTO>> getAllDirectors() {
    return ResponseEntity.ok(directorService.getAllDirectors());
  }

  @GetMapping("/{id}")
  public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(directorService.getDirectorById(id));

    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/save")
  public ResponseEntity<Director> registerDirector(@RequestBody Director director) {
    try {
      return ResponseEntity.ok(directorService.registerDirector(director));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<Director> updateDirector(@RequestBody Director director) {
    try {
      return ResponseEntity.ok(directorService.updateDirector(director));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteDirector(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(directorService.deleteDirector(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
