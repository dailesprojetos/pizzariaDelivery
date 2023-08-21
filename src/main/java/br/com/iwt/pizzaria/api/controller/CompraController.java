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

import br.com.iwt.pizzaria.api.assembler.CompraAssembler;
import br.com.iwt.pizzaria.api.model.CompraModel;
import br.com.iwt.pizzaria.api.model.input.CompraInput;
import br.com.iwt.pizzaria.domain.model.Cliente;
import br.com.iwt.pizzaria.domain.model.Compra;
import br.com.iwt.pizzaria.domain.service.CadastroCompraService;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@Autowired
	CadastroCompraService servico;
	
	@Autowired
	CompraAssembler assembler;
	
	@GetMapping
	public ResponseEntity<Page<CompraModel>> listarCompras(@PageableDefault(size=2) Pageable pageable){		
		
		List<CompraModel> compras = assembler.toCollectionModel(servico.listarTodos(pageable));
		
		Page<CompraModel> comprasModelPage = new PageImpl<>(compras, pageable, compras.size());
		
		return ResponseEntity.ok(comprasModelPage);
	}
	
	@GetMapping("/{compraId}")
	public ResponseEntity<CompraModel> listarCompra(@PathVariable UUID compraId) {
		Compra compraResposta = servico.listarPorId(compraId).get();
		
		if(compraResposta != null)	return ResponseEntity.ok(assembler.toModel(compraResposta));
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public CompraInput adicionarCompra(@RequestBody CompraInput compraInput) {
		
		servico.adicionar(assembler.toEntity(compraInput));
		
		return compraInput;
	}
	
	@PutMapping("/{compraId}")
	public ResponseEntity<Compra> atualizaCompra(@PathVariable UUID compraId, @RequestBody CompraInput compraInput) {
		
		Compra resposta = servico.listarPorId(compraId).orElse(null);
		
		if(resposta != null) {
			BeanUtils.copyProperties(compraInput, resposta,"id","clientes");
			
			servico.adicionar(resposta);
			
			return ResponseEntity.ok(resposta);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{compraId}")
	public ResponseEntity<Compra> deletaCliente(@PathVariable UUID compraId){
	
		Compra resposta = servico.listarPorId(compraId).orElse(null);
		
		if(resposta != null) {
			servico.deletar(resposta);
			
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}	
}
