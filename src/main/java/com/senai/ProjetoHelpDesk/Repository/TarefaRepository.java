package com.senai.ProjetoHelpDesk.Repository;

import com.senai.ProjetoHelpDesk.Models.TarefaModel;
import com.senai.ProjetoHelpDesk.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {

    public boolean existsByUsuario(UsuarioModel usuario);

    public List<TarefaModel> findAllByUsuario(UsuarioModel usuario);

}
