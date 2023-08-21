package br.com.iwt.pizzaria.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwt.pizzaria.api.assembler.ProdutoAssembler;
import br.com.iwt.pizzaria.api.model.ProdutoModel;
import br.com.iwt.pizzaria.api.model.input.ClienteInput;
import br.com.iwt.pizzaria.api.model.input.ProdutoInput;
import br.com.iwt.pizzaria.domain.model.Cliente;
import br.com.iwt.pizzaria.domain.model.Produto;
import br.com.iwt.pizzaria.domain.service.CadastroProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	CadastroProdutoService servico;
	
	@Autowired
	ProdutoAssembler assembler;
	
	@GetMapping
	public ResponseEntity<Page<ProdutoModel>> listarProdutos(@PageableDefault(size=2) Pageable pageable){		
		
		List<ProdutoModel> produtos = assembler.toCollectionModel(servico.listarTodos(pageable));
		
		Page<ProdutoModel> produtosModelPage = new PageImpl<>(produtos, pageable, produtos.size());
		
		return ResponseEntity.ok(produtosModelPage);
	}
	
	@GetMapping("/{produtoId}")
	public ResponseEntity<ProdutoModel> listarProduto(@PathVariable UUID produtoId) {
		Produto produtoResposta = servico.listarPorId(produtoId).get();
		
		if(produtoResposta != null)	return ResponseEntity.ok(assembler.toModel(produtoResposta));
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ProdutoInput adicionarProduto(@RequestBody ProdutoInput produtoInput) {
		
		servico.adicionar(assembler.toEntity(produtoInput));
		
		return produtoInput;
	}
	
	@PutMapping("/{produtoId}")
	public ResponseEntity<Produto> atualizaProduto(@PathVariable UUID produtoId, @RequestBody ProdutoInput produtoInput) {
		
		Produto resposta = servico.listarPorId(produtoId).orElse(null);
		
		if(resposta != null) {
			BeanUtils.copyProperties(produtoInput, resposta);
			
			servico.adicionar(resposta);
			
			return ResponseEntity.ok(resposta);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{produtoId}")
	public ResponseEntity<Produto> deletaProduto(@PathVariable UUID produtoId){
	
		Produto resposta = servico.listarPorId(produtoId).orElse(null);
		
		if(resposta != null) {
			servico.deletar(resposta);
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}	
}
