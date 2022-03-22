/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Controladores;

import com.EjemploMod.demo.Entidades.Cliente;
import com.EjemploMod.demo.Servicios.ClienteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Cliente")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class ClienteControlador {

    ClienteServicio clienteserv;

    @Autowired
    public ClienteControlador(ClienteServicio clienteserv) {
        this.clienteserv = clienteserv;
    }
    
    
    @GetMapping("/ListarClientes")
    public String listarclientes(ModelMap model){
    List<Cliente>clientes=clienteserv.listarclientes();
    model.addAttribute("clientes", clientes);
    
    return "cliente/listarclientes";
        
    }


        
        

    }


