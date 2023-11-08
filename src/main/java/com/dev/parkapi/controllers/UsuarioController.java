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
import com.dev.parkapi.exception.ErrorMessage;
import com.dev.parkapi.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos de cadastro, edição e leitura de um usuário.")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário",
            responses = {
               @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
               @ApiResponse(responseCode = "409", description = "Usuário e-mail já cadastrado no sistema",
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
               @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entradas invalidos",
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criar(@Valid @RequestBody UsuarioCreateDto usuarioCreateDto){

       Usuario usuarioSalvo = usuarioService.salvar(UsuarioMapper.toUsuario(usuarioCreateDto));
       return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(usuarioSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable Long id){

       Usuario usuario = usuarioService.retornaPorId(id);
       return ResponseEntity.ok().body(UsuarioMapper.toDto(usuario));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> alteraSenha(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto){

       Usuario usuarioNovaSenha = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
       return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> buscarTodos(){

       List<Usuario> usuarios = usuarioService.retornaTodos();
       return ResponseEntity.ok().body(UsuarioMapper.toListDto(usuarios));
    }
}
