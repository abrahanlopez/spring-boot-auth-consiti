package com.titulo.crud.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.titulo.crud.security.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario , Integer>{
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
}
