package com.senai.ProjetoHelpDesk.Services;

import com.senai.ProjetoHelpDesk.DTO.UsuarioDTO;
import com.senai.ProjetoHelpDesk.Models.UsuarioModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    public List<UsuarioDTO> obterUsuarios() {
        return new ArrayList<>();
    }

    public boolean inserirUsuario(UsuarioDTO usuario) {
        return true;
    }

    public boolean atualizarUsuario(UsuarioDTO usuario) {
        return true;
    }

    public boolean excluirUsuario(String email) {
        return true;
    }

    public UsuarioModel obterUsuario(String email) {
        return new UsuarioModel();
    }


}
