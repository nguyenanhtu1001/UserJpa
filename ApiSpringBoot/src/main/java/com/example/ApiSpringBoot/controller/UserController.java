package com.example.ApiSpringBoot.controller;

import com.example.ApiSpringBoot.constant.MessageResponse;
import com.example.ApiSpringBoot.dto.request.LoginRequest;
import com.example.ApiSpringBoot.dto.request.UserRequest;
import com.example.ApiSpringBoot.dto.response.LoginResponse;
import com.example.ApiSpringBoot.dto.response.ResponseGeneral;
import com.example.ApiSpringBoot.dto.response.UserResponse;
import com.example.ApiSpringBoot.service.impl.UserImplement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

  private final UserImplement userImplement;

  @Autowired
  public UserController(UserImplement userImplement) {
    this.userImplement = userImplement;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseGeneral<UserResponse> createUser(@RequestBody UserRequest userRequest) {
    UserResponse userResponse = userImplement.create(userRequest);
    return ResponseGeneral.ofCreated(MessageResponse.CREATE_USER, userResponse );
  }
  @GetMapping
  public ResponseGeneral<List<UserResponse>> getAll(){
    List<UserResponse> list = userImplement.list();
    return new ResponseGeneral<>(MessageResponse.LIST, list, HttpStatus.OK.value());
  }
  @PutMapping("{id}")
  public ResponseGeneral<UserResponse> update(@PathVariable int id, @Valid @RequestBody UserRequest userRequest){
    UserResponse userResponse=  userImplement.update(userRequest, id);
    return new ResponseGeneral<UserResponse>(MessageResponse.UPDATE_USER,userResponse, HttpStatus.OK.value());
  }
  @PostMapping("/login")
  public ResponseGeneral<LoginResponse> login(@RequestBody LoginRequest loginRequest){
    LoginResponse loginResponse =userImplement.login(loginRequest);
    return new ResponseGeneral<LoginResponse>(MessageResponse.LOGIN,loginResponse, HttpStatus.OK.value());
  }
  @GetMapping("{id}")
  public ResponseGeneral<UserResponse> getById(@PathVariable int id){
    UserResponse userResponse=  userImplement.getById(id);
    return new ResponseGeneral<UserResponse>(MessageResponse.GETBYID,userResponse, HttpStatus.OK.value());
  }
}
