
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
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService(
                usuarioRepository,
                passwordEncoder
        );
    }

    @Test
    void guardarDebeGuardarUsuarioCuandoCorreoNoExiste() {
        // Arrange
        Usuario usuario = new Usuario(
                "Ronal Sánchez",
                "ronal@example.com",
                "passwordTemporal"
        );

        when(usuarioRepository.existsByCorreo(usuario.getCorreo()))
                .thenReturn(false);

        when(passwordEncoder.encode("passwordTemporal"))
                .thenReturn("hashed-password");

        when(usuarioRepository.save(usuario))
                .thenReturn(usuario);

        // Act
        Usuario resultado = usuarioService.guardar(usuario);

        // Assert
        assertSame(usuario, resultado);
        assertEquals("hashed-password", resultado.getPassword());

        verify(usuarioRepository).existsByCorreo("ronal@example.com");
        verify(passwordEncoder).encode("passwordTemporal");
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void guardarDebeLanzarExcepcionCuandoCorreoExiste() {
        // Arrange
        Usuario usuario = new Usuario(
                "Ronal Sánchez",
                "ronal@example.com",
                "passwordTemporal"
        );

        when(usuarioRepository.existsByCorreo(usuario.getCorreo()))
                .thenReturn(true);

        // Act & Assert
        assertThrows(
                CorreoDuplicadoException.class,
                () -> usuarioService.guardar(usuario)
        );

        verify(usuarioRepository).existsByCorreo("ronal@example.com");
        verify(passwordEncoder, never()).encode("passwordTemporal");
        verify(usuarioRepository, never()).save(usuario);
    }
}
