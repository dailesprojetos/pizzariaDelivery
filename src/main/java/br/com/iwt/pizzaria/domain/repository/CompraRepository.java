package br.com.iwt.pizzaria.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iwt.pizzaria.domain.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, UUID> {

}
