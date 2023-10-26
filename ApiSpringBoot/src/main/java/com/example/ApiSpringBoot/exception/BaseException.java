package com.example.ApiSpringBoot.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
  private int code;
  private String message;
}