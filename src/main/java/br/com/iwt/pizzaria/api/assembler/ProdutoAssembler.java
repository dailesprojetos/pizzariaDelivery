package br.com.iwt.pizzaria.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.iwt.pizzaria.api.model.ProdutoModel;
import br.com.iwt.pizzaria.api.model.input.ProdutoInput;
import br.com.iwt.pizzaria.domain.model.Produto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ProdutoAssembler {

	private ModelMapper modelMapper;
	
	public ProdutoModel toModel(Produto produto) {
		
		ProdutoModel produtoModel = modelMapper.map(produto, ProdutoModel.class);
		
		return produtoModel;
	}
	
	public List<ProdutoModel> toCollectionModel(List<Produto> produtos){
		return produtos.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Produto toEntity(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }
}