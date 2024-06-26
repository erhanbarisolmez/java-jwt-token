package com.security.jwttoken.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.jwttoken.dto.CreateUserRequest;
import com.security.jwttoken.model.User;
import com.security.jwttoken.repository.UserRepository;

@Service
public class UserService {

 
  private final UserRepository userRepository;

  private final BCryptPasswordEncoder passwordEncoder;
  
  public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<User> getByUserName(String username){
    return userRepository.findByUsername(username);
  }

  public void createUserIfNotExists(CreateUserRequest request){
    Optional<User> existingUser = userRepository.findByUsername(request.username());
    if (existingUser.isEmpty()) {
      createUser(request);
    }
  }

  public User createUser(CreateUserRequest request){
    User newUser = User.builder()
      .name(request.name())
      .username(request.username())
      .password(passwordEncoder.encode(request.password()))
      .authorities(request.authorities())
      .accountNonExpired(true)
      .credentialNonExpired(true)
      .isEnabled(true)
      .accountNonLocked(true)
      .isCredentialsNonExpired(true)
      .build();

      return userRepository.save(newUser);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }
  

}

