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
import org.springframework.beans.factory.annotation.Autowired;

public class LibroServicio {

    @Autowired
    LibroRepositorio librorepositorio;
    AutorServicio autorserv;

    public void registrarlibro(Long isbn, String titulol,String nombreautor, Integer anio) throws Exception {
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulol);
        libro.setAnio(anio);
        autorserv.registrarautor(nombreautor,libro);
       //editorial
       librorepositorio.save(libro);
    }

}
