/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.EjemploMod.demo.Repositorios;

import com.EjemploMod.demo.Entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{

//  @Query("SELECT a from Autor a WHERE a.id = :id ")
//   public Autor buscarAutorPorId(@Param("id") String id);
    
    
   
}
