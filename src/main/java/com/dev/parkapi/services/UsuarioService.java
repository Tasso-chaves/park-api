package com.dev.parkapi.services;

import org.springframework.stereotype.Service;

import com.dev.parkapi.repositories.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    
    private final IUsuarioRepository usuarioRepository;
}
