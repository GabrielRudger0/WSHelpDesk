package com.senai.ProjetoHelpDesk.Services;

import com.senai.ProjetoHelpDesk.DTO.TarefaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaService {

    public List<TarefaDTO> obterTarefas() {
        return new ArrayList<>();
    }

    public boolean inserirTarefa(TarefaDTO tarefa) {
        return true;
    }

    public boolean atualizarTarefa(TarefaDTO tarefa) {
        return true;
    }

    public boolean excluirTarefa(Long tarefaId) {
        return true;
    }
}
