package com.senai.ProjetoHelpDesk.Models;

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

    public enum Status {
        PENDENTE, EM_ANDAMENTO, CANCELADO, CONCLUIDO
    }

}
