package com.example.moviesapi.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "streamings")
@Data
@NoArgsConstructor
public class Streaming {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @NotNull
  private String name;

  @NotBlank
  @NotNull
  private String url;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
    name = "streaming_movie",
    joinColumns = @JoinColumn(name = "streaming_id"),
    inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
  private List<Movie> movies;
}
