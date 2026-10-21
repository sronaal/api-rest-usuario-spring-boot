package com.gestionusuarios.mapper;

import com.gestionusuarios.dto.UsuarioResponse;
import com.gestionusuarios.entity.Usuario;

public final class UsuarioMapper {
    
    private UsuarioMapper(){}

    public static UsuarioResponse toResponse(Usuario usuario){
        return new UsuarioResponse(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getCorreo(),
            usuario.isActivo()
        );
    }
}
