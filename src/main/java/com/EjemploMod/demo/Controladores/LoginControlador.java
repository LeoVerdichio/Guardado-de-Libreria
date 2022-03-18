/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.EjemploMod.demo.Controladores;

import com.EjemploMod.demo.Entidades.Cliente;
import com.EjemploMod.demo.Servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Login")
public class LoginControlador {

    
    ClienteServicio clienteserv;

    @Autowired
    public LoginControlador(ClienteServicio clienteserv) {
        this.clienteserv = clienteserv;
    }
    
    
            
//    @GetMapping("/Login")
//    public String loginFormulario(@RequestParam(required=false) String error,ModelMap model){
//        if (error != null) {
//            model.put("error", "Email o Contraseña incorrecta");
//        }
//        return "login";
//    }
    
    
     @GetMapping("/SingIn")
    public String SingIn(ModelMap model) {

        model.addAttribute("cliente", new Cliente());

        return "cliente/Sing-in";
    }

    @PostMapping("/SingIn")
    public String procesarSingIn(@ModelAttribute Cliente cliente, ModelMap model) {
        try {
            clienteserv.registrarcliente(cliente);

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "cliente/Sing-in";
        }
        return "login/login";

    }
    
     @GetMapping("/login")
    public String loginFormulario(@RequestParam(required=false) String error,ModelMap model){
        if (error != null) {
            model.put("error", "Email o Contraseña incorrecta");
        }
        return "login/login";
    }
    
}
