package com.dev.parkapi.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_usuarios")
@Data
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;
    private String criadoPor;
    private String modificadoPor;

    @Column(nullable = false, length = 25)
    private Role role;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public enum Role{
        ROLE_ADMIN,
        ROLE_CLIENTE
    }
}
