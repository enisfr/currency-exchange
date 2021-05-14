package com.enisfr.currencyexchange.controller;

import com.enisfr.currencyexchange.model.ConversionAmountDto;
import com.enisfr.currencyexchange.model.ExchangeRateDto;
import com.enisfr.currencyexchange.service.ApiService;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

  private final ApiService apiService;

  @ApiOperation(value = "Get latest exchange rates")
  @GetMapping("/latest/exchange-rate")
  public ExchangeRateDto getLatestExchangeRate(
      @RequestParam String sourceCurrency,
      @RequestParam String targetCurrency) {
    return apiService.getLatestExchangeRate(sourceCurrency, targetCurrency);
  }

  @ApiOperation(value = "Get past exchange rates")
  @GetMapping("/{date}/exchange-rate")
  public ExchangeRateDto getPastExchangeRate(
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
      @RequestParam String sourceCurrency,
      @RequestParam String targetCurrency) {
    return apiService.getPastExchangeRate(date, sourceCurrency, targetCurrency);
  }

  @ApiOperation(value = "Convert currencies with latest exchange rates")
  @GetMapping("/latest/convert")
  public ConversionAmountDto getLatestConvertedExchangeRate(
      @RequestParam String sourceCurrency,
      @RequestParam String targetCurrency,
      @RequestParam double amount) {
    return apiService.getLatestConvertedExchangeRate(sourceCurrency, targetCurrency, amount);
  }

  @ApiOperation(value = "Convert currencies with past exchange rates")
  @GetMapping("/{date}/convert")
  public ConversionAmountDto getPastConvertedExchangeRate(
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
      @RequestParam String sourceCurrency,
      @RequestParam String targetCurrency,
      @RequestParam double amount) {
    return apiService.getPastConvertedExchangeRate(date, sourceCurrency, targetCurrency, amount);
  }

  @ApiOperation(value = "Get list of transactions")
  @GetMapping("/latest/exchange-rates")
  public Page<ExchangeRateDto> getLatestExchangeRates(
      @RequestParam Optional<Long> id,
      @RequestParam Optional<Date> date,
      @RequestParam Optional<Integer> pageNumber,
      @RequestParam Optional<Integer> totalElements) {
    return apiService.getLatestExchangeRates(id, date, pageNumber, totalElements);
  }


}
