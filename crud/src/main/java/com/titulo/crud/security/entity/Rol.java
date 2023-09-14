/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.titulo.crud.security.entity;

import com.sun.istack.NotNull;
import com.titulo.crud.security.enums.RolNombre;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author isaac
 *  
 */

@Entity
@Table(name="rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rol implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @NotNull
   @Enumerated(EnumType.STRING)
   private RolNombre rolNombre;
   
   public Rol (@NotNull RolNombre rolNombre){
       this.rolNombre = rolNombre;
   }
   
}

