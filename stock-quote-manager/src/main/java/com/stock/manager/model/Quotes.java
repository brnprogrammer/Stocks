package com.stock.manager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Quotes {

	

	private BigDecimal valorStock;
	
	private LocalDate dtStock;
	
	@JsonIgnore
	private Long idStock;

	public BigDecimal getValorStock() {
		return valorStock;
	}

	public void setValorStock(BigDecimal valorStock) {
		this.valorStock = valorStock;
	}

	public LocalDate getDtStock() {
		return dtStock;
	}

	public void setDtStock(LocalDate dtStock) {
		this.dtStock = dtStock;
	}

	public Long getIdStock() {
		return idStock;
	}

	public void setIdStock(Long idStock) {
		this.idStock = idStock;
	}
	
}
