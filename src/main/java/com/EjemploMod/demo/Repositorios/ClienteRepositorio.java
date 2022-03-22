/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Repositorios;

import com.EjemploMod.demo.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,String> {
//   @Query("SELECT c from Cliente c WHERE c.email= :c.email ")
//    public Cliente ();
    
    public Cliente findByEmail(String email);
        
    
}
