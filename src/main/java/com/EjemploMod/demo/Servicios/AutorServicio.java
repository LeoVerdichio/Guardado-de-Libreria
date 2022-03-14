/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Servicios;

import com.EjemploMod.demo.Entidades.Autor;
import com.EjemploMod.demo.Entidades.Libro;
import com.EjemploMod.demo.Repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

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


    public void validarautor(Autor autor) throws Exception {

        if (autor.getNombre().trim().isEmpty()) {
            throw new Exception("Nombre del autor vacio");
        }

    }

    @Transactional(rollbackOn = Exception.class)
    public Autor BuscarAutorPorId(String id) throws Exception {
        Optional<Autor> option = autorrepositorio.findById(id);
        if (option.isPresent()) {
            Autor autor=option.get();
            return autor;
        }else{
            throw new Exception ("Autor no encontrado");
        }

    }

    //metodos de alta y baja
    @Transactional
    public void bajaautor(Autor autor, String id) throws Exception {
        BuscarAutorPorId(id);
        if (autor.getAlta() == true) {
            autor.setAlta(false);

        }
    }
    
    public void editar(Autor autor,String nombre){
        autor.setNombre(nombre);
    }

    public void altaautor(Autor autor, String id) throws Exception {
        BuscarAutorPorId(id);
        if (autor.getAlta() == false) {
            autor.setAlta(true);
        }

    }

    public List listarautores() {
        List<Autor> autores = autorrepositorio.findAll();

        return autores;

    }
    
    public void eliminarautor(String autorid) throws Exception{
//        Autor autor=BuscarAutorPorId(autorid);
//        autorrepositorio.delete(autor);
        autorrepositorio.deleteById(autorid);
        
    }
}
