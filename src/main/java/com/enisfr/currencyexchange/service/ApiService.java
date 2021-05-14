package com.enisfr.currencyexchange.service;

import com.enisfr.currencyexchange.model.ConversionAmountDto;
import com.enisfr.currencyexchange.model.ExchangeRateDto;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ApiService {

  ExchangeRateDto getLatestExchangeRate(String sourceCurrency, String targetCurrency);

  ExchangeRateDto getPastExchangeRate(Date date, String sourceCurrency, String targetCurrency);

  ConversionAmountDto getLatestConvertedExchangeRate(String sourceCurrency, String targetCurrency, double amount);

  ConversionAmountDto getPastConvertedExchangeRate(Date date, String sourceCurrency, String targetCurrency, double amount);

  Page<ExchangeRateDto> getLatestExchangeRates(Optional<Long> id, Optional<Date> date, Optional<Integer> pageNumber, Optional<Integer> totalElements);
}
