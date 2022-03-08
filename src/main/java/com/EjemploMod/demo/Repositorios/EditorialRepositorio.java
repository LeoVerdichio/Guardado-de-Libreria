/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Repositorios;

import com.EjemploMod.demo.Entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Leoo
 */
@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    
public List<Editorial> findAll();    
    
    
}
