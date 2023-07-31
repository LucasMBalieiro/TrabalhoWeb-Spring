package br.ufscar.dc.dsw.Trabalho2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.Trabalho2.models.SiteReserva;
import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.models.Promocao;
import br.ufscar.dc.dsw.Trabalho2.service.spec.ISiteResService;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IHotelService;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IPromocaoService;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/Site")
public class SiteReservaController {

	@Autowired
	private ISiteResService sservice;
	@Autowired
	private IPromocaoService pservice;
	@Autowired
	private IHotelService hservice;

//	@GetMapping(value={"/home","/"})
//	public String index1() {
//		return "hotel/index";
//	}
//	@GetMapping("/cadastrar")
//	public String cadastrar(SiteReserva siteReserva) {
//		return "site/cadastro";
//	}
	
//	@GetMapping("/listar")
//	public String listar(ModelMap model) {
//		model.addAttribute("sitereserva",sservice.buscarTodos());
//		return "Site/listaPromocoes";
//	}
	
//	@PostMapping("/salvar")
//	public String salvar(@Valid SiteReserva siteReserva, BindingResult result, RedirectAttributes attr) {
//
//		if (result.hasErrors()) {
//			return "site/cadastro";
//		}
//
//		System.out.println("password = " + siteReserva.getSenha());
//
//		siteReserva.setSenha(siteReserva.getSenha());
//		sservice.salvar(siteReserva);
//		attr.addFlashAttribute("sucess", "site.create.sucess");
//		return "redirect:/site/listar";
//	}
	
//	@GetMapping("/editar/{id}")
//	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
//		model.addAttribute("sitereserva", sservice.buscarPorId(id));
//		return "site/cadastro";
//	}
//
//	@GetMapping("/excluir/{id}")
//	public String excluir(@PathVariable("id") Long id, ModelMap model) {
//		sservice.excluir(id);
//		model.addAttribute("sucess", "site.delete.sucess");
//		return listar(model);
//	}

	@GetMapping("/listarPromocoes")
	public String listarPromocoes(ModelMap model) {
		List<Promocao> l1 = pservice.buscarTodosPorHotel(getURLAtual());

		model.addAttribute("promocoes",l1);
		return "Site/listaPromocoes";//TODO criar html de lista de promos
	}
	private Hotel getURLAtual() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		Long id = Long.valueOf(a.getName());

		return hservice.buscarPorId(id);
	}
}
