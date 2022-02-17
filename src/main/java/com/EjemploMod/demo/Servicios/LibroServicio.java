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
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    private LibroRepositorio librorepositorio;

    @Autowired
    public LibroServicio(LibroRepositorio librorepositorio) {
        this.librorepositorio = librorepositorio;
    }

    AutorServicio autorserv;

    @Transactional(rollbackOn = {Exception.class})
    public void registrarlibro(Long isbn, String titulol, String nombreautor, Integer anio) throws Exception {
        validarlibro(titulol, nombreautor, anio, isbn);
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulol);
        libro.setAnio(anio);
        autorserv.registrarautor(nombreautor, libro);
        //editorial
        librorepositorio.save(libro);
    }

    @Transactional
    public Libro BuscarLibroPorId(String id) throws Exception {
        Libro libro = librorepositorio.getById(id);
        if (libro == null) {
            throw new Exception("Libro Inexistente");

        }
        return libro;
    }

    public void validarlibro(String titulo1, String nombreautor, Integer anio, Long isbn) throws Exception {
        if (titulo1.trim().isEmpty()) {
            throw new Exception("Libro sin titulo");
        }

        if (nombreautor.trim().isEmpty()) {
            throw new Exception("Autor sin nombre");

        }

        if (anio == null) {
            throw new Exception("Ingrese Correctamente el anio");
        }

        if (isbn == null) {
            throw new Exception("Libro sin ISBN");

        }
    }

    @Transactional
    public void BajaLibro(String id) throws Exception {
        Libro libro = BuscarLibroPorId(id);
        if (libro.getAlta() == true) {
            libro.setAlta(false);
        } else {
            throw new Exception("Libro dado de baja");
        }
    }

    public List<Libro> Listarlibros() {
        List<Libro> libros = librorepositorio.findAll();
        return libros;
    }
}
