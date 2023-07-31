package br.ufscar.dc.dsw.Trabalho2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.Trabalho2.models.entities.SiteReserva;
import br.ufscar.dc.dsw.Trabalho2.service.spec.ISiteResService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/sitereserva")
public class SiteReservaController {

	@Autowired
	private ISiteResService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(SiteReserva siteReserva) {
		return "site/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("sitereserva",service.buscarTodos());
		return "site/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid SiteReserva siteReserva, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "site/cadastro";
		}

		System.out.println("password = " + siteReserva.getSenha());
		
		siteReserva.setSenha(siteReserva.getSenha());
		service.salvar(siteReserva);
		attr.addFlashAttribute("sucess", "site.create.sucess");
		return "redirect:/site/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("sitereserva", service.buscarPorId(id));
		return "site/cadastro";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("sucess", "site.delete.sucess");
		return listar(model);
	}
}
