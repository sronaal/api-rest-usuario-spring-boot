package com.gestionusuarios;

import com.gestionusuarios.entity.Usuario;
import com.gestionusuarios.exception.CorreoDuplicadoException;
import com.gestionusuarios.repository.UsuarioRepository;
import com.gestionusuarios.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {
    
    @Mock
    private UsuarioRepository usuarioRepository;
    
    private UsuarioService usuarioService;

    @BeforeEach 
    void setup(){
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    void guardarUsuarioCuandoCorreoNoExiste(){
        Usuario usuario = new Usuario(
            "Ronal Sánchez",
            "ronal@example.com",
            "passwordTemporal"
        );

        when(usuarioRepository.existsByCorreo(usuario.getCorreo()))
        .thenReturn(false);

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.guardar(usuario);

        assertSame(usuario, resultado);
        verify(usuarioRepository).existsByCorreo("ronal@example.com");
        verify(usuarioRepository).save(usuario);
   
    }

    @Test
    void guardarDebeLanzarExcepcionCuandoCorreoExiste() {
        Usuario usuario = new Usuario(
                "Ronal Sánchez",
                "ronal@example.com",
                "passwordTemporal"
        );

        when(usuarioRepository.existsByCorreo(usuario.getCorreo()))
                .thenReturn(true);

        assertThrows(
                CorreoDuplicadoException.class,
                () -> usuarioService.guardar(usuario)
        );

        verify(usuarioRepository).existsByCorreo("ronal@example.com");
        verify(usuarioRepository, never()).save(usuario);
    }
}
