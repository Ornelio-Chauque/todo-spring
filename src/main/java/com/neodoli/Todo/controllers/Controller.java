package com.neodoli.Todo.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("name", "Vadacom MZ");
        return "home";
    }

    @GetMapping("/error")
    public String showError(){
       return "404" ;
    }
}
