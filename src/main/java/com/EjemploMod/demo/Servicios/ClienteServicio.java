/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.Servicios;

import com.EjemploMod.demo.Entidades.Cliente;
import com.EjemploMod.demo.Enums.Roles;
import com.EjemploMod.demo.Repositorios.ClienteRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio implements UserDetailsService {

    ClienteRepositorio clienterepo;

    @Autowired
    public ClienteServicio(ClienteRepositorio clienterepo) {
        this.clienterepo = clienterepo;
    }

    @Transactional(rollbackOn = Exception.class)
    public void registrarcliente(Cliente cliente) throws Exception {
        validarcliente(cliente);
        cliente.setAlta(true);
        String email=cliente.getEmail();
        cliente.setEmail(email.toLowerCase());
        String ClaveEncriptada=new BCryptPasswordEncoder().encode(cliente.getPassword());
        cliente.setPassword(ClaveEncriptada);
        cliente.setRoles(Roles.USER);
        clienterepo.save(cliente);
    }

    public void logincliente(String email) throws Exception {
        //validacion
        if (email.trim().isEmpty()) {
            throw new Exception("Ingrese un email");

        }
        
//        Cliente cliente=clienterepo

    }

    public void validarcliente(Cliente cliente) throws Exception {

        if (cliente.getNombre().trim().isEmpty()) {

            throw new Exception("Ingrese un nombre");

        }

        if (cliente.getApellido().trim().isEmpty()) {

            throw new Exception("Ingrese un apellido");

        }

        if (cliente.getTelefono().trim().isEmpty() || cliente.getTelefono().length() != 11) {
            throw new Exception("Ingrese un numero de telefono tiene que ser de 10 digitos");

        }

        if (cliente.getDni() == null || cliente.getDni().length() != 8) {
            throw new Exception("Ingrese un dni tiene que ser de 8 digitos");

        }

        if (cliente.getEmail().trim().isEmpty()) {
            throw new Exception("Ingrese un email");

        }

        if (cliente.getPassword().trim().isEmpty()) {
            throw new Exception("Ingrese una contraseña");

        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Cliente cliente=clienterepo.findByEmail(email);
        
        if (cliente==null) {
            throw new UsernameNotFoundException("Usuario incorrecto");
        }
        
        List<GrantedAuthority> permisos=new ArrayList<>();
        GrantedAuthority rolespermisos=new SimpleGrantedAuthority("ROL_" + cliente.getRoles().toString());
        permisos.add(rolespermisos);
        
        return new User(cliente.getEmail(),cliente.getPassword(),permisos);
    }
}
