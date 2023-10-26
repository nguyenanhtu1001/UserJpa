package com.example.ApiSpringBoot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class LoginRequest {
  @NotBlank(message = "username not blank")
  private String username;
  @NotBlank(message = "password not blank")
  private String password;
}
