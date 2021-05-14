package com.enisfr.currencyexchange.model;

import lombok.Data;

@Data
public class ErrorResponse {
  private String message;
  private Object data;
}
