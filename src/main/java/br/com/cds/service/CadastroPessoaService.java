package br.com.cds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.cds.model.Pessoa;
import br.com.cds.repository.Pessoas;

@Service
public class CadastroPessoaService {
	
	@Autowired
	private Pessoas pessoas;
	
	public void salvar(Pessoa pessoa) {
		try {
			pessoas.save(pessoa);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void excluir(Long codigo) {
		pessoas.delete(codigo);
	}
	
}
