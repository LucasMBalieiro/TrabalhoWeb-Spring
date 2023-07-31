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
