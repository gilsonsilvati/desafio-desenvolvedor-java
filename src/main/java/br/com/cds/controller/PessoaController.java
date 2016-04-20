package br.com.cds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cds.model.Pessoa;
import br.com.cds.repository.Pessoas;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private Pessoas pessoas;
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroPessoa";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Pessoa pessoa) {
		pessoas.save(pessoa);
		
		return "CadastroPessoa";
	}

}
