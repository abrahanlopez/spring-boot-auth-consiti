package com.titulo.crud.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titulo.crud.security.entity.Rol;
import com.titulo.crud.security.enums.RolNombre;

public interface RolRepository extends JpaRepository<Rol , Integer> {
    
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
