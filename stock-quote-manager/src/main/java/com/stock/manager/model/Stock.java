package com.stock.manager.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Stock {
	
	@JsonIgnore
	private Long idStock;
	
	private String cdStock;
	
	private List<Quotes> quotes = new ArrayList();


	public Long getIdStock() {
		return idStock;
	}

	public void setIdStock(Long idStock) {
		this.idStock = idStock;
	}

	public String getCdStock() {
		return cdStock;
	}

	public void setCdStock(String cdStock) {
		this.cdStock = cdStock;
	}

	public List<Quotes> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quotes> quotes) {
		this.quotes = quotes;
	}

	

	
	
}
