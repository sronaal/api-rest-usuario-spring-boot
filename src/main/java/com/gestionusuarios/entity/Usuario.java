package com.gestionusuarios.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuarios")
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 150)
    private String password;
    
    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private boolean activo = true;

    public Usuario(){
    }

    public Usuario(String nombre, String correo, String password){
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    public boolean isActivo(){
        return activo;
    }

    public void setActivo(boolean activo){
        this.activo = activo;
    }
}