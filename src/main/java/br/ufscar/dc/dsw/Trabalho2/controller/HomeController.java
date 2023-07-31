package br.ufscar.dc.dsw.Trabalho2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String home() {
        System.out.println("\n\n Bem-vindo \n\n");
        System.out.flush();
        return "layout";
    }
    // Povoar tabelas
    @GetMapping("/login")
    public String login() {
        System.out.println("\n Login \n");
        System.out.flush();
        return "login";
    }
}