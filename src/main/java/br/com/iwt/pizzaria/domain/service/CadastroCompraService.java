package br.com.iwt.pizzaria.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.iwt.pizzaria.domain.model.Compra;
import br.com.iwt.pizzaria.domain.model.Produto;
import br.com.iwt.pizzaria.domain.repository.CompraRepository;

@Service
public class CadastroCompraService {

	@Autowired
	CompraRepository repositorio;
	
	public Compra adicionar(Compra compra) {
		return repositorio.save(compra);
	}
	
	public List<Compra> listarTodos(Pageable pageable){
		
		Page<Compra> compraPage = repositorio.findAll(pageable);
		
		return compraPage.getContent();
	}
	
	public Optional<Compra> listarPorId(UUID id) {
		return repositorio.findById(id);
	}
	
	public void deletar(Compra compra) {
		
		repositorio.delete(compra);
	}
}
