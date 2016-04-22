package br.com.cds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cds.model.Pessoa;

public interface Pessoas extends JpaRepository<Pessoa, Long> {

}
