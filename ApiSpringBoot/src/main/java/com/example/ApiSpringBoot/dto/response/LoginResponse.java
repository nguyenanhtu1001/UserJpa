package com.example.ApiSpringBoot.dto.response;

import lombok.*;

@Getter
@Setter
public class LoginResponse {
  private int id;
  private String username;
  private String email;
  private String token;

  public LoginResponse(int id, String username, String email, String token) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.token = token;
  }
}
