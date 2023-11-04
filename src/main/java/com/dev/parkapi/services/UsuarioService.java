package com.dev.parkapi.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.parkapi.entities.Usuario;
import com.dev.parkapi.exception.EntityUserNotFoundException;
import com.dev.parkapi.exception.PasswordInvalidException;
import com.dev.parkapi.exception.UsernameUniqueViolationException;
import com.dev.parkapi.repositories.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    
    private final IUsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado!", usuario.getUsername()));
        }
        
    }

    @Transactional(readOnly = true)
    public Usuario retornaPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
            () -> new EntityUserNotFoundException(String.format("Usuário id:{%s} não encontrado!", id))
        );
    }

    //Nesse caso, não utiliza o UPDATE/SAVE, o Hibernat entende o estado do Obj usuário, através do setPassword automaticamente identifica a mudança.
    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        
        if(!novaSenha.equals(confirmaSenha)){
             throw new PasswordInvalidException("Nova senha está diferente da confirmação de senha");
        }

        Usuario usuario = retornaPorId(id);

        //Garantir que é o usuario autenticado
        if(!usuario.getPassword().equals(senhaAtual)){
             throw new PasswordInvalidException("Senhas não conferem");
        }
        usuario.setPassword(novaSenha);
        return usuario;
    }

    @Transactional(readOnly = true)
    public List<Usuario> retornaTodos() {
        return usuarioRepository.findAll();
    }
}
