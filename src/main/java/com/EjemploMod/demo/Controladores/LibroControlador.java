/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Controladores;

import com.EjemploMod.demo.Entidades.Autor;
import com.EjemploMod.demo.Entidades.Editorial;
import com.EjemploMod.demo.Entidades.Libro;
import com.EjemploMod.demo.Repositorios.AutorRepositorio;
import com.EjemploMod.demo.Repositorios.EditorialRepositorio;
import com.EjemploMod.demo.Servicios.AutorServicio;
import com.EjemploMod.demo.Servicios.EditorialServicio;
import com.EjemploMod.demo.Servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Libros")
public class LibroControlador {

    LibroServicio libroserv;
    AutorServicio autorserv;
    EditorialServicio editoserv;

    @Autowired
    public LibroControlador(LibroServicio libroserv, AutorServicio autorserv, EditorialServicio editoserv) {
        this.libroserv = libroserv;
        this.autorserv = autorserv;
        this.editoserv = editoserv;
    }

    @GetMapping("/Formulario")
    public String MostrarFormulario(ModelMap model) {
        model.addAttribute("libro", new Libro());
//        model.addAttribute("autor",new Autor());
//        model.addAttribute("editorial",new Editorial());
        return "libro/form-libro.html";
}
    
//                                                       @ModelAttribute Autor autor,@ModelAttribute Editorial editorial
    @PostMapping("/Formulario")
    public String ProcesarFormulario(@ModelAttribute Libro libro,ModelMap model){
        try{
            libroserv.registrarlibro(libro);
        }catch(Exception e){
           model.addAttribute("error",e.getMessage());
         return "libro/form-libro";
        }
     
        return "libro/lista-libros";
    
    }

    @GetMapping("/Lista")
    public String Listarlibros(ModelMap model) {
     List<Libro>libros=libroserv.Listarlibros();
     model.addAttribute("libros", libros);
        return "libro/lista-libros";
    }
}
