package com.senai.ProjetoHelpDesk.Repository;

import com.senai.ProjetoHelpDesk.Models.TarefaModel;
import com.senai.ProjetoHelpDesk.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {

    public boolean existsByUsuario(UsuarioModel usuario);

}
