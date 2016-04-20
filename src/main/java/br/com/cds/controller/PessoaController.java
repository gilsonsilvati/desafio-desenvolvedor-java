package br.com.cds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PessoaController {
	
	@RequestMapping("/pessoas/novo")
	public String novo() {
		return "CadastroPessoa";
	}

}
