package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesapi.model.entity.Franchise;
import com.example.moviesapi.model.repository.FranchiseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class FranchiseService {

  @Autowired
  FranchiseRepository franchiseRepository;

  public List<Franchise> getAllFranchises() {
    return franchiseRepository.findAll();
  }

  public Franchise getFranchiseById(@NotNull @Valid Long id) {
    if (id == null) {
      return null;
    }

    Optional<Franchise> optionalFranchise = franchiseRepository.findById(id);

    if (optionalFranchise.isEmpty()) {
      return null;
    }

    return optionalFranchise.get();
  }

  public Franchise registerFranchise(Franchise franchise) {
    if (franchise.getId() != null) {
      return null;
    }
    return franchiseRepository.save(franchise);
  }

  public Franchise updateFranchise(Franchise franchise) {
    if (franchise.getId() == null) {
      return null;
    }

    @SuppressWarnings("null")
    Franchise franchiseToUpdate = franchiseRepository.findById(franchise.getId()).get();
    franchiseToUpdate.setName(franchise.getName());
    Franchise result = franchiseRepository.save(franchiseToUpdate);
    return result;
  }

  @SuppressWarnings("null")
  public String deleteFranchise(@NotNull @Valid Long id) {
    Franchise franchiseToBeDeleted = getFranchiseById(id);
    if (franchiseToBeDeleted == null) {
      return "Não foi possível excluir pois nada foi encontrado com o ID " + id;
    }
    String franchiseName = franchiseToBeDeleted.getName();
    franchiseRepository.deleteById(id);
    return "A franquia '" + franchiseName + "' de ID " + id + " foi excluído com sucesso!";
  }
}