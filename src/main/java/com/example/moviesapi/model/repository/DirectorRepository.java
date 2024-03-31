package com.example.moviesapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moviesapi.model.entity.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

}
