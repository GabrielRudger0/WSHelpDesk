package com.senai.ProjetoHelpDesk.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "USUARIO")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioId")
    private Long id;

    @Column(name = "UsuarioNome")
    private String nome;

    @Column(name = "UsuarioEmail", unique = true)
    private String email;

    public UsuarioModel() {
    }
}
