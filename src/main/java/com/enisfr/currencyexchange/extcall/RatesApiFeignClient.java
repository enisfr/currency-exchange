package com.enisfr.currencyexchange.extcall;

import com.enisfr.currencyexchange.model.Currency;
import java.util.Date;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${ratesapi.feign.name}", url = "${ratesapi.feign.url}")
public interface RatesApiFeignClient {

  @GetMapping("/latest")
  ResponseEntity<Currency> getLatestCurrency();

  @GetMapping("/latest")
  ResponseEntity<Currency> getLatestCurrencyBySymbols(@RequestParam List<String> symbols);

  @GetMapping("/latest")
  ResponseEntity<Currency> getLatestCurrencyByBase(@RequestParam String base);

  @GetMapping("/latest")
  ResponseEntity<Currency> getLatestCurrencyBySymbolsAndBase(@RequestParam List<String> symbols,
      @RequestParam String base);

  @GetMapping("/{date}")
  ResponseEntity<Currency> getPastCurrency(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);

  @GetMapping("/{date}")
  ResponseEntity<Currency> getPastCurrencyBySymbols(
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
      @RequestParam List<String> symbols);

  @GetMapping("/{date}")
  ResponseEntity<Currency> getPastCurrencyByBase(
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
      @RequestParam String base);

  @GetMapping("/{date}")
  ResponseEntity<Currency> getPastCurrencyBySymbolsAndBase(
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
      @RequestParam List<String> symbols,
      @RequestParam String base);
}
