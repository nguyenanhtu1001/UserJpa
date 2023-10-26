package com.example.ApiSpringBoot.dto.response;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
  private int id;
  private String username;
  private String email;

  public UserResponse(String username, String email) {
    this.username = username;
    this.email = email;
  }

  public UserResponse(int id, String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
  }
}
