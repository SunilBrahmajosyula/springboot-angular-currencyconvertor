package com.rubiconred.workstream.api.led.application.network.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rubiconred.workstream.api.led.application.network.model.ExchangeRate;

@Service
public class CurrencyConverterService {

	private String EXCHANGE_RATE_URL = "https://api.exchangerate-api.com/v4/latest/";

	public ExchangeRate getExchangeRates(String currencyFrom) throws IOException {
		// Making Request
		URL url = new URL(EXCHANGE_RATE_URL + currencyFrom);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();

		InputStreamReader input = new InputStreamReader((InputStream) request.getContent());
		String string = IOUtils.toString(input);
		Gson gson = new Gson();
		return gson.fromJson(string, ExchangeRate.class);

	}

	public ExchangeRate convertCurrency(String currencyFrom, String currencyTo) throws IOException {
		ExchangeRate exchangeRate = this.getExchangeRates(currencyFrom);
		Map<String, String> rate = new HashMap<>();
		rate.put(currencyTo, exchangeRate.getRates().get(currencyTo));
		exchangeRate.getRates().clear();
		exchangeRate.setRates(rate);
		return exchangeRate;
	}

}
