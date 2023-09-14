package com.titulo.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.titulo.crud.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
   Optional<Producto> findByNombre(String nombre);
   
    boolean existsByNombre(String nombre);  
}
