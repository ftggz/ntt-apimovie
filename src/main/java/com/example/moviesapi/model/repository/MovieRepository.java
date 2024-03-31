package com.example.moviesapi.model.repository;

import com.example.moviesapi.model.entity.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
  @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE CONCAT('%', LOWER(:title), '%')")
  List<Movie> findMoviesByTitle(@Param("title") String title);
}