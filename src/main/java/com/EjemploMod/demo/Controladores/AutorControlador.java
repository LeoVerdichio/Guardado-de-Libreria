/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Controladores;

import com.EjemploMod.demo.Entidades.Autor;
import com.EjemploMod.demo.Servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Autor")
public class AutorControlador {

    AutorServicio autorserv;

    @Autowired
    public AutorControlador(AutorServicio autorserv) {
        this.autorserv = autorserv;
    }

    @GetMapping("/FormAutor")
    public String mostrarformulario(ModelMap model) {

        model.addAttribute("autor", new Autor());

        return "autor/form-autor";

    }
    
    
    @PostMapping("/FormAutor")
    public String procesarformulario(@ModelAttribute Autor autor,ModelMap model){
        try{
            autorserv.guardarautor(autor);
            
        }catch(Exception ex){
          model.addAttribute("error", ex.getMessage());
          return "autor/form-autor";
        }
        
        return "autor/form-autor";
    }

    @GetMapping("/ListaLibros")
    public String listarautores(ModelMap model){
        
        model.addAttribute("autores",autorserv.listarautores());
        
    }
}
