package br.ufscar.dc.dsw.Trabalho2.controller;

import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.models.SiteReserva;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IHotelService;
import br.ufscar.dc.dsw.Trabalho2.service.spec.ISiteResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.Trabalho2.models.Usuario;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IHotelService hservice;

	@Autowired
	private ISiteResService sservice;

	@Autowired
	private IUsuarioService uservice;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping(value = {"/index", "/"})
	public String index1() {
		return "admin/index";
	}

	/*
	 * PARTE PARA O HOTEL
	 * */

	@GetMapping("/cadastrarHotel")
	public String cadastrar(Hotel hotel, ModelMap model) {
		Hotel h = new Hotel();
		h.setId(null);
		model.addAttribute("hotel", h);

		return "admin/cadastroHotel";
	}


	@PostMapping("/salvarHotel")
	public String salvarHotel(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "admin/cadastroHotel";
		}
		hotel.setSenha(encoder.encode(hotel.getSenha()));
		hservice.salvar(hotel);
		attr.addFlashAttribute("sucess", "hotel.create.sucess");
		return "redirect:/hotel/listar";
	}

	@GetMapping("/editarHotel/{id}")
	public String preEditarH(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("hotel", hservice.buscarPorId(Long.parseLong(id)));
		return "admin/cadastroHotel";
	}

	@PostMapping("/editarHotel")
	public String editarH(@Valid Hotel hotel, BindingResult result, RedirectAttributes attr) {

		if (result.getFieldError("nome") != null ||
				result.getFieldError("cidade") != null ||
				result.getFieldError("senha") != null ||
				hotel.getEmail().isEmpty()
		) {
			return "admin/cadastroSite";
		}


		if (uservice.buscaUsuario(hotel.getEmail()) != null) {
			if (uservice.buscaUsuario(hotel.getEmail()).getTipo().equals("ROLE_HOTEL")) {//FIXME verifica se ta certo esa role
				if (hservice.buscarPorId(hotel.getId()).getId() != hotel.getId())
					return "admin/cadastroSite";

			} else {
				return "admin/cadastroSite";
			}
		}


		hotel.setSenha(encoder.encode(hotel.getSenha()));
		hservice.salvar(hotel);
		//attr.addFlashAttribute("sucess", "editora.edit.sucess");
		return "redirect:/hotel/listar";
	}

	@GetMapping("/excluirHotel/{id}")
	public String excluirH(@PathVariable("id") String id, ModelMap model) {
		hservice.excluir(Long.parseLong(id));
		model.addAttribute("sucess", "editora.delete.sucess");

		return "redirect:/hotel/listar";
	}

	/*
	 * PARTE PARA O SITE
	 * */

	@GetMapping("/cadastrarSite")
	public String cadastrarS(SiteReserva site, ModelMap model) {
		SiteReserva s = new SiteReserva();
		s.setId(null);
		model.addAttribute("site", s);

		return "admin/cadastroSite";
	}


	@PostMapping("/salvarSite")
	public String salvarSite(@Valid SiteReserva site, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "admin/cadastroSite";
		}
		site.setSenha(encoder.encode(site.getSenha()));
		sservice.salvar(site);
		attr.addFlashAttribute("sucess", "site.create.sucess");
		return "redirect:/site/listar";
	}

	@GetMapping("/editarSite/{id}")
	public String preEditarS(@PathVariable("id") String id, ModelMap model) {
		model.addAttribute("site", sservice.buscarPorId(Long.parseLong(id)));
		return "admin/cadastroSite";
	}

	@PostMapping("/editarSite")
	public String editarS(@Valid SiteReserva site, BindingResult result, RedirectAttributes attr) {
		if (result.getFieldError("nome") != null ||
				result.getFieldError("telefone") != null ||
				result.getFieldError("senha") != null ||
				site.getEmail().isEmpty()
		) {
			return "admin/cadastroSite";
		}


		if (uservice.buscaUsuario(site.getEmail()) != null) {
			if (uservice.buscaUsuario(site.getEmail()).getTipo().equals("ROLE_SITE")) {//FIXME verifica a role
				if (sservice.buscarPorId(site.getId()).getId() != site.getId())
					return "admin/cadastroSite";
			} else {
				return "admin/cadastroSite";
			}
		}


		site.setSenha(encoder.encode(site.getSenha()));
		sservice.salvar(site);
		attr.addFlashAttribute("sucess", "site.edit.sucess");
		return "redirect:/site/listar";
	}

	@GetMapping("/excluirSite/{id}")
	public String excluirS(@PathVariable("id") String id, ModelMap model) {
		Hotel site = sservice.buscarPorId(Long.valueOf(id));

		sservice.excluir(Long.parseLong(id));
		model.addAttribute("sucess", "site.delete.sucess");

		return "redirect:/site/listar";//TODO adicionar html do site de reserva
	}
}
