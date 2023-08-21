package br.com.iwt.pizzaria.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.iwt.pizzaria.domain.model.Produto;
import br.com.iwt.pizzaria.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	@Autowired
	ProdutoRepository repositorio;
	
	public Produto adicionar(Produto produto) {
		return repositorio.save(produto);
	}
	
	public List<Produto> listarTodos(Pageable pageable){
		
		Page<Produto> produtoPage = repositorio.findAll(pageable);
		
		return produtoPage.getContent();
	}
	
	public Optional<Produto> listarPorId(UUID id) {
		return repositorio.findById(id);
	}
	
	public void deletar(Produto produto) {
		
		repositorio.delete(produto);
	}
}
