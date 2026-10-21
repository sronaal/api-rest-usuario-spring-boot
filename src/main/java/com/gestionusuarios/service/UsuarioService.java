
package com.gestionusuarios.service;

import com.gestionusuarios.entity.Usuario;
import com.gestionusuarios.repository.UsuarioRepository;
import com.gestionusuarios.exception.CorreoDuplicadoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
        return usuarioRepository.save(usuario);
    }

    public boolean existeCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }
}
