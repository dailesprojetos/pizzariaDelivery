package br.com.iwt.pizzaria.api.model.input;

import java.math.BigDecimal;

import br.com.iwt.pizzaria.domain.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraInput {
	
	private Cliente clienteId;
	private BigDecimal total;

}
