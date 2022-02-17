/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.EjemploMod.demo.Controladores;

import com.EjemploMod.demo.Entidades.Libro;
import com.EjemploMod.demo.Servicios.LibroServicio;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Libros")
public class LibroControlador {

    LibroServicio libroserv;

@GetMapping()  
public String MostrarLibros(ModelMap model){
List<Libro> libros = libroserv.Listarlibros();
model.addAttribute("libros", libros);
return "Libros/Lista-Libros";
        }    

    
}
