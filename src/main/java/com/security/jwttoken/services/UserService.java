package com.security.jwttoken.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.security.jwttoken.model.User;
import com.security.jwttoken.repository.UserRepository;

public class UserService {

 
  private final UserRepository userRepository;

  private final BCryptPasswordEncoder passwordEncoder;
  
  public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<User> getByUserName(String username){
    return userRepository.findfindByUsername(username);
  }
  
}
