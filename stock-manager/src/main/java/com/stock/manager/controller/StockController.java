package com.stock.manager.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StockController {
	
	private  List<String> stocks = new ArrayList<>(Arrays.asList("PETR4", "ITUB3", "WEGE3", "MGLU3")); 
	// Arrays.asList("PETR4", "ITUB3", "WEGE3", "MGLU3");
	
	@GetMapping()
	private List<String> stocksStorage(){
		return this.stocks;
	}
	
	@PostMapping
	public void saveNewStock(String stock) {
		this.stocks.add(stock);
	}
	
	


}
