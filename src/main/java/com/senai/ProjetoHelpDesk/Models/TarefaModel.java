package com.senai.ProjetoHelpDesk.Models;

import com.senai.ProjetoHelpDesk.DTO.TarefaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Table(name = "TAREFA")
public class TarefaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TarefaId")
    private Long id;

    @Column(name = "TarefaNome")
    private String nome;

    @Column(name = "TarefaDescricao")
    private String descricao;

    @Column(name = "TarefaDataAgendamento")
    private Date dataAgendamento;

    @Column(name = "TarefaStatus")
    private Status status;

    @ManyToOne
    private UsuarioModel usuario;

    public TarefaModel() {
    }

    public TarefaModel(TarefaDTO tarefa, UsuarioModel tarefaUsuario) {
        this.nome = tarefa.getNome();
        this.descricao = tarefa.getDescricao();
        this.dataAgendamento = tarefa.getDataAgendamento();
        this.status =  converterHashCodeStatus(tarefa.getStatus());
        this.usuario = tarefaUsuario;
    }

    public enum Status {
        PENDENTE, EM_ANDAMENTO, CANCELADO, CONCLUIDO
    }

    private Status converterHashCodeStatus(Integer hashcode) {
        for (Status status : Status.values()) {
            if (status.hashCode() == hashcode) {
                return status;
            }
        }
        return Status.CANCELADO;
    }
}
