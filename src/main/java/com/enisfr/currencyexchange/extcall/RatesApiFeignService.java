package com.enisfr.currencyexchange.extcall;

import com.enisfr.currencyexchange.model.Currency;
import java.util.Date;
import java.util.List;

public interface RatesApiFeignService {

  Currency getLatestCurrency();

  Currency getLatestCurrencyBySymbols(List<String> symbols);

  Currency getLatestCurrencyByBase(String base);

  Currency getLatestCurrencyBySymbolsAndBase(List<String> symbols, String base);

  Currency getPastCurrency(Date date);

  Currency getPastCurrencyBySymbols(Date date, List<String> symbols);

  Currency getPastCurrencyByBase(Date date, String base);

  Currency getPastCurrencyBySymbolsAndBase(Date date, List<String> symbols, String base);
}
