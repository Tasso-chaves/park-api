package com.dev.parkapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.parkapi.entities.Usuario;
import com.dev.parkapi.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario){

       Usuario usuarioSalvo = usuarioService.salvar(usuario);
       return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){

       Usuario usuario = usuarioService.buscarPorId(id);
       return ResponseEntity.ok().body(usuario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> alteraSenha(@PathVariable Long id, @RequestBody Usuario usuario){

       Usuario usuarioNovaSenha = usuarioService.editarSenha(id, usuario.getPassword());
       return ResponseEntity.ok().body(usuarioNovaSenha);
    }
}
