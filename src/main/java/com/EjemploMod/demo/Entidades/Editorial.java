/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.EjemploMod.demo.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Editorial {

 @Id
 @GeneratedValue(generator = "uuid")
 @GenericGenerator(name = "uuid", strategy = "uuid2")
 private String id;   
 
 private Boolean alta;

    public Editorial() {
    }

    public Editorial(String id, Boolean alta) {
        this.id = id;
        this.alta = alta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
 
}
