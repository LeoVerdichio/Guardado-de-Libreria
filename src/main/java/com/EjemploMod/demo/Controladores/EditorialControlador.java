/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Controladores;

import com.EjemploMod.demo.Entidades.Editorial;
import com.EjemploMod.demo.Servicios.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Editorial")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class EditorialControlador {

    EditorialServicio editorialserv;

    @Autowired
    public EditorialControlador(EditorialServicio editorialserv) {
        this.editorialserv = editorialserv;
    }

    @GetMapping("/FormEditorial")
    public String mostrarformulario(ModelMap model) {
        model.addAttribute("editorial", new Editorial());
        

        return "editorial/form-editorial";

    }

    @PostMapping("/FormEditorial")
    public String procesarformulario(@ModelAttribute Editorial editorial, ModelMap model) {
        try {
            editorialserv.creareditorial(editorial);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "editorial/form-editorial";

        }

        return "editorial/form-editorial";
    }

    @GetMapping("/ListaEditorial")
    public String listareditoriales(ModelMap model) {
        List<Editorial> editoriales = editorialserv.listareditorial();
        model.addAttribute("editoriales", editoriales);
        return "editorial/lista-editoriales";
    }
}
