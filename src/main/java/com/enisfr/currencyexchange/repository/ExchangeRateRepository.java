package com.enisfr.currencyexchange.repository;

import com.enisfr.currencyexchange.model.ExchangeRate;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {
  Page<ExchangeRate> findAllById(Long id, Pageable pageable);
  Page<ExchangeRate> findAllByTransactionDate(Date date, Pageable pageable);
}
