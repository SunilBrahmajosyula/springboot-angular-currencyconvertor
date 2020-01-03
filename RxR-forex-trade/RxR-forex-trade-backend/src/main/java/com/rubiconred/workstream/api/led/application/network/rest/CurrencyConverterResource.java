package com.rubiconred.workstream.api.led.application.network.rest;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rubiconred.workstream.api.led.application.network.model.ExchangeRate;
import com.rubiconred.workstream.api.led.application.network.service.CurrencyConverterService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/exchange-rates")
public class CurrencyConverterResource {

	@Autowired
	private CurrencyConverterService service;

	@RequestMapping("/all")
	public ExchangeRate getExchangeRates(@RequestParam(value = "currencyFrom", required = false)String currencyFrom) throws IOException {
		return service.getExchangeRates(currencyFrom);
	}

	@RequestMapping
	public ExchangeRate convertCurrency(@RequestParam(value = "currencyFrom", required = false)String currencyFrom,@RequestParam(value = "currencyTo", required = false)String currencyTo) throws IOException {
		return service.convertCurrency(currencyFrom, currencyTo);
	}
	
	@GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> streamFlux() {
	    return Flux.interval(Duration.ofSeconds(1))
	      .map(sequence -> "CurrentTime - " + LocalTime.now().toString());
	}
}
