package br.ufscar.dc.dsw.Trabalho2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.Trabalho2.service.spec.IPromocaoService;

@Controller
@RequestMapping("/compras")
public class PromocaoController {

	@Autowired
	private IPromocaoService service;
	
	
}
