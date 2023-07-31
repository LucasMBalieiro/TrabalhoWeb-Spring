package br.ufscar.dc.dsw.Trabalho2.controller;

import br.ufscar.dc.dsw.Trabalho2.models.Promocao;
import br.ufscar.dc.dsw.Trabalho2.models.SiteReserva;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IPromocaoService;
import br.ufscar.dc.dsw.Trabalho2.service.spec.ISiteResService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IHotelService;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	//WIRES
	private final IHotelService hotelService;
	private final ISiteResService siteResService;
	private final IPromocaoService promocaoService;

	public HotelController(IHotelService hotelService, ISiteResService siteResService, IPromocaoService promocaoService) {
		this.hotelService = hotelService;
		this.siteResService = siteResService;
		this.promocaoService = promocaoService;
	}

	@GetMapping(value={"/home","/"})
	public String HomeHotel(){
		return "hotel/home";
	}
	private Hotel getCNPJAtual() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		Long id = Long.valueOf(a.getName());

		return hotelService.buscarPorId(id);
	}

	@GetMapping("/cadastrarpromo")
	public String  cadastrarPromocao(Promocao promocao, ModelMap model) {
		List<SiteReserva> sites = siteResService.buscarTodos();

		promocao.setHotel(getCNPJAtual());
		model.addAttribute("sites",sites);
		model.addAttribute("promocao", promocao);
		return "hotel/cadastroPromocao";//TODO fazer o html da home do hotel
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam(value="cidade",required=false) String cidade) {
		List<Hotel> l1;

		List<String> l2 = hotelService.buscarCidades();
		if(cidade != null && !cidade.isEmpty()) {
			l1 = hotelService.buscarTodosPorCidade(cidade);
		}
		else {
			l1 = hotelService.buscarTodos();
		}

		model.addAttribute("hoteis",l1);
		model.addAttribute("cidades",l2);
		return "hotel/lista";
	}

	@PostMapping("/salvarPromocao")
	public String salvarSite(@Valid Promocao promocao, BindingResult result, RedirectAttributes attr, ModelMap model) {

		if (result.getErrorCount() > 0) {
			return cadastrarPromocao(promocao, model);
		}

		promocaoService.salvar(promocao);
		attr.addFlashAttribute("sucess", "promocao.create.sucess");
		return "redirect:/hotel/listarPromocoes";
	}

	@GetMapping("/listarPromocoes")
	public String listarPromocoes(ModelMap model) {
		List<Promocao> l1 = promocaoService.buscarTodosPorHotel(getCNPJAtual());

		model.addAttribute("promocoes",l1);
		return "hotel/listaPromocoes";//TODO fazer o html da lista de promocoes do pelo hotel
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("hotel", hotelService.buscarPorId(id));
		return "hotel/cadastro";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		hotelService.excluir(id);
		model.addAttribute("sucess", "hotel.delete.sucess");
		return listar(model,null);
	}
}
