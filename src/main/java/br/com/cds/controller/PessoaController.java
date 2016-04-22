package br.com.cds.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cds.model.Pessoa;
import br.com.cds.model.Status;
import br.com.cds.model.UF;
import br.com.cds.repository.Pessoas;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private Pessoas pessoas;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroPessoa");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Pessoa pessoa) {
		pessoas.save(pessoa);
		
		ModelAndView mv = new ModelAndView("CadastroPessoa");
		mv.addObject("mensagem", "Cadastro efetuado com sucesso!");
		return mv;
	}
	
	@RequestMapping
	public String pesquisar() {
		return "PesquisaPessoas";
	}
	
	@ModelAttribute("todosUfPessoa")
	private List<UF> todosUfPessoa() {
		return Arrays.asList(UF.values());
	}
	
	@ModelAttribute("todosStatusPessoa")
	private List<Status> todosStatusPessoa() {
		return Arrays.asList(Status.values());
	}

}
