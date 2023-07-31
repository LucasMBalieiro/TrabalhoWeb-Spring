package br.ufscar.dc.dsw.Trabalho2.controller;

import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.models.SiteReserva;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IHotelService;
import br.ufscar.dc.dsw.Trabalho2.service.spec.ISiteResService;
import org.apache.maven.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.Trabalho2.models.Usuario;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;
	@Autowired
	private IHotelService hservice;
	@Autowired
	private ISiteResService sservice;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/cadastrarHotel")
	public String cadastrarHotel() {
		return "Admin/cadastroHotel";
	}

	@GetMapping("/cadastrarSite")
	public String cadastrarSite() {
		return "Admin/cadastroSite";
	}

	@GetMapping("/listarHotel")
	public String listarHotel(ModelMap model) {
		model.addAttribute("hoteis",hservice.buscarTodos());
		return "Admin/listaHoteis";
	}

	@GetMapping("/listarSites")
	public String listarSites(ModelMap model) {
		model.addAttribute("sites",sservice.buscarTodos());
		return "Admin/listaSites";
	}

	@PostMapping("/salvarHotel")
	public String salvarHotel(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "Admin/cadastroHotel";
		}

		System.out.println("password = " + hotel.getSenha());

		hotel.setSenha(encoder.encode(hotel.getSenha()));
		hservice.salvar(hotel);
		attr.addFlashAttribute("sucess", "usuario.create.sucess");
		return "redirect:/Admin/listarHoteis";
	}

	@PostMapping("/salvarSite")
	public String salvarSite(@Valid SiteReserva site, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "Admin/cadastroSite";
		}

		System.out.println("password = " + site.getSenha());

		site.setSenha(encoder.encode(site.getSenha()));
		sservice.salvar(site);
		attr.addFlashAttribute("sucess", "usuario.create.sucess");
		return "redirect:/Admin/listarSites";
	}

	@GetMapping("/editarHotel/{id}")
	public String preEditarHotel(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("hotel", hservice.buscarPorId(id));
		return "Admin/cadastroHotel";
	}

	@PostMapping("/editarHotel")
	public String editarHotel(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "Admin/cadastroHotel";
		}

		System.out.println(hotel.getSenha());

		hservice.salvar(hotel);
		attr.addFlashAttribute("sucess", "usuario.edit.sucess");
		return "redirect:/Admin/listarHoteis";
	}

	@GetMapping("/editarSite/{id}")
	public String preEditarSite(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("site", sservice.buscarPorId(id));
		return "Admin/cadastroSite";
	}

	@PostMapping("/editarSite")
	public String editarSite(@Valid SiteReserva site, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "Admin/cadastroSite";
		}

		System.out.println(site.getSenha());

		sservice.salvar(site);
		attr.addFlashAttribute("sucess", "usuario.edit.sucess");
		return "redirect:/Admin/listarSites";
	}

	@GetMapping("/excluirHotel/{id}")
	public String excluirHotel(@PathVariable("id") Long id, ModelMap model) {
		hservice.excluir(id);
		model.addAttribute("sucess", "usuario.delete.sucess");
		return listarHotel(model);
	}

	@GetMapping("/excluirSite/{id}")
	public String excluirSite(@PathVariable("id") Long id, ModelMap model) {
		sservice.excluir(id);
		model.addAttribute("sucess", "usuario.delete.sucess");
		return listarSites(model);
	}
}
