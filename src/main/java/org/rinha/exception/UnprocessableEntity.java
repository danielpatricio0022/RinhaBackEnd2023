package org.rinha.exception;

public class UnprocessableEntity extends RuntimeException {
  public UnprocessableEntity(String message) {
    super(message);
  }
}
