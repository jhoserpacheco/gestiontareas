package com.sura.gestiontareas.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuarioEntity {
    @Id
    @Column(name = "id_usuario",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "email",nullable = false)
    private String email;
}
