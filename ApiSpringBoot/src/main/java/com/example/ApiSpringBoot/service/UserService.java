package com.example.ApiSpringBoot.service;

import com.example.ApiSpringBoot.dto.request.LoginRequest;
import com.example.ApiSpringBoot.dto.request.UserRequest;
import com.example.ApiSpringBoot.dto.response.LoginResponse;
import com.example.ApiSpringBoot.dto.response.UserResponse;

import java.util.List;

public interface UserService {
  UserResponse create(UserRequest userRequest);
  UserResponse update(UserRequest userRequest, int id);
  UserResponse delete(int id);
  List<UserResponse> list();
  UserResponse getById(int id);
  UserResponse getByUsername(String username);
  LoginResponse login(LoginRequest loginRequest);
}
