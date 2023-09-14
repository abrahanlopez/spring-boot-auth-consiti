package com.titulo.crud.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.titulo.crud.dto.Mensaje;
import com.titulo.crud.dto.ProductoDto;
import com.titulo.crud.entity.Producto;
import com.titulo.crud.service.ProductoService;





@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")

public class ProductoController {
    @Autowired
    ProductoService productoService;

     @GetMapping("")
    public ResponseEntity<List<Producto>> findAll() {
        List<Producto> productos
     = productoService.list();
        return new ResponseEntity<List<Producto>>(productos
    , HttpStatus.OK);
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!productoService.existsById(id))
            return new ResponseEntity<>(new Mensaje("EL productos solicitado no existe"), HttpStatus.NOT_FOUND);
    
            Producto productos
         = productoService.getOne(id).get();
        return new ResponseEntity<>(productos
    , HttpStatus.OK);
    }

    @GetMapping("/detail-name/{name}")
    public ResponseEntity<?> getByNombre(@PathVariable("nombre") String nombre) {
        if (!productoService.existsByNombre(nombre))
            return new ResponseEntity<Mensaje>(new Mensaje("El productos con nombre"+nombre+"no existe"), HttpStatus.NOT_FOUND);


        Producto productos
     = productoService.getByNombre(nombre).get();
        return new ResponseEntity<Producto>(productos
    , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Mensaje> create(@RequestBody ProductoDto productoDto) {
        
        if (StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity<Mensaje>(new Mensaje("El nombre del productos es obligatorio"), HttpStatus.BAD_REQUEST);

        if (productoDto.getPrecio() == 0  ||  productoDto.getPrecio() < 0)
            return new ResponseEntity<Mensaje>(new Mensaje("El precio debe ser mayor 0.0"), HttpStatus.BAD_REQUEST);

        if (productoService.existsByNombre(productoDto.getNombre()))
            return new ResponseEntity<Mensaje>(new Mensaje("El nombre "+ productoDto+"ya se encuentra registrado"), HttpStatus.BAD_REQUEST);

        Producto productos
     = new Producto(productoDto.getNombre(), productoDto.getPrecio());
        productoService.save(productos
    );
        return new ResponseEntity<Mensaje>(new Mensaje("Producto creado con exito!"), HttpStatus.CREATED);
    }

     @PutMapping("/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody ProductoDto productoDto) {

    

        if (!productoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("El productos no existe"), HttpStatus.NOT_FOUND);

        if (productoService.existsByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id)
            return new ResponseEntity<Mensaje>(new Mensaje("El nombre" + productoDto.getNombre()+ "ya se encuentra registrado"), HttpStatus.BAD_REQUEST);

        if (StringUtils.isBlank(productoDto.getNombre()))
            return new ResponseEntity<Mensaje>(new Mensaje("El nombre del productos es obligatorio"), HttpStatus.BAD_REQUEST);

        if (productoDto.getPrecio() == 0 || productoDto.getPrecio()<0)
                
            return new ResponseEntity<Mensaje>(new Mensaje("El precio debe ser mayor a 0.0"), HttpStatus.BAD_REQUEST);


        Producto productos
     = productoService.getOne(id).get();
        productos
    .setNombre(productoDto.getNombre());
        productos
    .setPrecio(productoDto.getPrecio());
        productoService.save(productos
    );
        return new ResponseEntity<Mensaje>(new Mensaje("Producto actualizado con exito"), HttpStatus.OK);
    }
    @PreAuthorize("hasRol('ADMIN')")
     @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id") int id) {
        if (!productoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("El poducto a eliminar no existe"), HttpStatus.NOT_FOUND);

        productoService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("productos eliminado"), HttpStatus.OK);
    }

    
 
    
}
