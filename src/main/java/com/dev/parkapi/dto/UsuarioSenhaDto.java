package com.dev.parkapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@ToString
public class UsuarioSenhaDto {
    
    @NotBlank
    @Size(min = 6, max = 10)
    private String senhaAtual;

    @NotBlank
    @Size(min = 6, max = 10)
    private String novaSenha;

    @NotBlank
    @Size(min = 6, max = 10)
    private String confirmaSenha;

}
