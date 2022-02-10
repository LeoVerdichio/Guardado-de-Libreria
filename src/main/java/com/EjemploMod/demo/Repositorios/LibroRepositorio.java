/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.EjemploMod.demo.Repositorios;

import com.EjemploMod.demo.Entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LibroRepositorio extends JpaRepository<Libro, String> {

    
    
}
