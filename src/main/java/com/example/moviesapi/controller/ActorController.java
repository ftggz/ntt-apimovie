package com.example.moviesapi.controller;

import com.example.moviesapi.dto.ActorDTO;
import com.example.moviesapi.model.entity.Actor;
import com.example.moviesapi.service.ActorService;

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
@RequestMapping("actor")
public class ActorController {

  @Autowired
  ActorService actorService;

  @GetMapping("/list")
  public ResponseEntity<List<ActorDTO>> getAllActors() {
    return ResponseEntity.ok(actorService.getAllActors());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ActorDTO> getActorById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(actorService.getActorById(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/save")
  public ResponseEntity<Actor> registerActor(@RequestBody Actor actor) {
    try {
      return ResponseEntity.ok(actorService.registerActor(actor));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<Actor> updateActor(@RequestBody Actor actor) {
    try {
      return ResponseEntity.ok(actorService.updateActor(actor));

    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteActor(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(actorService.deleteActor(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
