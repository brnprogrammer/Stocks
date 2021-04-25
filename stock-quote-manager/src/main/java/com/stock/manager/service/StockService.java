package com.stock.manager.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stock.manager.model.Stock;
import com.stock.manager.repository.StockRepository;

@Service
public class StockService {

	@Autowired
	private StockRepository repository;
	

	public void salvar(Stock stock) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = "http://localhost:8080/stocks";
		URI uri = new URI(baseUrl);
		 
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		
		if(result.getBody().contains(stock.getCdStock())) {
			repository.salvar(stock);
		}else {
			throw new RuntimeException("Stock not registered in stock-manager");
		}
		
	}

	public Stock stockPorId(String cdStock) {
		return repository.stockPorId(cdStock);
	}

	public List<Stock> listarTodos() {
		return repository.listarTodos();
	}
	
	
//	public List<Stock> listar() {
//		return repository.findAll();
//	}
	
	
}
