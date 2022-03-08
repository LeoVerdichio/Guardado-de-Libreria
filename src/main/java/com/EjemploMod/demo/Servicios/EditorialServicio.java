/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Servicios;

import com.EjemploMod.demo.Entidades.Editorial;
import com.EjemploMod.demo.Repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    EditorialRepositorio editorialrepo;

    @Autowired
    public EditorialServicio(EditorialRepositorio editorialrepo) {
        this.editorialrepo = editorialrepo;
    }

    @Transactional
    public void creareditorial(String nombre) throws Exception {
        validareditorial(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        editorialrepo.save(editorial);
    }

    public void validareditorial(String nombre) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("Introduzca el nombre correctamente");
        }

    }

    @Transactional
    public List listareditorial(){
    List<Editorial> editoriales = editorialrepo.findAll();
        
    return editoriales;
        }
    
    public Editorial BuscarEditorialporId(String id) throws Exception{
     Optional<Editorial> option=editorialrepo.findById(id);
        if (option.isPresent()) {
            Editorial editorial=option.get();
            return editorial;
        }else{
            throw new Exception("Editorial no encontrado");
        }
   
        
    }
}
