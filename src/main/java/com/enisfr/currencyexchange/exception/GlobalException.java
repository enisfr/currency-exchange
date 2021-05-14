package com.enisfr.currencyexchange.exception;

public class GlobalException extends RuntimeException{
  public GlobalException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  public GlobalException(String errorMessage) {
    super(errorMessage);
  }
}
