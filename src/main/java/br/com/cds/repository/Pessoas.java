package br.com.cds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cds.model.Pessoa;

public interface Pessoas extends JpaRepository<Pessoa, Long> {
	
	public List<Pessoa> findByNomeContaining(String nome);
	
}
