package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesapi.model.entity.Movie;
import com.example.moviesapi.model.entity.User;
import com.example.moviesapi.model.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  MovieService movieService;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(@NotNull @Valid Long id) {
    if (id == null) {
      return null;
    }

    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isEmpty()) {
      return null;
    }

    return optionalUser.get();
  }

  public User registerUser(User user) {
    if (user.getId() != null) {
      return null;
    }
    return userRepository.save(user);
  }

  public User updateUser(User user) {
    if (user.getId() == null) {
      return null;
    }

    @SuppressWarnings("null")
    User userToUpdate = userRepository.findById(user.getId()).get();
    userToUpdate.setName(user.getName());
    userToUpdate.setEmail(user.getEmail());
    userToUpdate.setUsername(user.getUsername());
    userToUpdate.setPassword(user.getPassword());
    User result = userRepository.save(userToUpdate);
    return result;
  }

  public String deleteUser(@NotNull @Valid Long id) {
    User userToBeDeleted = getUserById(id);
    if (userToBeDeleted == null) {
      return "Não foi possível excluir pois nada foi encontrado com o ID " + id;
    }
    String userName = userToBeDeleted.getName();
    userRepository.deleteById(id);
    return "O usuário '" + userName + "' de ID " + id + " foi excluído com sucesso!";
  }

  public String addFavoriteMovie(
      @NotNull @Valid Long userId,
      @NotNull @Valid Long movieId) {
    @SuppressWarnings("null")
    User user = userRepository.findById(userId).get();
    Movie movie = movieService.getMovieById(movieId).get();

    List<Movie> favoritesList = user.getFavorites();
    favoritesList.add(movie);
    user.setFavorites(favoritesList);
    userRepository.save(user);

    return String.format(
        "Filme '%s' adicionado aos favoritos de '%s'",
        movie.getTitle(), user.getUsername());
  }

  public String removeFavoriteMovie(
      @NotNull @Valid Long userId,
      @NotNull @Valid Long movieId) {
    @SuppressWarnings("null")
    User user = userRepository.findById(userId).get();
    Movie movie = movieService.getMovieById(movieId).get();
    user.getFavorites().remove(movie);
    userRepository.save(user);
    return String.format(
        "Filme '%s' removido dos favoritos de '%s'",
        movie.getTitle(), user.getUsername());
  }
}