package com.example.ApiSpringBoot.exception;

import com.example.ApiSpringBoot.constant.MessageResponse;

public class NotFoundException extends BaseException {
  public NotFoundException() {
    super();
    setCode(404);
    setMessage(MessageResponse.USER_NOT_FOUND);
  }
}