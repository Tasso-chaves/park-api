package com.dev.parkapi.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.dev.parkapi.dto.UsuarioCreateDto;
import com.dev.parkapi.dto.UsuarioResponseDto;
import com.dev.parkapi.entities.Usuario;

public class UsuarioMapper {
    
    //Conversão de Objetos (UsuarioCreateDto -> Usuario)
    public static Usuario toUsuario(UsuarioCreateDto usuarioCreateDto){
        return new ModelMapper().map(usuarioCreateDto, Usuario.class);
    }
    
    public static UsuarioResponseDto toDto(Usuario usuario){

        //Remover o Role de (ROLE_PAPEL)
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario,UsuarioResponseDto>() {

            @Override
            protected void configure() {
                map().setRole(role);
            }
            
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios){
        return usuarios.stream().map(usuario -> toDto(usuario)).collect(Collectors.toList());
    }
}
