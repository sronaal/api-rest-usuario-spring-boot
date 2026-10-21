
package com.gestionusuarios.service;

import com.gestionusuarios.entity.Usuario;
import com.gestionusuarios.repository.UsuarioRepository;
import com.gestionusuarios.exception.CorreoDuplicadoException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
        UsuarioRepository usuarioRepository,
        PasswordEncoder passwordEncoder    
    ) 
    {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public Usuario guardar(Usuario usuario) {
        
        if(usuarioRepository.existsByCorreo(usuario.getCorreo())){
            throw new CorreoDuplicadoException(usuario.getCorreo());
        }

        String passeordHash = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passeordHash);
        return usuarioRepository.save(usuario);
    }

    public boolean existeCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }
}
