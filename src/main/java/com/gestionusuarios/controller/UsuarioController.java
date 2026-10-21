package com.gestionusuarios.controller;

import com.gestionusuarios.dto.CrearUsuarioRequest;
import com.gestionusuarios.dto.UsuarioResponse;
import com.gestionusuarios.entity.Usuario;
import com.gestionusuarios.mapper.UsuarioMapper;
import com.gestionusuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(
            @Valid @RequestBody CrearUsuarioRequest request
    ) {
        Usuario usuario = new Usuario(
                request.getNombre(),
                request.getCorreo(),
                request.getPassword()
        );

        Usuario usuarioGuardado = usuarioService.guardar(usuario);
        UsuarioResponse respuesta = UsuarioMapper.toResponse(usuarioGuardado);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }
}
