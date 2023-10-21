package com.dev.parkapi.dto.mapper;

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
}
