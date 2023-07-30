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

import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IHotelService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private IHotelService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Hotel hotel) {
		return "hotel/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("hotel",service.buscarTodos());
		return "hotel/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "hotel/cadastro";
		}

		System.out.println("password = " + hotel.getSenha());
		
		hotel.setSenha(hotel.getSenha());
		service.salvar(hotel);
		attr.addFlashAttribute("sucess", "hotel.create.sucess");
		return "redirect:/hotel/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("hotel", service.buscarPorId(id));
		return "hotel/cadastro";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("sucess", "hotel.delete.sucess");
		return listar(model);
	}
}
