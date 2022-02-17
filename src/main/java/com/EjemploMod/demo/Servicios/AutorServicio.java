/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Servicios;

import com.EjemploMod.demo.Entidades.Autor;
import com.EjemploMod.demo.Entidades.Libro;
import com.EjemploMod.demo.Repositorios.AutorRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio  {
    
    AutorRepositorio autorrepositorio;
    
    @Autowired
    public AutorServicio(AutorRepositorio autorrepositorio) {
        this.autorrepositorio = autorrepositorio;
    }
    
    @Transactional(rollbackOn = {Exception.class})
    public void guardarautor(Autor autor) throws Exception {
        
        validarautor(autor);
        
        if (autor.getAlta() == null) {
            autor.setAlta(true);
        }
        
        autorrepositorio.save(autor);
        
    }
    @Transactional
    public void registrarautor(String nombre,Libro libro) throws Exception {
        Autor autor = new Autor();
        libro.setAutor(autor);
        autor.setNombre(nombre);
        guardarautor(autor);
    }
    
    public void validarautor(Autor autor) throws Exception {
        
        if (autor.getNombre().trim().isEmpty()) {
            throw new Exception("Nombre del autor vacio");
        }
        
    }
    
    @Transactional    
    public Autor BuscarAutorPorId(String id) throws Exception {
        Autor autor = autorrepositorio.getById(id);        
        if (autor == null) {
            throw new Exception("Autor inexistente");
        }
        
        return autor;
    }

    //metodos de alta y baja
    @Transactional
    public void bajaautor(Autor autor, String id) throws Exception {
        BuscarAutorPorId(id);
        if (autor.getAlta() == true) {
            autor.setAlta(false);
            
        }
    }

    public void altaautor(Autor autor, String id) throws Exception {
        BuscarAutorPorId(id);
        if (autor.getAlta() == false) {
            autor.setAlta(true);
        }
        
    }    
    
}
