package com.senai.ProjetoHelpDesk.Repository;

import com.senai.ProjetoHelpDesk.Models.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {
}
