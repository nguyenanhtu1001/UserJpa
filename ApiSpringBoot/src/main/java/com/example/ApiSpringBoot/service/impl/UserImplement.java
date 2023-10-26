package com.example.ApiSpringBoot.service.impl;

import com.example.ApiSpringBoot.dto.request.LoginRequest;
import com.example.ApiSpringBoot.dto.request.UserRequest;
import com.example.ApiSpringBoot.dto.response.LoginResponse;
import com.example.ApiSpringBoot.dto.response.UserResponse;
import com.example.ApiSpringBoot.entity.User;
import com.example.ApiSpringBoot.exception.NotFoundException;
import com.example.ApiSpringBoot.repository.UserRespository;
import com.example.ApiSpringBoot.service.UserService;

import com.example.ApiSpringBoot.utils.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserImplement implements UserService {
  private final UserRespository userRespository;

  public UserImplement(UserRespository userRespository) {
    this.userRespository = userRespository;
  }

  @Override
  public UserResponse create(UserRequest userRequest) {
    User user = new User(
          userRequest.getUsername(),
          userRequest.getPassword(),
          userRequest.getEmail());
    userRespository.save(user);
    UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    return userResponse;
  }

  @Override
  public UserResponse update(UserRequest userRequest, int id) {
    User existingUser = userRespository.findById(id).orElse(null);
    if (existingUser != null) {
      existingUser.setUsername(userRequest.getUsername());
      existingUser.setPassword(userRequest.getPassword());
      existingUser.setEmail(userRequest.getEmail());
      userRespository.save(existingUser);
      UserResponse userResponse = new UserResponse(
            existingUser.getId(),
            existingUser.getUsername(),
            existingUser.getEmail());
      return userResponse;
    }
    throw new NotFoundException();
  }

  @Override
  public UserResponse delete(int id) {
    User existinUser = userRespository.findById(id).orElse(null);
    if (existinUser != null) {
      userRespository.delete(existinUser);
    }
    return null;
  }

  @Override
  public List<UserResponse> list() {
    List<User> users = userRespository.findAll();
    List<UserResponse> usersResponse = new ArrayList<>();
    for (User user : users) {
      UserResponse userResponse = new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail());
      usersResponse.add(userResponse);
    }
    return usersResponse;
  }

  @Override
  public UserResponse getById(int id) {
    User existing = userRespository.getById(id);
    if (existing == null) {
      throw new NotFoundException();
    }
    User user = userRespository.getById(id);
    UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    return userResponse;
  }

  @Override
  public UserResponse getByUsername(String username) {
    User user = userRespository.getByUsername(username);
    UserResponse userResponse = new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    return userResponse;
  }

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    User existingUser = userRespository.getByUsername(loginRequest.getUsername());
    if (existingUser == null) {
      throw new NotFoundException();
    }
    if (!existingUser.getPassword().equals(loginRequest.getPassword())) {
      throw new IllegalStateException("Invalid");
    }
    String token = TokenUtil.generateToken();
    existingUser.setToken(token);
    userRespository.save(existingUser);
    return new LoginResponse(existingUser.getId(), existingUser.getUsername(), existingUser.getEmail(), token);
  }

}
