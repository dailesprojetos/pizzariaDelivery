package br.com.iwt.pizzaria.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.iwt.pizzaria.api.model.CompraModel;
import br.com.iwt.pizzaria.api.model.input.CompraInput;
import br.com.iwt.pizzaria.domain.model.Compra;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CompraAssembler {

	private ModelMapper modelMapper;
	
	public CompraModel toModel(Compra compra) {
		
		CompraModel compraModel = modelMapper.map(compra, CompraModel.class);
		
		return compraModel;
	}
	
	public List<CompraModel> toCollectionModel(List<Compra> compra){
		return compra.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Compra toEntity(CompraInput compraInput) {
        return modelMapper.map(compraInput, Compra.class);
    }
}