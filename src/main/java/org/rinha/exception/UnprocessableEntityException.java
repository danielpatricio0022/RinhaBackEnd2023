package org.rinha.exception;

import org.springframework.validation.BindingResult;

public class UnprocessableEntityException extends RuntimeException {
  private final String message;

  public UnprocessableEntityException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}


