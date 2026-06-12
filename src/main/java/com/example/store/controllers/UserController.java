package com.example.store.controllers;

import com.example.store.dtos.UserDto;
import com.example.store.entities.User;
import com.example.store.mapper.UserMapper;
import com.example.store.repositories.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @GetMapping
  public List<UserDto> getUsers() {
    return userRepository.findAll().stream().map(userMapper::toDto).toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
    User user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(userMapper.toDto(user));
  }
}
