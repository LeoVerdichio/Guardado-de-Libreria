/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EjemploMod.demo.SeguridadConfiguracion;

import com.EjemploMod.demo.Servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

   
    

    public ClienteServicio clienteServicio;

    @Autowired
    public SeguridadConfig(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    
    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(clienteServicio).passwordEncoder(new BCryptPasswordEncoder());

    }
    
  
    @Override
    public void configure(HttpSecurity http) throws Exception{
     
               http
               .authorizeRequests()
               .antMatchers("/index/*","/css/*", "/js/*", "/img/*","/login/*").permitAll()
               .and()
               .formLogin()
               .loginPage("/Login/login")
               .loginProcessingUrl("/logincheck")
               .usernameParameter("email")
               .passwordParameter("password")
               .defaultSuccessUrl("/")
               .failureUrl("/Login/login?error=true").permitAll()
               .and()
               .logout()
               .logoutUrl("/logout")
               .logoutSuccessUrl("/login").permitAll()
               .and().csrf().disable();
               
   }
}
 
       
                


