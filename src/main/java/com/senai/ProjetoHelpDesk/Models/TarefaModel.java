package com.senai.ProjetoHelpDesk.Models;

import com.senai.ProjetoHelpDesk.DTO.TarefaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public TarefaModel(Long id, TarefaDTO tarefa, UsuarioModel tarefaUsuario) {
        this.id = id;
        this.nome = tarefa.getNome();
        this.descricao = tarefa.getDescricao();
        this.dataAgendamento = formatarData(tarefa.getDataAgendamento());
        this.status = converterHashCodeStatus(tarefa.getStatus());
        this.usuario = tarefaUsuario;
    }

    public TarefaModel(TarefaDTO tarefa, Date dataAgendamento, UsuarioModel tarefaUsuario) {
        this.nome = tarefa.getNome();
        this.descricao = tarefa.getDescricao();
        this.dataAgendamento = dataAgendamento;
        this.status =  converterHashCodeStatus(tarefa.getStatus());
        this.usuario = tarefaUsuario;
    }

    public enum Status {
        PENDENTE, EM_ANDAMENTO, CANCELADO, CONCLUIDO
    }

    private Status converterHashCodeStatus(Integer hashcode) {
        for (Status status : Status.values()) {
            if (status.ordinal() == hashcode) {
                return status;
            }
        }
        return Status.CANCELADO;
    }

    private Date formatarData(String stringData) {
        SimpleDateFormat formatoddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formatoddMMyyyy.parse(stringData);
        } catch (ParseException e) {
            return null;
        }

    }
}
