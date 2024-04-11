package com.senai.ProjetoHelpDesk.Models;

import com.senai.ProjetoHelpDesk.DTO.UsuarioDTO;
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

    public UsuarioModel(UsuarioDTO usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public UsuarioModel(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public boolean usuarioEstaVazio() {
        if (this.id == null && this.nome == null && this.email == null) {
            return true;
        }
        return false;
    }
}
