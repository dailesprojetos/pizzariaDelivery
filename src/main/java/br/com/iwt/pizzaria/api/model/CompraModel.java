package br.com.iwt.pizzaria.api.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import br.com.iwt.pizzaria.domain.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraModel {
	
	private UUID id;
	private Cliente clienteId;
	private BigDecimal total;
	
}
