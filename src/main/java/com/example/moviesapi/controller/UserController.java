package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.User;
import com.example.moviesapi.service.UserService;

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
@RequestMapping("user")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/list")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(userService.getUserById(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/save")
  public ResponseEntity<User> registerUser(@RequestBody User user) {
    try {
      return ResponseEntity.ok(userService.registerUser(user));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    try {
      return ResponseEntity.ok(userService.updateUser(user));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(userService.deleteUser(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PutMapping("/{user_id}/add_bookmark/{movie_id}")
  public ResponseEntity<String> addFavoriteMovie(
      @PathVariable Long user_id,
      @PathVariable Long movie_id) {
    try {
      return ResponseEntity.ok(userService.addFavoriteMovie(user_id, movie_id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PutMapping("/{user_id}/remove_bookmark/{movie_id}")
  public ResponseEntity<String> removeFavoriteMovie(
      @PathVariable Long user_id,
      @PathVariable Long movie_id) {
    try {
      return ResponseEntity.ok(userService.removeFavoriteMovie(user_id, movie_id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
