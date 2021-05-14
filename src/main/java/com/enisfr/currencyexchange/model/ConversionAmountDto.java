package com.enisfr.currencyexchange.model;

import lombok.Data;

@Data
public class ConversionAmountDto {
  private Long transactionId;
  private double amount;

}
