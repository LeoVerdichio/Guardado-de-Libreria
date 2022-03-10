/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Servicios;

import com.EjemploMod.demo.Entidades.Autor;
import com.EjemploMod.demo.Entidades.Editorial;
import com.EjemploMod.demo.Entidades.Libro;
import com.EjemploMod.demo.Repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    private LibroRepositorio librorepositorio;
    private AutorServicio autorserv;
    private EditorialServicio editoserv;

    @Autowired
    public LibroServicio(LibroRepositorio librorepositorio, AutorServicio autorserv, EditorialServicio editoserv) {
        this.librorepositorio = librorepositorio;
        this.autorserv = autorserv;
        this.editoserv = editoserv;
    }

  

    @Transactional(rollbackOn = {Exception.class})
    public void registrarlibro(Libro libro,String autorid,String editorialid) throws Exception {
        validarlibro(libro,autorid);
        libro.setAlta(true);
        Autor autor=autorserv.BuscarAutorPorId(autorid);
        libro.setAutor(autor);
        Editorial editorial=editoserv.BuscarEditorialporId(editorialid);
        libro.setEditorial(editorial);
        librorepositorio.save(libro);
    }


   

    @Transactional
    public Libro BuscarLibroPorId(String id) throws Exception {
        Optional<Libro> option = librorepositorio.findById(id);
        if (option.isPresent()) {
            Libro libro = option.get();
            return libro;
        } else {
            throw new Exception("Libro no encontrado");
        }
    }

    public void validarlibro(Libro libro,String autorid) throws Exception {
        if (libro.getIsbn() == null) {
            throw new Exception("Ingrese el isbn");
        }

        if (libro.getTitulo().isEmpty()) {
            throw new Exception("Ingrese el titulo");

        }

        if (libro.getAnio() == null) {
            throw new Exception("Ingrese el anio");

        }
        
        if (autorid.isEmpty()) {
            throw new Exception("Ingrese la zona");
        }
        
//        if (autor.getNombre().isEmpty()) {
//            throw new Exception("Ingrese el nombre del autor");
//        }
//        
//        if (editorial.getNombre().isEmpty()) {
//            throw new Exception ("Ingrese el nombre del editorial");
//        }

    }

    @Transactional(rollbackOn = {Exception.class})
    public void BajaLibro(String id) throws Exception {
        Libro libro = BuscarLibroPorId(id);
        if (libro.getAlta() == true) {
            libro.setAlta(false);
        } else {
            throw new Exception("Libro dado de baja");
        }
    }
//modificar (Alta)

    public List<Libro> Listarlibros() {
        List<Libro> libros = librorepositorio.findAll();
        return libros;
    }
}
