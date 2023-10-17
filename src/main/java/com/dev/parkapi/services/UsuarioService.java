package com.dev.parkapi.services;

import org.springframework.stereotype.Service;

import com.dev.parkapi.entities.Usuario;
import com.dev.parkapi.repositories.IUsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    
    private final IUsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
