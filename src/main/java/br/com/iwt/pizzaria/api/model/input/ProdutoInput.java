package br.com.iwt.pizzaria.api.model.input;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {
	
	private String nome;
	private String categoria;
	private BigDecimal preco;

}
