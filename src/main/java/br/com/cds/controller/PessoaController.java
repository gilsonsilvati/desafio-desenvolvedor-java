package br.com.cds.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cds.model.Pessoa;
import br.com.cds.model.Status;
import br.com.cds.model.UF;
import br.com.cds.repository.Pessoas;
import br.com.cds.service.CadastroPessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	private static final String CADASTRO_VIEW = "CadastroPessoa";
	
	@Autowired
	private Pessoas pessoas;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Pessoa());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Pessoa pessoa, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			cadastroPessoaService.salvar(pessoa);
			attributes.addFlashAttribute("mensagem", "Cadastro efetuado com sucesso!");
			return "redirect:/pessoas/novo";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataNascimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Pessoa> todos = pessoas.findAll();
		ModelAndView mv = new ModelAndView("PesquisaPessoas");
		mv.addObject("pessoas", todos);
		
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Pessoa pessoa) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(pessoa);
		return mv;
	}
	
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroPessoaService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Pessoa excluída com sucesso!");
		return "redirect:/pessoas";
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
