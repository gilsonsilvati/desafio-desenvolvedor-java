package br.com.cds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.cds.model.Pessoa;
import br.com.cds.model.Status;
import br.com.cds.repository.Pessoas;
import br.com.cds.repository.filter.PessoaFilter;

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
	
	public String ativar(Long codigo) {
		Pessoa pessoa = pessoas.findOne(codigo);
		pessoa.setStatus(Status.ATIVO);
		pessoas.save(pessoa);
		
		return Status.ATIVO.getDescricao();
	}
	
	public List<Pessoa> filtrar(PessoaFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome(); 
		return pessoas.findByNomeContaining(nome);
	}
	
}
