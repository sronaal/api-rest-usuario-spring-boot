
package com.gestionusuarios.dto;

public class UsuarioResponse {

    private Long id;
    private String nombre;
    private String correo;
    private boolean activo;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Long id, String nombre, String correo, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public boolean isActivo() {
        return activo;
    }
}
