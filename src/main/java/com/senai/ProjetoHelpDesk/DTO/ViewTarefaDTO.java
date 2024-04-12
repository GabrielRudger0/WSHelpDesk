package com.senai.ProjetoHelpDesk.DTO;

import com.senai.ProjetoHelpDesk.Models.TarefaModel;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
        this.dataAgendamento = formatarData(tarefa.getDataAgendamento());
        this.status = tarefa.getStatus().name().replace('_',' ');
        this.usuarioEmail = tarefa.getUsuario().getEmail();
    }

    private String formatarData(Date data) {
        SimpleDateFormat formatoddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
        return formatoddMMyyyy.format(data);

    }

}
