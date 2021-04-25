package com.stock.manager.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.stock.manager.model.Quotes;
import com.stock.manager.model.Stock;
import com.stock.manager.model.StockDTO;

@Repository
public class StockRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void salvar(Stock stock) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		StockDTO stockAux = stockExistente(stock);
		
		StringBuilder queryInsertStock = null;
		StringBuilder queryInsertQuotes = null;
		
		if(stockAux == null) {
			queryInsertStock = new StringBuilder();
			SqlParameterSource params = new MapSqlParameterSource().addValue("cd_stock", stock.getCdStock());
			
			queryInsertStock.append("insert into Stock (cd_stock) values(:cd_stock) ");
			namedParameterJdbcTemplate.update(queryInsertStock.toString(), params, keyHolder);
			
			for (int i = 0; i < stock.getQuotes().size(); i++) {
				queryInsertQuotes = new StringBuilder();
				SqlParameterSource paramsQuotes = new MapSqlParameterSource()
						.addValue("id_stock", keyHolder.getKey())
						.addValue("valor_stock", stock.getQuotes().get(i).getValorStock())
						.addValue("dt_valor_stock", stock.getQuotes().get(i).getDtStock());
				queryInsertQuotes.append("insert into Quotes (id_stock, dt_stock, valor_stock) values(:id_stock, :dt_valor_stock, :valor_stock) ");
				namedParameterJdbcTemplate.update(queryInsertQuotes.toString(), paramsQuotes);
			}
			
		}else {
			
			for (int i = 0; i < stock.getQuotes().size(); i++) {
				queryInsertQuotes = new StringBuilder();
				SqlParameterSource paramsQuotes = new MapSqlParameterSource()
						.addValue("id_stock", stockAux.getIdStock())
						.addValue("valor_stock", stock.getQuotes().get(i).getValorStock())
						.addValue("dt_valor_stock", stock.getQuotes().get(i).getDtStock());
				queryInsertQuotes.append("insert into Quotes (id_stock, dt_stock, valor_stock) values(:id_stock, :dt_valor_stock, :valor_stock) ");
				namedParameterJdbcTemplate.update(queryInsertQuotes.toString(), paramsQuotes);
			}
			
		}
		
		
		
	}
	
	private StockDTO stockExistente(Stock stock) {
		try {
			StringBuilder query = new StringBuilder();
			SqlParameterSource params = new MapSqlParameterSource().addValue("cd_stock", stock.getCdStock());
			query.append("select id_stock, cd_stock from Stock where cd_stock = :cd_stock");
			return (StockDTO) namedParameterJdbcTemplate.queryForObject(query.toString(), params, new BeanPropertyRowMapper(StockDTO.class));
		} catch (Exception e) {
			return null;
		}
	}

	public Stock stockPorId(String cdStock) {
		try {
			StringBuilder queryStock = new StringBuilder();
			StringBuilder queryQuotes = new StringBuilder();
			
			Stock stock = new Stock();
			List<Quotes> quotes = new ArrayList<>();
			
			SqlParameterSource params = new MapSqlParameterSource().addValue("cd_stock", cdStock);
			queryStock.append("select id_stock, cd_stock from Stock where cd_stock = :cd_stock");
			stock =  (Stock) namedParameterJdbcTemplate.queryForObject(queryStock.toString(), params, new BeanPropertyRowMapper(Stock.class));
			
			SqlParameterSource paramsQuotes = new MapSqlParameterSource().addValue("id_stock", stock.getIdStock());
			queryQuotes.append("select dt_stock, valor_stock from Quotes where id_stock = :id_stock");
			quotes =  namedParameterJdbcTemplate.query(queryQuotes.toString(), paramsQuotes, new BeanPropertyRowMapper(Quotes.class));
		
			stock.getQuotes().addAll(quotes);
			
			return stock;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Stock> listarTodos() {
		try {
			StringBuilder queryStock = new StringBuilder();
			StringBuilder queryQuotes = new StringBuilder();
			
			List<Stock> stock = new ArrayList<>();
			List<Quotes> quotes = new ArrayList<>();
			
			queryStock.append("select id_stock, cd_stock from Stock");
			stock =  namedParameterJdbcTemplate.query(queryStock.toString(),  new BeanPropertyRowMapper(Stock.class));
			
			queryQuotes.append("select dt_stock, valor_stock, id_stock from Quotes");
			quotes =  namedParameterJdbcTemplate.query(queryQuotes.toString(), new BeanPropertyRowMapper(Quotes.class));
		
			
			for (int i = 0; i < stock.size(); i++) {
				for (int j = 0; j < quotes.size(); j++) {
					if(stock.get(i).getIdStock() == quotes.get(j).getIdStock()) {
						stock.get(i).getQuotes().add(quotes.get(j));
					}
				}
				
				
			}
			
			
			return stock;
		} catch (Exception e) {
			throw e;
		}
	}
	

}
