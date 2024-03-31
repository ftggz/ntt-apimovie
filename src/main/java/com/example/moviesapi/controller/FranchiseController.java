package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Franchise;
import com.example.moviesapi.service.FranchiseService;

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
@RequestMapping("franchise")
public class FranchiseController {

  @Autowired
  FranchiseService franchiseService;

  @GetMapping("/list")
  public ResponseEntity<List<Franchise>> getAllFranchises() {
    return ResponseEntity.ok(franchiseService.getAllFranchises());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Franchise> getFranchiseById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(franchiseService.getFranchiseById(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/save")
  public ResponseEntity<Franchise> registerFranchise(@RequestBody Franchise franchise) {
    try {
      return ResponseEntity.ok(franchiseService.registerFranchise(franchise));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<Franchise> updateFranchise(@RequestBody Franchise franchise) {
    try {
      return ResponseEntity.ok(franchiseService.updateFranchise(franchise));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteFranchise(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(franchiseService.deleteFranchise(id));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
