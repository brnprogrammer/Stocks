package com.stock.manager.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.stock.manager.model.Stock;
import com.stock.manager.service.StockService;

@RestController
public class StockController {
	
	
	@Autowired
	private StockService service;
	
	
	@PostMapping("/save")
	@CacheEvict(value = "stocks", allEntries = true) 
	public ResponseEntity<Stock> salvar(@RequestBody Stock stock, UriComponentsBuilder uriBuilder) throws URISyntaxException {
		URI uri = uriBuilder.path("/stock/{cdStock}").buildAndExpand(stock.getIdStock()).toUri(); 
		
		return ResponseEntity.created(uri).body(stock);
	}
	
	@GetMapping("/stock/{cdStock}")
	@Cacheable(value = "stocks")
	public ResponseEntity<Optional<Stock>> stockPorId(@PathVariable String cdStock) {
		
		Optional<Stock> stock = Optional.ofNullable(service.stockPorId(cdStock));
		if(stock.isPresent()) {
			return ResponseEntity.ok().body(stock);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	
	@GetMapping("/todos")
	@Cacheable(value = "stocks")
	public List<Stock> listAll() {
		return service.listarTodos();
		
	}
	

}
