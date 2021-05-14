package com.enisfr.currencyexchange.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class ExchangeRate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String sourceCurrency;
  private String targetCurrency;
  private double sourceAmount;
  private double targetAmount;
  private double rate;
  private Date transactionDate = new Date(System.currentTimeMillis());
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date currencyDate;

}
