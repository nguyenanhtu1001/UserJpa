package com.example.ApiSpringBoot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class UserRequest {
  @NotBlank(message="username not blank")
  private String username;
  @NotBlank(message="password not blank")
  private String password;
  @NotBlank(message="email not blank")
  private String email;
}
