package com.dev.parkapi.dto;

import jakarta.validation.constraints.Email;
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
public class UsuarioCreateDto {
    
    @NotBlank
    @Email(message = "Formato de Email inválido", 
    regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String username;

    @NotBlank
    @Size(min = 6, max = 10)
    private String password;
}
