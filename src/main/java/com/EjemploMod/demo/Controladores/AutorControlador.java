/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Controladores;

import com.EjemploMod.demo.Entidades.Autor;
import com.EjemploMod.demo.Servicios.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
       
        return "redirect:/Autor/ListaAutores";
    }
    
    
    

    @GetMapping("/ListaAutores")
    public String listarautores(ModelMap model){
      List<Autor> autores = autorserv.listarautores();
      model.addAttribute("autores", autores);
        
      return "autor/lista-autores";
    }
    
    @GetMapping("/{id}")
    public String modificarautor(@PathVariable("id") String autorid,ModelMap model){
        try {
            Autor autor=autorserv.BuscarAutorPorId(autorid);
             List<Autor> autores = autorserv.listarautores();
             model.addAttribute("autor", autor);
             model.addAttribute("autores", autores);
        } catch (Exception ex) {
           model.addAttribute("error", ex.getMessage());
        }
        return "autor/form-autor";
    }
    
    @GetMapping("/Eliminar/{id}")
    public String eliminarautor(@PathVariable("id") String autorid,ModelMap model){
       try{
         autorserv.eliminarautor(autorid);

       }catch(Exception ex){
           model.addAttribute("error", ex.getMessage());
       } 
        
        return "redirect:/Autor/ListaAutores";
    }
}
