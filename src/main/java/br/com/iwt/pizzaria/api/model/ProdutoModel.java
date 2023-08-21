package br.com.iwt.pizzaria.api.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {
	
	private UUID id;
	private String nome;
	private String categoria;
	private BigDecimal preco;

}
