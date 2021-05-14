package com.enisfr.currencyexchange.extcall;

import com.enisfr.currencyexchange.exception.GlobalException;
import com.enisfr.currencyexchange.model.Currency;
import feign.FeignException;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatesApiFeignServiceImpl implements RatesApiFeignService {

  private final RatesApiFeignClient ratesApiFeignClient;
  private static final Logger logger = LoggerFactory.getLogger(RatesApiFeignServiceImpl.class);

  @Override
  public Currency getLatestCurrency() {
    long startMs = System.currentTimeMillis();
    ResponseEntity<Currency> responseEntity;
    try {
      logger.info("[getLatestCurrency()] rates api call started");
      responseEntity = ratesApiFeignClient.getLatestCurrency();
      logger.info("[getLatestCurrency()] rates api call completed :: duration={}",
          (System.currentTimeMillis() - startMs));
    } catch (FeignException ex) {
      logger.error("[getLatestCurrency()] rates api call failed :: duration={}", (System.currentTimeMillis() - startMs),
          ex);
      throw new GlobalException(ex.getMessage());
    }
    return responseEntity != null ? responseEntity.getBody() : null;
  }

  @Override
  public Currency getLatestCurrencyBySymbols(List<String> symbols) {
    long startMs = System.currentTimeMillis();
    ResponseEntity<Currency> responseEntity;
    try {
      logger.info("[getLatestCurrencyBySymbols()] rates api call started");
      responseEntity = ratesApiFeignClient.getLatestCurrencyBySymbols(symbols);
      logger.info("[getLatestCurrencyBySymbols()] rates api call completed :: duration={}",
          (System.currentTimeMillis() - startMs));
    } catch (FeignException ex) {
      logger.error("[getLatestCurrencyBySymbols()] rates api call failed :: duration={}",
          (System.currentTimeMillis() - startMs),
          ex);
      throw new GlobalException(ex.getMessage());
    }
    return responseEntity != null ? responseEntity.getBody() : null;
  }

  @Override
  public Currency getLatestCurrencyByBase(String base) {
    long startMs = System.currentTimeMillis();
    ResponseEntity<Currency> responseEntity;
    try {
      logger.info("[getLatestCurrencyByBase()] rates api call started");
      responseEntity = ratesApiFeignClient.getLatestCurrencyByBase(base);
      logger.info("[getLatestCurrencyByBase()] rates api call completed :: duration={}",
          (System.currentTimeMillis() - startMs));
    } catch (FeignException ex) {
      logger.error("[getLatestCurrencyByBase()] rates api call failed :: duration={}",
          (System.currentTimeMillis() - startMs),
          ex);
      throw new GlobalException(ex.getMessage());
    }
    return responseEntity != null ? responseEntity.getBody() : null;
  }

  @Override
  public Currency getLatestCurrencyBySymbolsAndBase(List<String> symbols, String base) {
    long startMs = System.currentTimeMillis();
    ResponseEntity<Currency> responseEntity;
    try {
      logger.info("[getLatestCurrencyBySymbolsAndBase()] rates api call started");
      responseEntity = ratesApiFeignClient.getLatestCurrencyBySymbolsAndBase(symbols, base);
      logger.info("[getLatestCurrencyBySymbolsAndBase()] rates api call completed :: duration={}",
          (System.currentTimeMillis() - startMs));
    } catch (FeignException ex) {
      logger.error("[getLatestCurrencyBySymbolsAndBase()] rates api call failed :: duration={}",
          (System.currentTimeMillis() - startMs),
          ex);
      throw new GlobalException(ex.getMessage());
    }
    return responseEntity != null ? responseEntity.getBody() : null;
  }

  @Override
  public Currency getPastCurrency(Date date) {
    long startMs = System.currentTimeMillis();
    ResponseEntity<Currency> responseEntity;
    try {
      logger.info("[getPastCurrency()] rates api call started");
      responseEntity = ratesApiFeignClient.getPastCurrency(date);
      logger.info("[getPastCurrency()] rates api call completed :: duration={}",
          (System.currentTimeMillis() - startMs));
    } catch (FeignException ex) {
      logger.error("[getPastCurrency()] rates api call failed :: duration={}", (System.currentTimeMillis() - startMs),
          ex);
      throw new GlobalException(ex.getMessage());
    }
    return responseEntity != null ? responseEntity.getBody() : null;
  }

  @Override
  public Currency getPastCurrencyBySymbols(Date date, List<String> symbols) {
    long startMs = System.currentTimeMillis();
    ResponseEntity<Currency> responseEntity;
    try {
      logger.info("[getPastCurrencyBySymbols()] rates api call started");
      responseEntity = ratesApiFeignClient.getPastCurrencyBySymbols(date, symbols);
      logger.info("[getPastCurrencyBySymbols()] rates api call completed :: duration={}",
          (System.currentTimeMillis() - startMs));
    } catch (FeignException ex) {
      logger.error("[getPastCurrencyBySymbols()] rates api call failed :: duration={}",
          (System.currentTimeMillis() - startMs),
          ex);
      throw new GlobalException(ex.getMessage());
    }
    return responseEntity != null ? responseEntity.getBody() : null;
  }

  @Override
  public Currency getPastCurrencyByBase(Date date, String base) {
    long startMs = System.currentTimeMillis();
    ResponseEntity<Currency> responseEntity;
    try {
      logger.info("[getPastCurrencyByBase()] rates api call started");
      responseEntity = ratesApiFeignClient.getPastCurrencyByBase(date, base);
      logger.info("[getPastCurrencyByBase()] rates api call completed :: duration={}",
          (System.currentTimeMillis() - startMs));
    } catch (FeignException ex) {
      logger.error("[getPastCurrencyByBase()] rates api call failed :: duration={}",
          (System.currentTimeMillis() - startMs),
          ex);
      throw new GlobalException(ex.getMessage());
    }
    return responseEntity != null ? responseEntity.getBody() : null;
  }

  @Override
  public Currency getPastCurrencyBySymbolsAndBase(Date date, List<String> symbols, String base) {
    long startMs = System.currentTimeMillis();
    ResponseEntity<Currency> responseEntity;
    try {
      logger.info("[getPastCurrencyBySymbolsAndBase()] rates api call started");
      responseEntity = ratesApiFeignClient.getPastCurrencyBySymbolsAndBase(date, symbols, base);
      logger.info("[getPastCurrencyBySymbolsAndBase()] rates api call completed :: duration={}",
          (System.currentTimeMillis() - startMs));
    } catch (FeignException ex) {
      logger.error("[getPastCurrencyBySymbolsAndBase()] rates api call failed :: duration={}",
          (System.currentTimeMillis() - startMs),
          ex);
      throw new GlobalException(ex.getMessage());
    }
    return responseEntity != null ? responseEntity.getBody() : null;
  }

}
