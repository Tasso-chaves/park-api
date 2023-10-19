package com.dev.parkapi.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.parkapi.entities.Usuario;
import com.dev.parkapi.repositories.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    
    private final IUsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario retornaPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Usuário não encontrado!")
        );
    }

    //Nesse caso, não utiliza o UPDATE/SAVE, o Hibernat entende o estado do Obj usuário, através do setPassword automaticamente identifica a mudança.
    @Transactional
    public Usuario editarSenha(Long id, String password) {
        Usuario usuario = retornaPorId(id);
        usuario.setPassword(password);
        return usuario;
    }

    @Transactional(readOnly = true)
    public List<Usuario> retornaTodos() {
        return usuarioRepository.findAll();
    }
}
