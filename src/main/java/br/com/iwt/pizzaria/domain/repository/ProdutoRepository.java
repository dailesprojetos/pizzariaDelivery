package br.com.iwt.pizzaria.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iwt.pizzaria.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

}
