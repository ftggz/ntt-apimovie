package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Studio;
import com.example.moviesapi.service.StudioService;

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
@RequestMapping("studio")
public class StudioController {

  @Autowired
  StudioService studioService;

  @GetMapping("/list")
  public ResponseEntity<List<Studio>> getAllStudios() {
    return ResponseEntity.ok(studioService.getAllStudios());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Studio> getStudioById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(studioService.getStudioById(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/save")
  public ResponseEntity<Studio> registerStudio(@RequestBody Studio studio) {
    try {
      return ResponseEntity.ok(studioService.registerStudio(studio));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<Studio> updateStudio(@RequestBody Studio studio) {
    try {
      return ResponseEntity.ok(studioService.updateStudio(studio));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStudio(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(studioService.deleteStudio(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
