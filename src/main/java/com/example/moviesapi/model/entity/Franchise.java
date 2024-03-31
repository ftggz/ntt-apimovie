package com.example.moviesapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "franchises")
@Data
@NoArgsConstructor
public class Franchise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "genre_id")
  private Genre genre;

  @ManyToOne
  @JoinColumn(name = "studio_id")
  private Studio studio;

  @OneToMany(mappedBy = "franchise")
  private List<Movie> movies;
}
