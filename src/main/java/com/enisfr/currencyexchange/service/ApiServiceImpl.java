package com.enisfr.currencyexchange.service;

import com.enisfr.currencyexchange.extcall.RatesApiFeignService;
import com.enisfr.currencyexchange.model.ConversionAmountDto;
import com.enisfr.currencyexchange.model.ExchangeRate;
import com.enisfr.currencyexchange.model.ExchangeRateDto;
import com.enisfr.currencyexchange.repository.ExchangeRateRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {

  private final RatesApiFeignService ratesApiFeignService;
  private final ExchangeRateRepository exchangeRateRepository;
  private final ModelMapper modelMapper;

  @Transactional
  @Override
  public ExchangeRateDto getLatestExchangeRate(String sourceCurrency, String targetCurrency) {
    ExchangeRate exchangeRate = new ExchangeRate();

    double rate = ratesApiFeignService.getLatestCurrencyBySymbolsAndBase(
        new ArrayList<>(Collections.singletonList(targetCurrency)),
        sourceCurrency).getRates().get(targetCurrency);

    exchangeRate.setSourceCurrency(sourceCurrency);
    exchangeRate.setTargetCurrency(targetCurrency);
    exchangeRate.setRate(rate);

    exchangeRateRepository.save(exchangeRate);

    ExchangeRateDto result = new ExchangeRateDto();
    result.setExchangeRate(rate);

    return result;
  }

  @Transactional
  @Override
  public ExchangeRateDto getPastExchangeRate(Date date, String sourceCurrency, String targetCurrency) {
    ExchangeRate exchangeRate = new ExchangeRate();

    double rate = ratesApiFeignService.getPastCurrencyBySymbolsAndBase(
        date,
        new ArrayList<>(Collections.singletonList(targetCurrency)),
        sourceCurrency).getRates().get(targetCurrency);

    exchangeRate.setSourceCurrency(sourceCurrency);
    exchangeRate.setTargetCurrency(targetCurrency);
    exchangeRate.setRate(rate);
    exchangeRate.setCurrencyDate(date);

    exchangeRateRepository.save(exchangeRate);

    ExchangeRateDto result = new ExchangeRateDto();
    result.setExchangeRate(rate);

    return result;
  }

  @Transactional
  @Override
  public ConversionAmountDto getLatestConvertedExchangeRate(String sourceCurrency, String targetCurrency,
      double amount) {
    ConversionAmountDto response = new ConversionAmountDto();
    ExchangeRate exchangeRate = new ExchangeRate();

    double rate = ratesApiFeignService
        .getLatestCurrencyBySymbolsAndBase(new ArrayList<>(Collections.singletonList(targetCurrency)),
            sourceCurrency).getRates().get(targetCurrency);

    double targetAmount = amount * rate;

    exchangeRate.setSourceCurrency(sourceCurrency);
    exchangeRate.setTargetCurrency(targetCurrency);
    exchangeRate.setSourceAmount(amount);
    exchangeRate.setTargetAmount(targetAmount);
    exchangeRate.setRate(rate);

    exchangeRateRepository.save(exchangeRate);

    response.setTransactionId(exchangeRate.getId());
    response.setAmount(exchangeRate.getTargetAmount());
    return response;
  }

  @Transactional
  @Override
  public ConversionAmountDto getPastConvertedExchangeRate(Date date, String sourceCurrency, String targetCurrency,
      double amount) {
    ConversionAmountDto response = new ConversionAmountDto();
    ExchangeRate exchangeRate = new ExchangeRate();

    double rate = ratesApiFeignService
        .getPastCurrencyBySymbolsAndBase(
            date,
            new ArrayList<>(Collections.singletonList(targetCurrency)),
            sourceCurrency).getRates().get(targetCurrency);

    double targetAmount = amount * rate;

    exchangeRate.setSourceCurrency(sourceCurrency);
    exchangeRate.setTargetCurrency(targetCurrency);
    exchangeRate.setSourceAmount(amount);
    exchangeRate.setTargetAmount(targetAmount);
    exchangeRate.setRate(rate);
    exchangeRate.setCurrencyDate(date);

    exchangeRateRepository.save(exchangeRate);

    response.setTransactionId(exchangeRate.getId());
    response.setAmount(exchangeRate.getTargetAmount());
    return response;
  }

  @Transactional
  @Override
  public Page<ExchangeRateDto> getLatestExchangeRates(Optional<Long> id, Optional<Date> date, Optional<Integer> pageNumber, Optional<Integer> totalElements) {
    Page<ExchangeRate> exchangeRatePage;
    int page;
    int size;
    page = pageNumber.orElse(0);
    size = totalElements.orElse(10);
    Pageable pageable = PageRequest.of(page, size);

    if (id.isPresent()) {
      exchangeRatePage = exchangeRateRepository.findAllById(id.get(), pageable);
      List<ExchangeRateDto> exchangeRateDtoList = exchangeRatePage.getContent().stream().map(exchangeRate -> modelMapper.map(exchangeRate, ExchangeRateDto.class))
          .collect(Collectors.toList());
      return new PageImpl<>(exchangeRateDtoList, pageable, exchangeRatePage.getTotalElements());
    } else if (date.isPresent()) {
      exchangeRatePage = exchangeRateRepository.findAllByTransactionDate(date.get(), pageable);
      List<ExchangeRateDto> exchangeRateDtoList =  exchangeRatePage.getContent().stream().map(exchangeRate -> modelMapper.map(exchangeRate, ExchangeRateDto.class))
          .collect(Collectors.toList());
      return new PageImpl<>(exchangeRateDtoList, pageable, exchangeRatePage.getTotalElements());
    }
    return new PageImpl<>(new ArrayList<>(), pageable, 0);
  }
}
