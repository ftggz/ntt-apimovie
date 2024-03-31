package com.example.moviesapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @NotBlank
  private String title;

  @ManyToOne
  @JoinColumn(name = "genre_id")
  private Genre genre;

  @ManyToOne
  @JoinColumn(name = "studio_id")
  private Studio studio;

  @ManyToOne
  @JoinColumn(name = "franchise_id")
  @JsonIgnore
  private Franchise franchise;

  @ManyToMany
  @JoinTable(name = "director_movie", joinColumns = @JoinColumn(name = "director_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
  private List<Director> directors;

  @ManyToMany(mappedBy = "movies")
  private List<Streaming> streaming;

  @ManyToMany
  @JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
  private List<Actor> actors;
}
