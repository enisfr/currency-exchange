package com.enisfr.currencyexchange.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.enisfr.currencyexchange.extcall.RatesApiFeignService;
import com.enisfr.currencyexchange.model.ConversionAmountDto;
import com.enisfr.currencyexchange.model.Currency;
import com.enisfr.currencyexchange.model.ExchangeRate;
import com.enisfr.currencyexchange.model.ExchangeRateDto;
import com.enisfr.currencyexchange.repository.ExchangeRateRepository;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class ApiServiceImplTest {

  ApiServiceImpl apiService;

  @Mock
  RatesApiFeignService ratesApiFeignService;

  @Mock
  ExchangeRateRepository exchangeRateRepository;

  @Mock
  ModelMapper modelMapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    apiService = new ApiServiceImpl(ratesApiFeignService, exchangeRateRepository, modelMapper);
  }

  @Test
  @Disabled
  void getLatestExchangeRate() {
    String sourceCurrency = "TRY";
    String targetCurrency = "USD";
    double rate = 10.0;

    Currency currency = new Currency();
    HashMap<String, Double> rates = new HashMap<>();
    rates.put(targetCurrency, rate);
    currency.setRates(rates);

    when(ratesApiFeignService.getLatestCurrencyBySymbolsAndBase(
        Collections.singletonList(targetCurrency), sourceCurrency)).thenReturn(currency);

    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setSourceCurrency(sourceCurrency);
    exchangeRate.setTargetCurrency(targetCurrency);
    exchangeRate.setRate(currency.getRates().get(targetCurrency));

    when(exchangeRateRepository.save(exchangeRate)).thenReturn(exchangeRate);


    ExchangeRateDto result  = apiService.getLatestExchangeRate(sourceCurrency, targetCurrency);

    assertEquals(result.getExchangeRate(), rate);
  }

  @Test
  @Disabled
  void getPastExchangeRate() {
    String sourceCurrency = "TRY";
    String targetCurrency = "USD";
    Date date = new Date(System.currentTimeMillis());
    double rate = 10.0;

    Currency currency = new Currency();
    HashMap<String, Double> rates = new HashMap<>();
    rates.put(targetCurrency, rate);
    currency.setRates(rates);

    when(ratesApiFeignService.getPastCurrencyBySymbolsAndBase(date,
        Collections.singletonList(targetCurrency), sourceCurrency)).thenReturn(currency);

    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setSourceCurrency(sourceCurrency);
    exchangeRate.setTargetCurrency(targetCurrency);
    exchangeRate.setRate(currency.getRates().get(targetCurrency));

    when(exchangeRateRepository.save(exchangeRate)).thenReturn(exchangeRate);

    ExchangeRateDto result  = apiService.getLatestExchangeRate(sourceCurrency, targetCurrency);

    assertEquals(result.getExchangeRate(), rate);
  }

  @Test
  void getLatestConvertedExchangeRate() {
    String sourceCurrency = "TRY";
    String targetCurrency = "USD";
    double rate = 10.0;
    Long transactionId = 1L;
    double amount = 10.0;

    ConversionAmountDto expectedResponse = new ConversionAmountDto();
    expectedResponse.setTransactionId(transactionId);
    expectedResponse.setAmount(amount * rate);

    Currency currency = new Currency();
    HashMap<String, Double> rates = new HashMap<>();
    rates.put(targetCurrency, rate);
    currency.setRates(rates);

    when(ratesApiFeignService.getLatestCurrencyBySymbolsAndBase(
        Collections.singletonList(targetCurrency), sourceCurrency)).thenReturn(currency);

    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setId(transactionId);
    exchangeRate.setSourceCurrency(sourceCurrency);
    exchangeRate.setTargetCurrency(targetCurrency);
    exchangeRate.setRate(currency.getRates().get(targetCurrency));
    exchangeRate.setSourceAmount(amount);

    double targetAmount = exchangeRate.getSourceAmount() * exchangeRate.getRate();
    assertEquals(targetAmount, rate * amount);

    exchangeRate.setTargetAmount(targetAmount);

    when(exchangeRateRepository.save(exchangeRate)).thenReturn(exchangeRate);

    ConversionAmountDto response = apiService.getLatestConvertedExchangeRate(sourceCurrency, targetCurrency, amount);

    assertEquals(response.getAmount(), expectedResponse.getAmount());
  }

  @Test
  void getPastConvertedExchangeRate() {
    String sourceCurrency = "TRY";
    String targetCurrency = "USD";
    Date date = new Date(System.currentTimeMillis());
    double rate = 10.0;

    Long transactionId = 1L;
    double amount = 10.0;
    ConversionAmountDto expectedResponse = new ConversionAmountDto();
    expectedResponse.setTransactionId(transactionId);
    expectedResponse.setAmount(amount * rate);

    Currency currency = new Currency();
    HashMap<String, Double> rates = new HashMap<>();
    rates.put(targetCurrency, rate);
    currency.setRates(rates);

    when(ratesApiFeignService.getPastCurrencyBySymbolsAndBase(date,
        Collections.singletonList(targetCurrency), sourceCurrency)).thenReturn(currency);

    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setId(transactionId);
    exchangeRate.setSourceCurrency(sourceCurrency);
    exchangeRate.setTargetCurrency(targetCurrency);
    exchangeRate.setRate(currency.getRates().get(targetCurrency));
    exchangeRate.setSourceAmount(amount);

    double targetAmount = exchangeRate.getSourceAmount() * exchangeRate.getRate();
    assertEquals(targetAmount, rate * amount);

    exchangeRate.setTargetAmount(targetAmount);

    when(exchangeRateRepository.save(exchangeRate)).thenReturn(exchangeRate);

    ConversionAmountDto response = apiService.getPastConvertedExchangeRate(date, sourceCurrency, targetCurrency, amount);

    assertEquals(response.getAmount(), expectedResponse.getAmount());
  }

  @Test
  void getLatestExchangeRates() {
    String sourceCurrency = "TRY";
    String targetCurrency = "USD";
    double rate = 10.0;
    double amount = 10.0;
    Optional<Long> id = Optional.of(1L);
    Optional<Date> date = Optional.of(new Date(System.currentTimeMillis()));
    Optional<Integer> page = Optional.of(0);
    Optional<Integer> size = Optional.of(1);

    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setId(id.get());
    exchangeRate.setSourceCurrency(sourceCurrency);
    exchangeRate.setTargetCurrency(targetCurrency);
    exchangeRate.setRate(rate);
    exchangeRate.setSourceAmount(amount);
    exchangeRate.setTargetAmount(amount * rate);

    ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
    exchangeRateDto.setSourceCurrency(exchangeRate.getSourceCurrency());
    exchangeRateDto.setTargetCurrency(exchangeRate.getTargetCurrency());
    exchangeRateDto.setSourceAmount(exchangeRate.getTargetAmount());
    exchangeRateDto.setExchangeRate(exchangeRate.getRate());


    Pageable pageable = PageRequest.of(0, 1);
    Page<ExchangeRate> exchangeRatePage = new PageImpl<>(Collections.singletonList(exchangeRate), pageable, 1);
    Page<ExchangeRateDto> exchangeRateDtoPage = new PageImpl<>(Collections.singletonList(exchangeRateDto), pageable, 1);

    when(exchangeRateRepository.findAllById(id.get(), pageable)).thenReturn(exchangeRatePage);
    when(exchangeRateRepository.findAllByTransactionDate(date.get(), pageable)).thenReturn(exchangeRatePage);
    when(modelMapper.map(any(), any())).thenReturn(exchangeRateDto);


    Page<ExchangeRateDto> responseWithId = apiService.getLatestExchangeRates(id, Optional.empty(), page, size);
    assertEquals(responseWithId.getContent().get(0).getExchangeRate(), exchangeRateDtoPage.getContent().get(0).getExchangeRate());

    Page<ExchangeRateDto> responseWithDate = apiService.getLatestExchangeRates(Optional.empty(), date, page, size);
    assertEquals(responseWithDate.getContent().get(0).getExchangeRate(), exchangeRateDtoPage.getContent().get(0).getExchangeRate());


  }
}
