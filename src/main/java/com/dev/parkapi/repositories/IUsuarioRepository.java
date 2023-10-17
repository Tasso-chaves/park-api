package com.dev.parkapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.parkapi.entities.Usuario;

@Repository
public interface IUsuarioRepository  extends JpaRepository<Long, Usuario>{
    
}
