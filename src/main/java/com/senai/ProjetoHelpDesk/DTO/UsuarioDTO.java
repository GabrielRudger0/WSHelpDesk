package com.senai.ProjetoHelpDesk.DTO;

import com.senai.ProjetoHelpDesk.Models.UsuarioModel;
import com.senai.ProjetoHelpDesk.Padroes.RespostaDTO;
import lombok.*;

@Data
public class UsuarioDTO {

    private String nome;

    private String email;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String email) {
        this.nome  = nome;
        this.email = email;
    }

    public UsuarioDTO(UsuarioModel usuario) {
        this.nome  = usuario.getNome();
        this.email = usuario.getEmail();
    }

}
