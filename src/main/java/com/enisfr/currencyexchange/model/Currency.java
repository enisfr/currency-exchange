package com.enisfr.currencyexchange.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.HashMap;
import lombok.Data;

@Data
public class Currency {
  private String base;
  private HashMap<String, Double> rates;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date date;

}
