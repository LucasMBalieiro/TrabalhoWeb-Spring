package br.ufscar.dc.dsw.Trabalho2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "layout";
    }

    @GetMapping("/logar")
    public String login() { return "login"; }
}