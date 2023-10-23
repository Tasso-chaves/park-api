package com.dev.parkapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.parkapi.dto.UsuarioCreateDto;
import com.dev.parkapi.dto.UsuarioResponseDto;
import com.dev.parkapi.dto.UsuarioSenhaDto;
import com.dev.parkapi.dto.mapper.UsuarioMapper;
import com.dev.parkapi.entities.Usuario;
import com.dev.parkapi.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criar(@RequestBody UsuarioCreateDto usuarioCreateDto){

       Usuario usuarioSalvo = usuarioService.salvar(UsuarioMapper.toUsuario(usuarioCreateDto));
       return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(usuarioSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable Long id){

       Usuario usuario = usuarioService.retornaPorId(id);
       return ResponseEntity.ok().body(UsuarioMapper.toDto(usuario));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> alteraSenha(@PathVariable Long id, @RequestBody UsuarioSenhaDto dto){

       Usuario usuarioNovaSenha = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
       return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos(){

       List<Usuario> usuarios = usuarioService.retornaTodos();
       return ResponseEntity.ok().body(usuarios);
    }
}
