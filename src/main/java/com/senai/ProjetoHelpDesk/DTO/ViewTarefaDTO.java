package com.senai.ProjetoHelpDesk.DTO;

import com.senai.ProjetoHelpDesk.Models.TarefaModel;
import lombok.*;


@Data
public class ViewTarefaDTO {

    private String nome;

    private String descricao;

    private String dataAgendamento;

    private String status;

    private String usuarioEmail;


    public ViewTarefaDTO() {
    }

    public ViewTarefaDTO(TarefaModel tarefa) {
        this.nome = tarefa.getNome();
        this.descricao = tarefa.getDescricao();
        this.dataAgendamento = tarefa.getDataAgendamento().toString();
        this.status = tarefa.getStatus().name().replace('_',' ');
        this.usuarioEmail = tarefa.getUsuario().getEmail();
    }

}
