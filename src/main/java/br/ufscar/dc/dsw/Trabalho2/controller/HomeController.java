package br.ufscar.dc.dsw.Trabalho2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String home() {
        System.out.println("\n\n Acesso a home \n\n");
        System.out.flush();

        return "layout";
    }

    @GetMapping("/login")
    public String login(ModelMap model) {
        System.out.println("\n Login \n");
        System.out.flush();

        System.out.println(model.getAttribute("email"));
        System.out.println(model.getAttribute("senha"));

        return "login";
    }
}