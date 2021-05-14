package com.enisfr.currencyexchange.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class ExchangeRateDto {
  private String sourceCurrency;
  private String targetCurrency;
  private double sourceAmount;
  private double targetAmount;
  private double exchangeRate;
}
